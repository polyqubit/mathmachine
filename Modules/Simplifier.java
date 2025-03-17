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

    public void targetobj(MathObject e) {
        top = e;
    }

    public void read(String path) throws FileNotFoundException {
        try(BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = "";
            line = br.readLine();
            while(line != null) {
                if ((line.length()>0)&&(line.charAt(0)=='?')) {
                    line = br.readLine();
                    System.out.println("\nSkipped line");
                    continue;
                }
                System.out.println("Reading: " + line);
                Rule r = new Rule(line.substring(0,line.indexOf("~")),
                                  line.substring(1+line.indexOf("~")));
                ruleset.add(r);
                line = br.readLine();
            }
        }
        catch(Exception e) {
            System.err.println("error: " + e);
        }
        System.out.println("Rule count: " + ruleset.size());
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
                r.apply(top);
                applied = !m.equals(top);
            }
            c++;
            if(c>max) {
                System.out.println("Exceeded limit of " + max + " steps");
                return;
            }
            System.out.println("Step " + c);
        }
    }
}
