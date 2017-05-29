/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.strings;

/**
 *
 * @author Dang
 */
public class CountAndSay {

    public String countAndSay(int a) {
        if (a <= 0) {
            return null;
        }
        String result = "1";
        for (int i = 1; i < a; i++) {
            int count = 1;
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < result.length(); j++) {
                if (j + 1 < result.length() && result.charAt(j + 1) == result.charAt(j)) {
                    count++;
                } else {
                    sb.append(count);
                    sb.append(result.charAt(j));
                    count = 1;
                }
            }
            result = sb.toString();
        }
        return result;
    }
}
