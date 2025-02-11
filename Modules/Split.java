package Modules;
import java.util.*;

public class Split {

    public static ArrayList<String> splitString(String input) {
        ArrayList<String> terms = new ArrayList<>();
        // int start = 0;
        input = input.replaceAll(" ", "");

        for (int i = 0; i < input.length(); i++) {
            // char c = input.charAt(i);

            // if (Character.isDigit(c) || c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')' || c == '{'
            //         || c == '}' || c == '^' || i == input.length() - 1) {
            //     int endIndex = i;
            //     if (i == input.length() - 1) {
            //         endIndex++;
            //     }
            //     String term = input.substring(start, endIndex);
            //     terms.add(term);
            //     start = i + 1;
            // }

            // sb(skyblock) is an easy(to read) method for combining characters
            StringBuilder sb = new StringBuilder();
            while(true) {
                sb.append(input.charAt(i));
                if(!(Character.isDigit(input.charAt(i)) || Character.isLetter(input.charAt(i)))) {
                    // this happens if current digit is an operator(+,- etc)
                    break;
                }
                if(i<input.length()-1){
                    if(!(Character.isDigit(input.charAt(i+1)) || Character.isLetter(input.charAt(i+1)))) {
                        // this happens if next digit is an operator(+,- etc)
                        break;
                    }
                }
                else{ break; }
                i++;

                // if c is not an operator, it must be a variable or a number (or a user mistake)
            }
            // } while((Character.isDigit(input.charAt(i-1)) || Character.isLetter(input.charAt(i-1))) && (i<input.length()));
            terms.add(sb.toString());
        }
// 11+1
        return terms;
    }

}
