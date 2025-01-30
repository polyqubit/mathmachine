package MathTypes;

public class Number implements MathObject {
    private double n;

    // Warning: do not use default for Number
    public Number() {
        n = 0;
    }

    public Number(double d) {
        n = d;
    }

    public double value() {
        return n;
    }

    // should not be used
    public MathObject parameter1() {
        return new Null();
    }
    public MathObject parameter2() {
        return new Null();
    }

    public String type() {
        return "Number"; // remember to extend to integer, real, complex etc
    }
}
