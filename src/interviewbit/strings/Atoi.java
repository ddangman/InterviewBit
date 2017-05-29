/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.strings;

/**
 *
 * @author Dang
 */
public class Atoi {

    public int atoi(final String a) {
        boolean isNegative = false;
        int s = -1;
        int e = a.length();

        // get start index
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) == '-') {
                s = i;
                isNegative = true;

                // check if next char is digit
                if (i + 1 == a.length()) {
                    return 0;
                }
                if (!Character.isDigit(a.charAt(i + 1))) {
                    return 0;
                }
                break;
            } else if (a.charAt(i) == '+') {
                // check if next char is digit
                if (i + 1 == a.length()) {
                    return 0;
                }
                if (!Character.isDigit(a.charAt(i + 1))) {
                    return 0;
                }
            } else if (a.charAt(i) != ' ') {
                s = i;
                break;
            }
        }

        // get end index
        for (int i = isNegative ? s + 1 : s; i < a.length(); i++) {
            if (!Character.isDigit(a.charAt(i))) {
                e = i;
                break;
            }
        }

        // no numberic values found
        if (s == -1 || s == e) {
            return 0;
        }

        // check if exceeds int limits
        String num = a.substring(s, e);
        if (isNegative) {
            if (exceedMin(num)) {
                return Integer.MIN_VALUE;
            } else {
                return Integer.parseInt(num);
            }
        } else {
            if (exceedMax(num)) {
                return Integer.MAX_VALUE;
            } else {
                return Integer.parseInt(num);
            }
        }
    }

    public boolean exceedMax(String num) {
        if (num.length() > 10) {
            return true;
        }
        Long n = Long.parseLong(num);
        if (n > Integer.MAX_VALUE) {
            return true;
        }
        return false;
    }

    public boolean exceedMin(String num) {
        if (num.length() > 11) {
            return true;
        }
        Long n = Long.parseLong(num);
        if (n < Integer.MIN_VALUE) {
            return true;
        }
        return false;
    }
}
