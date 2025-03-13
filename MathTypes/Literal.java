package MathTypes;

// for rule searching a specific number
public class Literal implements MathObject {
    private double n;

    // Warning: do not use default for Literal
    public Literal() {
        n = 0;
    }

    public Literal(double d) {
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
        return "Number";
    }

    public String name() {
        return "Literal";  // remember to extend to integer, real, complex etc
    }

    public boolean equals(MathObject m) { // for simplification purposes, not evaluative
        if(m.type()==this.type()) {
            return this.n == ((Number)m).value();
        }
        return false;
    }
}