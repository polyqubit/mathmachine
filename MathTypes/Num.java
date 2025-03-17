package MathTypes;

public class Num implements MathObject {
    private double n;

    // TODO: number standard object, integer/real/complex etc descendants
    // also reconsider Literal

    // Warning: do not use default for Number
    public Num() {
        n = 0;
    }

    public Num(double d) {
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
        System.out.println("(num)This obj: " + this.name());
        System.out.println("(num)That obj: " + m.name());
        if(m.type().equals(this.type())) {
            return this.n == ((Num)m).value();
        }
        return false;
    }
}