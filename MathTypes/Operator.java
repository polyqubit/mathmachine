package MathTypes;

public class Operator implements MathObject {
    private MathObject store1;
    private MathObject store2;

    // should not be used
    public Operator() {
        store1 = new Null();
        store2 = new Null();
    }

    public Operator(MathObject a, MathObject b) {
        store1 = a;
        store2 = b;
        check();
    }

    // should not be used
    public double value() {
        return 0;
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
        return "Operator";
    }

    public boolean equals(MathObject m) {
        System.out.println("(op)This obj: " + this.type());
        System.out.println("(op)That obj: " + m.type());
        if(m.type().equals(this.type())) {
            Operator o = (Operator)m;
            return this.store1.equals(o.parameter1())
                && this.store2.equals(o.parameter2());
        }
        return false;
    }
}
