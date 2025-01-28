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

    public String type() {
        return "Number"; // remember to extend to integer, real, complex etc
    }
}
