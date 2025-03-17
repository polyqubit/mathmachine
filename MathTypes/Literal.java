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

    public String type() {
        return "Number";
    }

    public String name() {
        return "Literal";  // remember to extend to integer, real, complex etc
    }

    public boolean equals(MathObject m) { // for simplification purposes, not evaluative
        // System.out.println("(lit)This obj: " + this.name());
        // System.out.println("(lit)That obj: " + m.name());
        if(m.type().equals(this.type())) {
            return this.n == ((Num)m).value();
        }
        return false;
    }
}