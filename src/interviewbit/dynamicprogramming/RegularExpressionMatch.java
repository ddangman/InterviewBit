/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.dynamicprogramming;

/**
 *
 * @author Duy Dang
 */
public class RegularExpressionMatch {

    // '?' matches any single character
    // '*' matches any sequence of characters (including empty sequences)
    public int isMatch(final String s, final String p) {
        int sl = s.length();
        int pl = p.length();
        boolean[] valid = new boolean[pl + 1];
        valid[0] = true; // empty string matches empty pattern
        for (int pi = 0; pi < pl; pi++) { // '*' prefix is always true
            if (p.charAt(pi) == '*') {
                valid[pi + 1] = valid[pi];
            } else {
                break;
            }
        }

        //            [pi-1][pi ][old array]
        // [new array][prev][cur]
        for (int si = 0; si < sl; si++) {
            boolean prev = false; // 0th col. Empty pattern never matches non-empty string
            for (int pi = 0; pi < pl; pi++) {
                // cur will be placed at valid[pi + 1] after scope passes 2 iterations
                boolean cur = false; // arbitrary initialization
                char pc = p.charAt(pi);
                if (pc == s.charAt(si) || pc == '?') {
                    cur = valid[pi];
                } else if (pc == '*') {
                    cur = valid[pi + 1] || valid[pi] || prev;
                }                
                valid[pi] = prev;
                prev = cur; // 'cur' will become 'prev' on next iteration
            }
            valid[pl] = prev;
        }
        return valid[pl] ? 1 : 0;
    }
}
