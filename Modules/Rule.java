package Modules;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import MathTypes.*;
import MathTypes.Num;

public class Rule {
    private MathObject search;
    private MathObject replace;
    private Queue<MathObject> tempnums;
    private HashMap<String, MathObject> mathmap; // used to store any mathobject for replacement of Any in pattern
    private HashMap<String, String> varmap; // sorted search, target

    // x+1 -> Addition(x,1)
    public Rule(String s, String r) {
        search = Split.convert(s);
        replace = Split.convert(r);
        tempnums = new LinkedList<>();
        mathmap = new HashMap<>();
        varmap = new HashMap<>();
        // .offer() adds, .poll() removes
    }

    // if this does not work, consider Object.equals recursive approach
    public void apply(MathObject m) {
        if (recursiveapply(m)) {
            System.out.print("Starting rule subtraverse for "); MathPrinter.print(search); System.out.println();
            substitute(m);
        }
    }

    // need an outer traverse layer
    private boolean recursiveapply(MathObject m) {
        if(m.type().equals("Number") || m.type().equals("Variable")) {
            System.out.println("reached end of target");
            return false;
        } 
        if(traverse(m, search)) {
            return true;
        }
        if(m.type().equals("Operator")) {
            return recursiveapply(((Operator)m).parameter1()) || recursiveapply(((Operator)m).parameter2());
        }
        if(m.type().equals("Expression")) {
            return recursiveapply(((Expression)m).getexpr());
        }
        return recursiveapply(m);
    }

    // current target layer, current search(as in this.search) layer
    private boolean traverse(MathObject cur, MathObject s) {
        if (s.type().equals("Any")) {
            System.out.println("success - found wildcard: " + cur.name());
            mathmap.put(s.name(), cur);
            return true;
        }

        // Literal only appears in Rules; must be checked here
        if (s.name().equals("Literal") && cur.name().equals("Number")) { // searching for a specific number
            if (s.value() != cur.value()) {
                System.out.println("failed - wrong literal");
                return false;
            }
            System.out.println("success - correct literal");
            return true;
        }

        if (!(s.name().equals(cur.name()))) {
            System.out.println("failed - name difference: " + s.name() + " " + cur.name());
            return false;
        }

        if (s.name().equals("Variable")) {
            if (!varmap.containsKey(s.name()) && !varmap.containsValue(cur.name())) {
                varmap.put(s.name(), cur.name());
                System.out.println("success - new var pair");
                return true;
            }
            if (cur.name().equals(varmap.get(s.name()))) {
                System.out.println("success - found var pair");
                return true;
            }
            System.out.println("failed - mismatched var pair");
            return false;
        }

        else if (s.name().equals("Number")) { // searching for any number
            tempnums.offer(cur);
            System.out.println("success - new num");
            return true;
        }

        // else if(cur.name().equals(Number")) { // also variable, also functionality for
        // both literal and expression Number search
        // return s.name().equals("Number"); // eg. false if any number except 1
        // }

        if (cur.type().equals("Operator")) {
            Operator ocur = (Operator) cur;
            Operator os = (Operator) s;
            boolean b1 = traverse(ocur.parameter1(), os.parameter1());
            boolean b2 = traverse(ocur.parameter2(), os.parameter2());
            return (b1 && b2);
        }

        if (cur.type().equals("Expression")) {
            Expression ecur = (Expression) cur;
            Expression es = (Expression) s;
            return traverse(ecur.getexpr(), es.getexpr());
        }
        // TODO: need case for function
        System.out.println("failure - no cases matched");
        return false;
    }

    private int count(MathObject m) {
        if(m.type().equals("Number")||m.type().equals("Variable")) {
            return 1;
        }
        if(m.type().equals("Operator")) {
            Operator o = (Operator)m;
            return count(o.parameter1()) + count(o.parameter2()) + 1;
        }
        if(m.type().equals("Expression")) {
            Expression e = (Expression)m;
            return count(e.getexpr()); // no need to count expr
        }
        return 0;
    }

    // substitute traverse
    // current target layer, current replacement layer
    private void subtraverse(MathObject cur, MathObject r) {
        if (r.name().equals("Number")) {
            System.out.println(" - setting num " + ((Num)cur).value());
            ((Num)cur).setval(tempnums.poll().value());
            return;
        }

        if (r.name().equals("Variable")) {
            System.out.println(" - setting var " + cur.name());
            ((Variable)cur).settoken(varmap.get(((Variable)r).token()));
            return;
        }

        // else if(cur.name().equals("Number")) { // also variable, also functionality for
        // both literal and expression Number search
        // return s.name().equals("Number"); // eg. false if any number except 1
        // }

        if (cur.type().equals("Expression")) {
            Expression ecur = (Expression) cur;
            Expression er = (Expression) r;
            if(er.getexpr().type().equals("Any")) {
                System.out.print(" - setting expr "); MathPrinter.print(mathmap.get(er.getexpr().name())); System.out.println();
                ecur.setexpr(mathmap.get(er.getexpr().name()));
                // System.out.println(mathmap.get(er.name()).name());
                return;
            }
            subtraverse(ecur.getexpr(), er.getexpr());
        }

        if (cur.type().equals("Operator")) {
            Operator ocur = (Operator) cur;
            Operator or = (Operator) r;
            boolean skip1 = false;
            boolean skip2 = false;
            if(or.parameter1().type().equals("Any")) {
                System.out.print(" - setting expr "); MathPrinter.print(mathmap.get(or.parameter1().name())); System.out.println();
                ocur.setP1(mathmap.get(or.parameter1().name()));
                skip1 = true;
            }
            if(or.parameter2().type().equals("Any")) {
                ocur.setP2(mathmap.get(or.parameter2().name()));
                System.out.print(" - setting expr "); MathPrinter.print(mathmap.get(or.parameter2().name())); System.out.println();
                skip2 = true;
            }
            // if(skip) {
            //     System.out.println(" - completed operator, returning");
            //     return;
            // }
            if(!skip1){subtraverse(ocur.parameter1(), or.parameter1());}
            if(!skip2){subtraverse(ocur.parameter2(), or.parameter2());}
        }
    }

    // need some way to change the target without accessing the parent
    // likely need to switch to c++
    private void subrecursiveapply(MathObject m) {
        if(m.type().equals("Operator")) {
            Operator o = (Operator)m;
            if(traverse(o.parameter1(), search)) {
                subtraverse(m, replace);
            }
            subrecursiveapply(((Operator)m).parameter1());
            subrecursiveapply(((Operator)m).parameter2());
        }
        if(m.type().equals("Expression")) {
            subrecursiveapply(((Expression)m).getexpr());
        }
    }

    // Number -> Literal(order Numbers in rule description)
    // _N(0) + _N(1) = _N(_N(1) + _N(2)) ?
    // _L(0) + _N(0) =
    private void substitute(MathObject m) {
        subrecursiveapply(m);
    }
}
