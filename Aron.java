import java.util.*;

import Modules.Split;

public class Aron {
    public static void main(String args[]) {

        System.out.println("ok input your weirdo equation thingy: ");
        try (Scanner sc = new Scanner(System.in)) {
            String in = sc.nextLine();

            ArrayList<String> switx = new ArrayList<>();
            // switx = ohio.splitString(in);
            switx = Split.parse(in, true);

            for (int i = 0; i < switx.size(); i++) {
                System.out.println(switx.get(i));
            }
        }
    }
}