import inheritance.A;
import inheritance.B;
import inheritance.C;

public class classing {
    static void p(String s) {
        System.out.print(s);
    }

    public static void main(String[] args) {
        A example = new C();
        B ex = (B)example;
        // example.p("null");
        // ex.p("\nhi");  // even though example was cast as B, it still retains the C version of p()
        ((B)example).pk();
        ex.pk();          // even though example was cast as B, it still retains the C version of p()
    }
}

// idea for simplifier : pattern matching
// https://stackoverflow.com/questions/7540227/strategies-for-simplifying-math-expressions