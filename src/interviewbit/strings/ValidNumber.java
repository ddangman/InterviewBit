/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.strings;

/**
 *
 * @author Dang
 */
public class ValidNumber {

    public int isNumberMessy(final String a) {
        String regex = "(-|\\+)?[0-9]*\\.?[0-9]+e?(-|\\+)?[0-9]+";
        String regex2 = "[ \\t\\n\\x0B\\f\\r]*(-|\\+)?[0-9]*\\.?[0-9]+[ \\t\\n\\x0B\\f\\r]*";
        if (a.matches(regex) || a.matches(regex2)) {
            return 1;
        } else {
            return 0;
        }
    }

    public int isNumber(final String a) {
        return (a.matches("\\s*[\\-\\+]?([0-9]*\\.?)?[0-9]+(e[\\-\\+]?[0-9]+)?\\s*")) ? 1 : 0;
    }
}
