package MathTypes;

public class Variable implements MathObject {
    private String token;
    private double val;

    // Warning: do not use default
    public Variable() {
        token = "x";
        val = 0;
    }

    public Variable(String s, double d) {
        token = s;
        val = d;
    }

    public double value() {
        return val;
    }

    public String name() {
        return token;
    }

    // should not be used
    public MathObject parameter1() {
        return new Null();
    }
    public MathObject parameter2() {
        return new Null();
    }

    public String type() {
        return "Variable"; // remember to extend to integer, real, complex etc
    }

    public boolean equals(MathObject m) { // for simplification purposes, not evaluative
        if(m.type()==this.type()) {
            return this.token == ((Variable)m).name();
        }
        return false;
    }
}