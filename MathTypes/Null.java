package MathTypes;

public class Null implements MathObject {
    public double value() { // maybe throws exception?
        return 0;
    }

    public MathObject parameter1() {
        return null;
    }
    public MathObject parameter2() {
        return null;
    }

    public String type() {
        return "Null";
    }

    public boolean equals(MathObject m) {
        return m.type() == this.type();
    }
}
