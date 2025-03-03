import java.util.ArrayList;
import java.util.Stack;

import MathTypes.*;
import MathTypes.Number;
import Modules.Simplifier;
import Modules.Split;

public class Test {
    public static void main(String[] args) {
        // Simplifier s = new Simplifier();
        // s.target(null);

        // Expression e = new Expression(new Subtraction(new Number(1),new Addition(9,3))); // 1-(9+3)
        // System.out.println(e.value());
        // e = new Expression(new Division(new Multiplication(3,1),new Multiplication(3,20))); // (3*1)/(3*20)
        // System.out.println(e.value());

        conversion("1+1");
    }

    static MathObject conversion(String s) {
        ArrayList<String> tokens = new ArrayList<>();
        ArrayList<String> output = new ArrayList<>();
        Stack<String> op = new Stack<>();
        tokens = Split.splitString(s);
        for(int i=0;i<tokens.size();i++) {
            switch(tokens.get(i)) {
                case "+": // precedence 2, left
                case "-": // precedence 2, left
                    if(!op.empty()) {
                        while(!op.peek().equals("(")) {
                                output.add(op.pop());
                                if(op.empty()) { break; } // breaks out of all?
                        }
                    }
                    op.add(tokens.get(i));
                    break;
                case "*": // precedence 3, left
                case "/": // precedence 3, left
                    if(!op.empty()) {
                        while(!op.peek().equals("(")
                            &&!op.peek().equals("+")
                            &&!op.peek().equals("-")) {
                                output.add(op.pop());
                                if(op.empty()) { break; }
                        }
                    }
                    op.add(tokens.get(i));
                    break;
                case "^": // precedence 4, right
                    if(!op.empty()) {
                        while(!op.peek().equals("(")
                            &&!op.peek().equals("+")
                            &&!op.peek().equals("-")
                            &&!op.peek().equals("*")
                            &&!op.peek().equals("/")
                            &&!op.peek().equals("^")) {
                                output.add(op.pop());
                                if(op.empty()) { break; }
                        }
                    }
                    op.add(tokens.get(i));
                    break;
                case "(":
                    op.push("(");
                    break;
                case ")": // assume this is never a first token
                    while(!op.peek().equals("(")) {
                        output.add(op.pop());
                    }
                    op.pop(); // remove "("
                    if(op.peek().charAt(0)=='_') { // function
                        op.pop();
                    }
                    break;
                case "_": // function
                    op.push("_" + tokens.get(i+1));
                    i++;
                    break;
                // assumed to be number/variable
                default:
                    output.add(tokens.get(i));
                    break;
            }
        }
        while(!op.empty()) {
            output.add(op.pop());
        }
        for(String ss : output) {
            System.out.print(ss+" ");
        }
        return new Null();
    }
}

// idea for simplifier : pattern matching
// https://stackoverflow.com/questions/7540227/strategies-for-simplifying-math-expressions