package MathTypes;

public interface MathObject {
    public abstract double value(); // for expressions with only constants

    public abstract MathObject parameter1();

    public abstract MathObject parameter2();

    public abstract String type();
}