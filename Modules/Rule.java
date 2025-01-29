package Modules;
import MathTypes.*;

public class Rule {
    private MathObject search;
    // x+1 -> Addition(x,1)
    public Rule(String s) {
        search = conversion(s);
    }
    private MathObject conversion(String s) {
        return new Null();
    }
}
