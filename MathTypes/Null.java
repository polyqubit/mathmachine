package MathTypes;

public class Null implements MathObject {
    public double value() { // maybe throws exception?
        return 0;
    }

    public String type() {
        return "Null";
    }

    public String name() {
        return "Null";
    }

    public boolean equals(MathObject m) {
        return m.type().equals(this.type());
    }
}
