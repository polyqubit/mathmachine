import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

import MathTypes.*;
import MathTypes.Number;
import Modules.Split;

public class Test {
    static void p(String s) {
        System.out.print(s);
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            // Simplifier s = new Simplifier();
            // s.target(null);

            // Expression e = new Expression(new Subtraction(new Number(1),new
            // Addition(9,3))); // 1-(9+3)
            // System.out.println(e.value());
            // e = new Expression(new Division(new Multiplication(3,1),new
            // Multiplication(3,20))); // (3*1)/(3*20)
            // System.out.println(e.value());
            p("\ninfix->postfix example\n");
            p("functions are denoted as _func()\n");
            p("expressions such as 2n or 2(n) are not valid, do 2*n or 2*(n) instead\n");
            p("expressions such as -n or n! are not valid, do _neg(n) or _factorial(n) instead\n");
            p("mismatched parentheses and operators are not handled, please confirm validity of infix string\n");
            p("enter infix expression:\n");
            parse(sc.nextLine());
        }
    }

    static ArrayList<String> parse(String s) {
        ArrayList<String> tokens = new ArrayList<>();
        ArrayList<String> output = new ArrayList<>();
        Stack<String> op = new Stack<>();
        tokens = Split.splitString(s);
        // for(String ss : tokens) {
        // p(ss + " ");
        // }
        // p("\n");
        for (int i = 0; i < tokens.size(); i++) {
            switch (tokens.get(i)) {
                case "+": // precedence 2, left
                case "-": // precedence 2, left
                    if (!op.empty()) {
                        while (!op.peek().equals("(")) {
                            output.add(op.pop());
                            if (op.empty()) {
                                break;
                            }
                        }
                    }
                    op.add(tokens.get(i));
                    break;
                case "*": // precedence 3, left
                case "/": // precedence 3, left
                    if (!op.empty()) {
                        while (!op.peek().equals("(")
                                && !op.peek().equals("+")
                                && !op.peek().equals("-")) {
                            output.add(op.pop());
                            if (op.empty()) {
                                break;
                            }
                        }
                    }
                    op.add(tokens.get(i));
                    break;
                case "^": // precedence 4, right
                    if (!op.empty()) {
                        while (!op.peek().equals("(")
                                && !op.peek().equals("+")
                                && !op.peek().equals("-")
                                && !op.peek().equals("*")
                                && !op.peek().equals("/")
                                && !op.peek().equals("^")) {
                            output.add(op.pop());
                            if (op.empty()) {
                                break;
                            }
                        }
                    }
                    op.add(tokens.get(i));
                    break;
                case "(":
                    op.push("(");
                    break;
                case ")": // assume this is never a first token
                    while (!op.peek().equals("(")) {
                        output.add(op.pop());
                    }
                    op.pop(); // remove "("
                    if (!op.empty()) {
                        if (op.peek().charAt(0) == '_') { // function
                            output.add(op.pop());
                        }
                    }
                    break;
                case ",": // for functions
                    if (!op.empty()) {
                        while (!op.peek().equals("(")) {
                            output.add(op.pop());
                            if (op.empty()) {
                                break;
                            }
                        }
                    }
                    break;
                case "_": // function
                    op.push("_" + tokens.get(i + 1));
                    i++;
                    break;
                case ".": // decimal
                    output.set(output.size() - 1, output.get(output.size() - 1) + "." + tokens.get(i + 1));
                    i++;
                    break;
                // assumed to be number/variable
                default:
                    if (!Character.isLetterOrDigit(tokens.get(i).charAt(0))) {
                        System.out.println("Invalid symbol \"" + tokens.get(i) + "\"");
                        return null;
                    }
                    output.add(tokens.get(i));
                    break;
            }
        }
        while (!op.empty()) {
            output.add(op.pop());
        }
        for (String ss : output) {
            System.out.print(ss + " ");
        }
        convert(output);
        return output;
    }
    
    static MathObject convert(ArrayList<String> in) {
        // ArrayList<MathObject> temp = new ArrayList<>();
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
                    for(int k=0;k<funcs.get(in.get(i));k++) {
                        temps.add(values.pop());
                    }
                    switch(in.get(i)) {
                        case "neg":
                            values.add(new Subtraction(new Number(0),temps.get(0)));
                            break;
                    }
                }
            }
        }
        return new Null();
    }
}

// idea for simplifier : pattern matching
// https://stackoverflow.com/questions/7540227/strategies-for-simplifying-math-expressions