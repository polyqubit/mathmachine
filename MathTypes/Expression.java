package MathTypes;

public class Expression {
    private MathObject top; // outermost layer of Expression

    public Expression() {
        // def =
    }

    public Expression(MathObject m) {
        top = m;
    }

    public MathObject head() {
        return top;
    }

    public double value() {
        return top.value();
    }
}
// idea : make recursive definition