package MathTypes;

public class Variable implements MathObject {
    private String token;
    private double val;
    private boolean assigned;

    // Warning: do not use default
    public Variable() {
        token = "x";
        val = 0;
        assigned = false;
    }

    public Variable(String s) {
        token = s;
        val = 0;
        assigned = false;
    }

    public Variable(String s, double d) {
        token = s;
        val = d;
        assigned = true;
    }

    public void assign(double d) {
        val = d;
        assigned = true;
    }

    public boolean assigned() {
        return assigned;
    }

    public double value() {
        return val;
    }

    public String token() {
        return token;
    }

    public void settoken(String s) {
        token = s;
    }
    
    public String type() {
        return "Variable"; // remember to extend to integer, real, complex etc
    }

    public String name() {
        return token;
    }

    public boolean equals(MathObject m) { // for simplification purposes, not evaluative
        if(m.type()==this.type()) {
            return this.token == ((Variable)m).name();
        }
        return false;
    }
}