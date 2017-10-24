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
    public int isMatchDP(final String s, final String p) {
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

    /**
     * '?' matches any single character '*' matches any sequence of characters
     * (including empty sequences) Check if string matches pattern in linear
     * runtime and constant space using 2-pointers
     *
     * @param s input string
     * @param p pattern to match
     */
    public int isMatchLinear(final String s, final String p) {
        int sl = s.length(); // string length
        int pl = p.length(); // pattern length
        int si = 0; // string index
        int pi = 0; // pattern index
        int rs = 0; // reverts string index back to last match
        int rp = -1; // reverts pattern index back to start of '*'

        while (si < sl) { // traverse string
            if (pi < pl) { // compare pattern character while still in scope
                char pc = p.charAt(pi); // pattern character
                if (pc == '?' || pc == s.charAt(si)) {
                    // single character match, advance both indices
                    pi++;
                    si++;
                    continue;
                } else if (pc == '*') { // save index to revert if needed
                    rp = pi++; // increase pattern index
                    rs = si;
                    continue;
                }
            }
            if (rp != -1) { // revert back to last '*' indices
                pi = rp + 1; // pattern index is immediately after '*'
                // advance revert string index since '*' can hold any string 
                // character combination
                si = rs++;
            } else {
                return 0; // no possible match
            }
        }

        while (pi < pl) { // rest of pattern must be empty '*'
            if (p.charAt(pi++) != '*') {
                return 0; // character found in pattern not in string
            }
        }

        return 1; // valid match
    }

    /**
     * '.'  matches any single character
     * '*'  matches zero or more of the preceding element
     * '.*' matches any sequence of characters (including empty sequences)
     * Dynamic programming implementation using O(p) space
     *
     * @param s input string
     * @param p pattern to match
     */
    public int rexMatchDP(final String s, final String p) {
        int sl = s.length(); // string length
        int pl = p.length(); // pattern length
        
        // initialize memoization
        boolean[] dp = new boolean[pl + 1];
        dp[0] = true; // empty string matches empty pattern
        for (int pi = 2; pi <= pl; pi++) { // '*' must follow another char. start at 2
            if (p.charAt(pi - 1) == '*') {
                dp[pi] = dp[pi - 2]; // copy value before '*'
            }            
        }
        
        for (int si = 1; si <= sl; si++) {
            boolean one = false; // new value at one index prior
            for (int pi = 1; pi <= pl; pi++) {
                boolean now = false; // new value being calculated now
                char pc = p.charAt(pi - 1); // pattern character
                char sc = s.charAt(si - 1); // string character
                
                if (pc == '.' || pc == sc) { // single match
                    now = dp[pi - 1]; // copy value excluding char
                } else if (pc == '*') {
                    now = dp[pi - 2]; // copy value of no occurances before '*'
                    char wild = p.charAt(pi - 2); // character before '*'
                    if (wild == '.' || wild == sc) {
                        now = now | dp[pi]; // bitwise OR valid wild match
                    }                    
                }                
               
                // transition from old array to new array
                dp[pi - 1] = one;
                one = now;
            }
            dp[pl] = one; // now was copied to one before leaving scope
        }
        
        return dp[pl] ? 1 : 0;
    }

}
