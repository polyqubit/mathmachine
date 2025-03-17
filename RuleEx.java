import java.io.FileNotFoundException;
import java.util.Scanner;

import MathTypes.*;
import MathTypes.Number; // required to avoid ambiguity with java.Number
import Modules.MathPrinter;
import Modules.Simplifier;
import Modules.Split;

public class RuleEx {
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
            MathObject m = Split.convert(sc.nextLine());
            // m.print();
            MathPrinter.print(m);
            Simplifier sm = new Simplifier();
            sm.targetobj(m);
            try {
                sm.read("ruleset.txt");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            sm.simplify(10);
            MathPrinter.print(m);
        }
    }
}

// idea for simplifier : pattern matching
// https://stackoverflow.com/questions/7540227/strategies-for-simplifying-math-expressions