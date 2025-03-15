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

    public void target(MathObject e) {
        top = e;
    }

    public void read(String path) throws FileNotFoundException {
        try(BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = "";
            line = br.readLine();
            while(line != null) {
                Rule r = new Rule(line.substring(0,line.indexOf("~")),
                                  line.substring(line.indexOf("~")));
                ruleset.add(r);
                line = br.readLine();
            }
        }
        catch(Exception e) {
            System.err.println("error: " + e);
        }
    }

    // TODO: store previously used Rule to prevent immediate application of its inverse; implement inverses
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
