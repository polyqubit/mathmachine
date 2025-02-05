package Modules;
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

            // if c is not an operator, it must be a variable or a number (or a user mistake)
            // StringBuilder sb = new StringBuilder();
            // do {
            //     sb.append(input.charAt(i));
            //     i++;
            // } while((Character.isDigit(input.charAt(i)) || Character.isLetter(input.charAt(i))) && (i<input.length()));
            // terms.add(sb.toString());
        }

        return terms;
    }

}
