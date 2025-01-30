package MathTypes;

public class Multiplication implements MathObject {
    private MathObject store1;
    private MathObject store2;

    // should not be used
    public Multiplication() {
        store1 = new Null();
        store2 = new Null();
    }

    public Multiplication(MathObject a, MathObject b) {
        store1 = a;
        store2 = b;
    }

    public Multiplication(double a, double b) {
        store1 = new Number(a);
        store2 = new Number(b);
    }

    public double value() {
        return store1.value() * store2.value();
    }

    public MathObject parameter1() {
        return store1;
    }
    public MathObject parameter2() {
        return store2;
    }

    public String type() {
        return "Multiplication";
    }
}
