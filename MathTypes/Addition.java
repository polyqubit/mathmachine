package MathTypes;

public class Addition implements MathObject {
    private MathObject store1;
    private MathObject store2;

    // should not be used
    public Addition() {
        store1 = new Null();
        store2 = new Null();
    }

    public Addition(MathObject a, MathObject b) {
        store1 = a;
        store2 = b;
    }

    public Addition(double a, double b) {
        store1 = new Number(a);
        store2 = new Number(b);
    }

    public double value() {
        return store1.value() + store2.value();
    }

    public MathObject parameter1() {
        return store1;
    }

    public MathObject parameter2() {
        return store2;
    }

    public String type() {
        return "Addition";
    }

    public boolean equals(MathObject m) {
        if(m.type()==this.type()) {
            return this.store1.equals(m.parameter1())
                && this.store2.equals(m.parameter2());
        }
        return false;
    }
}
