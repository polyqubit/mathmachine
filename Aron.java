import java.util.*;

public class Aron {
    public static void main(String args[]) {

        System.out.println("ok input your weirdo equation thingy: ");
        Scanner sc = new Scanner(System.in);

        String in = sc.nextLine();
        Split ohio = new Split();

        ArrayList<String> switx = new ArrayList<>();
        switx = ohio.splitString(in);

        for (int i = 0; i < switx.size(); i++) {
            System.out.println(switx[i]);
        }
    }
}