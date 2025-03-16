package inheritance;
public class C extends B {
    private int k;
    public C() {
        k = 2;
    }
    public void p(String s) {
        System.out.print(s+" CCC");
    }
    public void pk() {
        System.out.println(k);
    }
}
