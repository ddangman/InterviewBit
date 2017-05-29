/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.strings;

/**
 *
 * @author Dang
 */
public class ReverseTheString {

    public String reverseWords(String a) {
        StringBuilder sb = new StringBuilder();
        boolean parsingWord = false;
        boolean first = true;
        int end = -1;
        for (int i = a.length() - 1; i >= 0; i--) {
            if (parsingWord) {
                if (a.charAt(i) != ' ') {
                    continue;
                } else {
                    if (!first) {
                        sb.append(' ');
                    }
                    sb.append(a.substring(i + 1, end));
                    first = false;
                    parsingWord = false;
                }
            } else { // parsing space
                if (a.charAt(i) == ' ') {
                    continue;
                } else {
                    parsingWord = true;
                    end = i + 1;
                }
            }
        }
        if (parsingWord) {
            if (!first) {
                sb.append(' ');
            }
            sb.append(a.substring(0, end));
        }
        return sb.toString();
    }
}
