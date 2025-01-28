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

    public String type() {
        return "Addition";
    }
}
