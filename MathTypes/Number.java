package MathTypes;

public class Number implements MathObject {
    private double n;

    // TODO: number standard object, integer/real/complex etc descendants
    // also reconsider Literal

    // Warning: do not use default for Number
    public Number() {
        n = 0;
    }

    public Number(double d) {
        n = d;
    }

    public void setval(double d) {
        n = d;
    }

    public double value() {
        return n;
    }

    public String type() {
        return "Number";
    }

    public String name() {
        return "Number";  // remember to extend to integer, real, complex etc
    }

    public boolean equals(MathObject m) { // for simplification purposes, not evaluative
        if(m.type()==this.type()) {
            return this.n == ((Number)m).value();
        }
        return false;
    }
}