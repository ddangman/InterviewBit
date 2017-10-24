/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.util.ArrayList;

/**
 *
 * @author Dang
 */
public class Sandbox {

    public void run() {
        
    }
    
        /**
     * Dynamic programming technique for regex matching. bottom up solution
     * Space complexity: O(patternLength * stringLength) Time complexity:
     * O(patternLength * stringLength) since each cell is written once
     */
    public int matchRegex(final String text, final String pattern) {
        // booleanMatrix[textRow][patternColumn]
        boolean T[][] = new boolean[text.length() + 1][pattern.length() + 1];

        T[0][0] = true; // 0 text matches 0 pattern
        //Deals with patterns like a* or a*b* or a*b*c*
        for (int i = 1; i < T[0].length; i++) {
            if (pattern.charAt(i - 1) == '*') {
                T[0][i] = T[0][i - 2];
            }
        }

        // since matrix[t][p] starts at 1, adjust matrix index by -1
        for (int t = 1; t < T.length; t++) {
            for (int p = 1; p < T[0].length; p++) {
                // pattern'.' always true || valid char match
                if (pattern.charAt(p - 1) == '.' || 
                        pattern.charAt(p - 1) == text.charAt(t - 1)) {
                    // subtract matching char, check if all prior matches
                    T[t][p] = T[t - 1][p - 1];
                } else if (pattern.charAt(p - 1) == '*') {
                    T[t][p] = T[t][p - 2]; // 0 occurances of char before *
                    // '.*' allows any combination of 0+ characters
                    // || textChar == patternCharBefore*
                    if (pattern.charAt(p - 2) == '.' || 
                            pattern.charAt(p - 2) == text.charAt(t - 1)) {
                        // skip current textChar by copying result of prior textChar
                        // OR keep true from T[t][p-2]
                        T[t][p] = T[t][p] | T[t - 1][p]; //bitwise OR assignment
                    }
                } else {
                    T[t][p] = false;
                }
            }
        }
        return T[text.length()][pattern.length()] ? 1 : 0;
    }
}
