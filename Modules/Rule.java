package Modules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import MathTypes.*;
import MathTypes.Number;

public class Rule {
    private MathObject search;
    private MathObject replace;
    private ArrayList<Number> tempvals;

    // x+1 -> Addition(x,1)
    public Rule(String s, String r) {
        search = conversion(s);
        replace = conversion(r);
    }

    // if this does not work, consider Object.equals recursive approach
    public MathObject apply(MathObject m) {
        if(traverse(m, search)) {
            return substitute();
        }
        return m;
    }

    // current target layer, current search(as in this.search) layer
    private boolean traverse(MathObject cur, MathObject s) {
        if(s.name()=="MathObject") {
            return true; // TODO: store MathObject for MathObject manipulation rules
        }
        else if(!(cur.name()==s.name())) {
            return false;
        }

        // if(s.name() == "Literal") {

        // }
        // else if(cur.name() == "Number") { // also variable, also functionality for both literal and expression Number search
        //     return s.name() == "Number"; // eg. false if any number except 1
        // }

        if(cur.type()=="Operator"){
            Operator ocur = (Operator)cur;
            Operator os = (Operator)s;
            boolean b1 = traverse(ocur.parameter1(), os.parameter1());
            boolean b2 = traverse(ocur.parameter2(), os.parameter2());
            return (b1&&b2);
        }
        // TODO: need case for function
        return false;
    }

    // Number -> Literal(order Numbers in rule description)
    // _N(0) + _N(1) = _N(_N(1) + _N(2)) ?
    // _L(0) + _N(0) = 
    private MathObject substitute() {
        return new Null();
    }

    private MathObject conversion(String s) {
        ArrayList<String> in = new ArrayList<>();
        in = Split.parse(s, false);

        HashMap<String, Integer> funcs = new HashMap<>();
        funcs.put("_neg", 1);

        Stack<MathObject> values = new Stack<>();
        ArrayList<MathObject> temps = new ArrayList<>();
        for(int i=0;i<in.size();i++) {
            char c = in.get(i).charAt(0);
            if(Character.isLetter(c)) {
                values.push(new Variable(in.get(i)));
            }
            else if(Character.isDigit(c)) {
                values.push(new Number(Double.parseDouble(in.get(i))));
            }
            else if(c=='_') {
                if(funcs.containsKey(in.get(i))) {
                    int argnum = funcs.get(in.get(i));
                    for(int k=0;k<argnum;k++) { // pops for each argument
                        temps.add(values.pop());
                    }
                    switch(in.get(i)) {
                        case "neg":
                            values.add(new Op_Sub(new Number(0),temps.get(0)));
                            break;
                    }
                    for(int k=0;k<argnum;k++) {
                        temps.clear();
                    }
                }
            }
            else { // assume that all tokens are binary operators, and that syntax is correct
                temps.add(values.pop()); // second item(0)
                temps.add(values.pop()); // first item(1)
                switch(c) {
                    case '+':
                        values.add(new Op_Add(temps.get(1),temps.get(0)));
                        break;
                    case '-':
                        values.add(new Op_Sub(temps.get(1),temps.get(0)));
                        break;
                    case '*':
                        values.add(new Op_Mult(temps.get(1),temps.get(0)));
                        break;
                    case '/':
                        values.add(new Op_Div(temps.get(1),temps.get(0)));
                        break;
                    case '^':
                        values.add(new Op_Pow(temps.get(1),temps.get(0)));
                        break;
                }
                temps.clear();
            }
        }
        return values.get(0);
    }
}
