public class Addition extends MathObject {
    private MathObject store1;
    private MathObject store2;
    public Addition() {

    }
    public Addition(MathObject a, MathObject b) {
        store1 = a;
        store2 = b;
    }
    @Override
    public String type() {
        return "Addition";
    }
}
