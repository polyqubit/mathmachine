package MathTypes;

// expression form of variable
public class Any implements MathObject {
    private String token;

    // Warning: do not use default
    public Any() {
        token = "x";
    }

    public Any(String s) {
        token = s;
    }

    public double value() {
        return 0;
    }

    public String token() {
        return token;
    }

    public void settoken(String s) {
        token = s;
    }
    
    public String type() {
        return "Any";
    }

    public String name() {
        return token;
    }

    public boolean equals(MathObject m) { // for simplification purposes, not evaluative
        // System.out.println("(var)This obj: " + this.name());
        // System.out.println("(var)That obj: " + m.name());
        if(m.type().equals(this.type())) {
            return this.token.equals(((Any)m).name());
        }
        return false;
    }
}