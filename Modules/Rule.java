package Modules;

import java.util.ArrayList;

import MathTypes.*;

public class Rule {
    private MathObject search;
    private MathObject replace;

    // x+1 -> Addition(x,1)
    public Rule(String s, String r) {
        search = conversion(s);
        replace = conversion(r);
    }

    // if this does not work, consider Object.equals recursive approach
    public MathObject apply(MathObject m) {
        if(traverse(m, search)) {
            return replace;
        }
        return m;
    }

    // current target layer, current search layer
    private boolean traverse(MathObject cur, MathObject s) {
        if(!(cur.type()==s.type())) {
            return false;
        }
        if(cur.type() == "Number") { // also variable, also functionality for both literal and expression Number search
            return s.type() == "Number"; // eg. false if any number except 1
        }
        boolean b1 = traverse(cur.parameter1(), s.parameter1());
        boolean b2 = traverse(cur.parameter2(), s.parameter2());
        return (b1&&b2);
    }

    private MathObject conversion(String s) {
        ArrayList<String> tokens = new ArrayList<>();
        tokens = Split.splitString(s);
        for(int i=0;i<tokens.size();i++) {
            switch(tokens.get(i)) {
                case "+":

                    break;
                case "-":

                    break;
                case "*":

                    break;
                case "/":

                    break;
                case "?":

                    break;
            }
        }
        return new Null();
    }
}
