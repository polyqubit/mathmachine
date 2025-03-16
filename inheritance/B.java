package inheritance;
public class B implements A {
    private int k;
    public B() {
        k = 1;
    }
    public void p(String s) {
        System.out.print(s+" B");
    }
    public void pk() {
        System.out.println(k);
    }
}
