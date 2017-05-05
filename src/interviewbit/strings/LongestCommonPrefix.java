/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.strings;

import java.util.ArrayList;

/**
 *
 * @author Dang
 */
public class LongestCommonPrefix {

    public String longestCommonPrefix(ArrayList<String> a) {
        if (a.size() == 1) {
            return a.get(0);
        }

        // get length of shortest string
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i).length() < min) {
                min = a.get(i).length();
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < min; i++) {
            for (int j = 1; j < a.size(); j++) {
                if (a.get(j).charAt(i) != a.get(0).charAt(i)) {
                    break;
                }
                if (j + 1 == a.size()) {
                    sb.append(a.get(0).charAt(i));
                }
            }

        }
        return sb.toString();
    }
}
