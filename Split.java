import java.util.*;

public class Split {

    public static ArrayList<String> splitString(String input) {
        ArrayList<String> terms = new ArrayList<>();
        int start = 0;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (Character.isDigit(c) || c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')' || c == '{'
                    || c == '}' || c == '^' || i == input.length() - 1) {
                int endIndex = i;
                if (i == input.length() - 1) {
                    endIndex++;
                }
                String term = input.substring(start, endIndex);
                terms.add(term);
                start = i + 1;
            }
        }

        return terms;
    }

}
