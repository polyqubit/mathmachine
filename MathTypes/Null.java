package MathTypes;

public class Null implements MathObject {
    public String type() {
        return "Null";
    }

    public double value() { // maybe throws exception?
        return 0;
    }
}
