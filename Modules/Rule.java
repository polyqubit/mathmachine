package Modules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import MathTypes.*;
import MathTypes.Number;

public class Rule {
    private MathObject search;
    private MathObject replace;
    private Queue<MathObject> tempnums;
    private Queue<MathObject> tempmaths; // used to store any mathobject for replacement of Null in pattern
    private HashMap<String, String> varmap; // sorted search, target

    // x+1 -> Addition(x,1)
    public Rule(String s, String r) {
        search = conversion(s);
        replace = conversion(r);
        tempnums = new LinkedList<>();
        tempmaths = new LinkedList<>();
        varmap = new HashMap<>();
        // .offer() adds, .poll() removes
    }

    // if this does not work, consider Object.equals recursive approach
    public void apply(MathObject m) {
        if (traverse(m, search)) {
            substitute(m);
        }
    }

    // current target layer, current search(as in this.search) layer
    private boolean traverse(MathObject cur, MathObject s) {
        if (s.name() == "Null") {
            tempmaths.offer(cur);
            return true;
        }

        if (!(s.name() == cur.name())) {
            return false;
        }

        if (s.name() == "Variable") {
            if (!varmap.containsKey(s.name()) && !varmap.containsValue(cur.name())) {
                varmap.put(s.name(), cur.name());
                return true;
            }
            if (cur.name() == varmap.get(s.name())) {
                return true;
            }
            return false;
        }

        if (s.name() == "Literal") { // searching for a specific number
            if (s.value() != cur.value()) {
                return false;
            }
        }

        else if (s.name() == "Number") { // searching for any number
            tempnums.offer(cur);
        }

        // else if(cur.name() == "Number") { // also variable, also functionality for
        // both literal and expression Number search
        // return s.name() == "Number"; // eg. false if any number except 1
        // }

        if (cur.type() == "Operator") {
            Operator ocur = (Operator) cur;
            Operator os = (Operator) s;
            boolean b1 = traverse(ocur.parameter1(), os.parameter1());
            boolean b2 = traverse(ocur.parameter2(), os.parameter2());
            return (b1 && b2);
        }
        // TODO: need case for function
        return false;
    }

    // substitute traverse
    // current target layer, current replacement layer
    private void subtraverse(MathObject cur, MathObject r) {
        if (r.name() == "Number") {
            ((Number)cur).setval(tempnums.poll().value());
            return;
        }

        if (r.name() == "Variable") {
            ((Variable)cur).settoken(varmap.get(((Variable)r).token()));
            return;
        }

        // else if(cur.name() == "Number") { // also variable, also functionality for
        // both literal and expression Number search
        // return s.name() == "Number"; // eg. false if any number except 1
        // }

        if (cur.type() == "Operator") {
            Operator ocur = (Operator) cur;
            Operator or = (Operator) r;
            if(or.parameter1().name()=="Null") {
                ocur.setP1(tempmaths.poll());
                return;
            }
            if(or.parameter2().name()=="Null") {
                ocur.setP2(tempmaths.poll());
                return;
            }
            subtraverse(ocur.parameter1(), or.parameter1());
            subtraverse(ocur.parameter2(), or.parameter2());
        }
    }

    // Number -> Literal(order Numbers in rule description)
    // _N(0) + _N(1) = _N(_N(1) + _N(2)) ?
    // _L(0) + _N(0) =
    private void substitute(MathObject m) {
        subtraverse(m, replace);
    }

    private MathObject conversion(String s) {
        ArrayList<String> in = new ArrayList<>();
        in = Split.parse(s, false);

        HashMap<String, Integer> funcs = new HashMap<>();
        funcs.put("_neg", 1);

        Stack<MathObject> values = new Stack<>();
        ArrayList<MathObject> temps = new ArrayList<>();
        for (int i = 0; i < in.size(); i++) {
            char c = in.get(i).charAt(0);
            if (Character.isLetter(c)) {
                values.push(new Variable(in.get(i)));
            } else if (Character.isDigit(c)) {
                values.push(new Number(Double.parseDouble(in.get(i))));
            } else if (c == '_') {
                if (funcs.containsKey(in.get(i))) {
                    int argnum = funcs.get(in.get(i));
                    for (int k = 0; k < argnum; k++) { // pops for each argument
                        temps.add(values.pop());
                    }
                    switch (in.get(i)) {
                        case "neg":
                            values.add(new Op_Sub(new Number(0), temps.get(0)));
                            break;
                    }
                    for (int k = 0; k < argnum; k++) {
                        temps.clear();
                    }
                }
            } else { // assume that all tokens are binary operators, and that syntax is correct
                temps.add(values.pop()); // second item(0)
                temps.add(values.pop()); // first item(1)
                switch (c) {
                    case '+':
                        values.add(new Op_Add(temps.get(1), temps.get(0)));
                        break;
                    case '-':
                        values.add(new Op_Sub(temps.get(1), temps.get(0)));
                        break;
                    case '*':
                        values.add(new Op_Mult(temps.get(1), temps.get(0)));
                        break;
                    case '/':
                        values.add(new Op_Div(temps.get(1), temps.get(0)));
                        break;
                    case '^':
                        values.add(new Op_Pow(temps.get(1), temps.get(0)));
                        break;
                }
                temps.clear();
            }
        }
        return values.get(0);
    }
}
