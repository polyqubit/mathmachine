package MathTypes;

public class Variable implements MathObject {
    private String token;

    // Warning: do not use default for Number
    public Variable() {
        token = "x";
    }

    public Variable(String s) {
        token = s;
    }

    public double value() {
        return 0;
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