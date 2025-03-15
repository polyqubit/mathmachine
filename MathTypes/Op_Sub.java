package MathTypes;

public class Op_Sub extends Operator {
    private MathObject store1;
    private MathObject store2;

    // should not be used
    public Op_Sub() {
        store1 = new Null();
        store2 = new Null();
    }

    public Op_Sub(MathObject a, MathObject b) {
        store1 = a;
        store2 = b;
    }

    public Op_Sub(double a, double b) {
        store1 = new Number(a);
        store2 = new Number(b);
    }

    public double value() {
        return store1.value() - store2.value();
    }

    public MathObject parameter1() {
        return store1;
    }
    public MathObject parameter2() {
        return store2;
    }

    public String type() {
        return "Operator";
    }

    public String name() {
        return "Sub";
    }
}
