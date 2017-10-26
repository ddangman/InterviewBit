/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.strings;

import java.util.ArrayList;

/**
 *
 * @author Dang
 */
public class PrettyJson {

    private int tabs = 1;

    public ArrayList<String> prettyJSON(String a) {
        ArrayList<String> json = new ArrayList<>();

        int start = 1;
        int end = 1;

        // processing logic
        json.add(String.valueOf(a.charAt(0)));
        while (end < a.length()) {
            StringBuilder sb = tabBuilder();
            switch (a.charAt(end)) {
                case '{':
                case '[':
                    // prevent blank lines
                    if (start != end) {
                        // save valid line before opening bracket
                        sb.append(a.substring(start, end));
                        json.add(sb.toString());
                    }

                    StringBuilder sb2 = tabBuilder();
                    sb2.append(a.charAt(end++));
                    json.add(sb2.toString());
                    start = end;
                    tabs++;
                    break;
                case '}':
                case ']':
                    tabs--;
                    // prevent blank lines
                    if (start != end) {
                        // save valid line before opening bracket
                        sb.append(a.substring(start, end));
                        json.add(sb.toString());
                        start = end;
                    }

                    StringBuilder sb3 = tabBuilder();
                    // check if next char is comma, include it
                    if (end + 1 < a.length() && a.charAt(end + 1) == ',') {
                        end++;
                    }                    
                    sb3.append(a.substring(start, ++end));
                    json.add(sb3.toString());
                    start = end;
                    break;
                case ',':
                    sb.append(a.substring(start, ++end));
                    json.add(sb.toString());
                    start = end;
                    break;
                default:
                    end++;
            }
        }
        return json;
    }

    private StringBuilder tabBuilder() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tabs; i++) {
            sb.append("\t");
        }
        return sb;
    }

}
