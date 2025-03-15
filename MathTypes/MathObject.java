package MathTypes;

public interface MathObject {
    public abstract double value(); // for expressions with only constants

    public abstract String type();

    public abstract String name();

    public abstract boolean equals(MathObject m);
}