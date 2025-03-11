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
            default: // assume some sort of function(+,-,cos etc)
                if(m.type()=="Operator") {
                    p(m.name());
                    p("(");
                    traverse(m.parameter1()); // will need to extend to array of parameters for function
                    p(", ");
                    traverse(m.parameter2());
                    p(")");
                }
                break;
        }
    }
}