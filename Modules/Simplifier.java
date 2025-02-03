package Modules;

import java.util.ArrayList;

import MathTypes.*;

public class Simplifier {
    private MathObject top;
    private ArrayList<Rule> ruleset;

    public Simplifier() {
        ruleset = new ArrayList<>();
    }

    public void target(MathObject m) {
        top = m;
    }

    public void simplify() {
        boolean e = true;
        while(e) {
            e = false;
            for(Rule r : ruleset) {
                
            }
        }
    }
}
