package MathTypes;

public class Op_Mult extends Operator {
    private MathObject store1;
    private MathObject store2;

    public Op_Mult(MathObject a, MathObject b) {
        store1 = a;
        store2 = b;
        check();
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

    public void setP1(MathObject m) {
        store1 = m;
    }
    public void setP2(MathObject m) {
        store1 = m;
    }

    private void check() {
        if(!store1.type().equals("Number")
        && store2.type().equals("Number")) {
            MathObject temp = store1;
            store1 = store2;
            store2 = temp;
        }
    }

    public String type() {
        return "Operator";
    }
    
    public String name() {
        return "Mult";
    }
}
