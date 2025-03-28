package Modules;

import MathTypes.*;

public class MathPrinter {
    private static void p(String s) { System.out.print(s); }
    public static void print(MathObject m) {
        traverse(m);
    }
    private static void traverse(MathObject m) {
        switch(m.type()) {
            case "Variable":
                p("["+m.name()+"]");
                break;
            case "Number":
                p(m.value()+"");
                break;
            case "Any":
                p("#" + m.name());
                break;
            default: // assume some sort of function(+,-,cos etc)
                if(m.type().equals("Expression")) {
                    Expression e = (Expression)m;
                    p(e.name());
                    p("(");
                    traverse(e.getexpr());
                    p(")");
                }
                if(m.type().equals("Operator")) {
                    Operator o = (Operator)m;
                    p(o.name());
                    p("(");
                    traverse(o.parameter1()); // will need to extend to array of parameters for function
                    p(", ");
                    traverse(o.parameter2());
                    p(")");
                }
                break;
        }
    }
}