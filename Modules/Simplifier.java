package Modules;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import MathTypes.*;

public class Simplifier {
    private MathObject top;
    private ArrayList<Rule> ruleset;

    public Simplifier() {
        ruleset = new ArrayList<>();
    }

    public void target(Expression e) {
        top = e.head();
    }

    public void read(String path) throws FileNotFoundException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        try {
            br.readLine();
        }
        catch(Exception e) {}

    }

    public void simplify(int max) {
        boolean applied = true;
        int c = 0;
        MathObject m;
        while(applied) {
            applied = false;
            for(Rule r : ruleset) {
                m = top;
                top = r.apply(top);
                applied = !m.equals(top);
            }
            c++;
            if(c>max) {
                System.out.println("Exceeded limit of " + max + " steps");
                return;
            }
        }
    }
}
