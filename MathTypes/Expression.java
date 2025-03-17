package MathTypes;

// top level container in case the entire expression needs to change
public class Expression implements MathObject {
    private MathObject store;

    // should not be used
    public Expression() {
        // System.out.println("Bad constructor used!");
        store = new Num();
    }

    public Expression(MathObject a) {
        store = a;
    }

    // should not be used
    public double value() {
        return 0;
    }

    public MathObject getexpr() {
        return store;
    }

    public void setexpr(MathObject m) {
        store = m;
    }

    public String type() {
        return "Expression";
    }
    
    public String name() {
        return "Expression";
    }

    public boolean equals(MathObject m) {
        // System.out.println("(op)This obj: " + this.name());
        // System.out.println("(op)That obj: " + m.name());
        if(m.type().equals(this.type())) {
            // System.out.println("tried recursion on: " + parameter1().type() + ", " + parameter2().type());
            Expression e = (Expression)m;
            return getexpr().equals(e.getexpr());
        }
        return false;
    }
}
