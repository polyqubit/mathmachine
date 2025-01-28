import MathTypes.*;
import MathTypes.Number;

public class Test {
    public static void main(String[] args) {
        Expression e = new Expression(new Subtraction(new Number(1),new Addition(9,3))); // 1-(9+3)
        System.out.println(e.value());
        e = new Expression(new Division(new Multiplication(3,1),new Multiplication(3,20))); // (3*1)/(3*20)
        System.out.println(e.value());
    }
}