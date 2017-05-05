/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.strings;

/**
 *
 * @author Dang
 */
public class MultiplyStrings {

    public String multiply(String s1, String s2) {
        // edge case zero
        if (s2.charAt(0) == '0' && s2.length() == 1) {
            return "0";
        }
        if (s1.charAt(0) == '0' && s1.length() == 1) {
            return "0";
        }

        // copy string to int arrays
        int[] top;
        int[] bottom;
        if (s1.length() > s2.length()) {
            top = new int[s1.length()];
            bottom = new int[s2.length()];
            for (int i = 0; i < s1.length(); i++) {
                top[i] = Character.getNumericValue(s1.charAt(i));
            }
            for (int i = 0; i < s2.length(); i++) {
                bottom[i] = Character.getNumericValue(s2.charAt(i));
            }
        } else {
            bottom = new int[s1.length()];
            top = new int[s2.length()];
            for (int i = 0; i < s1.length(); i++) {
                bottom[i] = Character.getNumericValue(s1.charAt(i));
            }
            for (int i = 0; i < s2.length(); i++) {
                top[i] = Character.getNumericValue(s2.charAt(i));
            }
        }

        // utility arrays
        int[] carry = new int[top.length + 1];
        int[] answer = new int[top.length * 2];

        // multiplication logic
        for (int b = bottom.length - 1; b >= 0; b--) {
            for (int t = top.length - 1; t >= 0; t--) {
                int n = bottom[b] * top[t];
                n += answer[t + b + 1] + carry[t + 1];
                answer[t + b + 1] = n % 10;
                carry[t] = n / 10;

                if (t == 0) {
                    int m = answer[t + b] + carry[t];
                    answer[t + b] = m % 10;
                }
            }
        }

        // trim leading zeros from answer
        int lead = 0;
        while (answer[lead] == 0) {
            lead++;
        }
        // generate and return String answer
        StringBuilder sb = new StringBuilder();
        while (lead < top.length + bottom.length) {
            sb.append(answer[lead++]);
        }
        return sb.toString();
    }
}
