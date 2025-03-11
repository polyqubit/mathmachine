package Modules;
import java.util.*;

public class Split {
    public static ArrayList<String> parse(String s, boolean print) {
        ArrayList<String> tokens = new ArrayList<>();
        ArrayList<String> output = new ArrayList<>();
        Stack<String> op = new Stack<>();
        tokens = Split.splitString(s);
        // for(String ss : tokens) {
        // p(ss + " ");
        // }
        // p("\n");
        for (int i = 0; i < tokens.size(); i++) {
            switch (tokens.get(i)) {
                case "+": // precedence 2, left
                case "-": // precedence 2, left
                    if (!op.empty()) {
                        while (!op.peek().equals("(")) {
                            output.add(op.pop());
                            if (op.empty()) {
                                break;
                            }
                        }
                    }
                    op.add(tokens.get(i));
                    break;
                case "*": // precedence 3, left
                case "/": // precedence 3, left
                    if (!op.empty()) {
                        while (!op.peek().equals("(")
                                && !op.peek().equals("+")
                                && !op.peek().equals("-")) {
                            output.add(op.pop());
                            if (op.empty()) {
                                break;
                            }
                        }
                    }
                    op.add(tokens.get(i));
                    break;
                case "^": // precedence 4, right
                    if (!op.empty()) {
                        while (!op.peek().equals("(")
                                && !op.peek().equals("+")
                                && !op.peek().equals("-")
                                && !op.peek().equals("*")
                                && !op.peek().equals("/")
                                && !op.peek().equals("^")) {
                            output.add(op.pop());
                            if (op.empty()) {
                                break;
                            }
                        }
                    }
                    op.add(tokens.get(i));
                    break;
                case "(":
                    op.push("(");
                    break;
                case ")": // assume this is never a first token
                    while (!op.peek().equals("(")) {
                        output.add(op.pop());
                    }
                    op.pop(); // remove "("
                    if (!op.empty()) {
                        if (op.peek().charAt(0) == '_') { // function
                            output.add(op.pop());
                        }
                    }
                    break;
                case ",": // for functions
                    if (!op.empty()) {
                        while (!op.peek().equals("(")) {
                            output.add(op.pop());
                            if (op.empty()) {
                                break;
                            }
                        }
                    }
                    break;
                case "_": // function
                    op.push("_" + tokens.get(i + 1));
                    i++;
                    break;
                case ".": // decimal
                    output.set(output.size() - 1, output.get(output.size() - 1) + "." + tokens.get(i + 1));
                    i++;
                    break;
                // assumed to be number/variable
                default:
                    if (!Character.isLetterOrDigit(tokens.get(i).charAt(0))) {
                        System.out.println("Invalid symbol \"" + tokens.get(i) + "\"");
                        return null;
                    }
                    output.add(tokens.get(i));
                    break;
            }
        }
        while (!op.empty()) {
            output.add(op.pop());
        }
        if(print) {
            for (String ss : output) {
                System.out.print(ss + " ");
            }
        }
        return output;
    }

    private static ArrayList<String> splitString(String input) {
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

            // sb(skyblock) is an easy method for combining characters
            StringBuilder sb = new StringBuilder();
            while(true) {
                sb.append(input.charAt(i));
                if(!Character.isLetterOrDigit(input.charAt(i))) {
                    // this happens if current digit is an operator(+,- etc)
                    // no special case for functions because the parser handles _ + name
                    break;
                }
                if(i<input.length()-1){
                    if(!Character.isLetterOrDigit(input.charAt(i+1))) {
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
