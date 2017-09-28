/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.dynamicprogramming;

/**
 *
 * @author Dang
 */
public class ScrambleString {

    public int isScrambleIt(final String s1, final String s2) {
        if (s1.length() != s2.length()) {
            return 0;
        }
        int length = s1.length();
        	/**
		 * Let F(i, j, k) = whether the substring S1[i..i + k - 1] is a scramble of S2[j..j + k - 1] or not
		 * Since each of these substrings is a potential node in the tree, we need to check for all possible cuts.
		 * Let q be the length of a cut (hence, q < k), then we are in the following situation:
		 * 
		 * S1 [   x1    |         x2         ]
		 *    i         i + q                i + k - 1
		 * 
		 * here we have two possibilities:
		 *      
		 * S2 [   y1    |         y2         ]
		 *    j         j + q                j + k - 1
		 *    
		 * or 
		 * 
		 * S2 [       y1        |     y2     ]
		 *    j                 j + k - q    j + k - 1
		 * 
		 * which in terms of F means:
		 * 
		 * F(i, j, k) = for some 1 <= q < k we have:
		 *  (F(i, j, q) AND F(i + q, j + q, k - q)) OR (F(i, j + k - q, q) AND F(i + q, j, k - q))
		 *  
		 * Base case is k = 1, where we simply need to check for S1[i] and S2[j] to be equal 
		 **/
        boolean[][][] F = new boolean[length][length][length + 1];
        for (int subL = 1; subL <= length; ++subL) {
            for (int i = 0; i + subL <= length; ++i) {
                for (int j = 0; j + subL <= length; ++j) {
                    if (subL == 1) {
                        F[i][j][subL] = s1.charAt(i) == s2.charAt(j);
                    } else {
                        for (int cut = 1; cut < subL && !F[i][j][subL]; ++cut) {
                            F[i][j][subL] = (F[i][j][cut] && F[i + cut][j + cut][subL - cut]) || 
                                    (F[i][j + subL - cut][cut] && F[i + cut][j][subL - cut]);
                        }
                    }
                }
            }
        }
        return F[0][0][length] ? 1 : 0;
    }
    
    // recursive solution
    public int isScrambleRec(final String s1, final String s2) {
        if (s1.length() != s2.length()) { // length test
            return 0;
        }
        if (s1.equals(s2) || isMirrored(s1, s2)) { // test match or mirrored match
            return 1;
        }

        if (!isCharacterHashEqual(s1, s2)) { // match character hash
            return 0;
        }

        int n = s1.length();
        for (int i = 1; i < n; i++) {
            String s1start = s1.substring(0, i);
            String s1end = s1.substring(i);
            String s2start = s2.substring(0, i);
            String s2end = s2.substring(i);
            String s2flipstart = s2.substring(0, n - i);
            String s2flipend = s2.substring(n - i);

            // test unflipped substring for match
            if (isScrambleRec(s1start, s2start) == 1
                    && isScrambleRec(s1end, s2end) == 1) {
                return 1;
            }
            // test flipped substring for match
            if (isScrambleRec(s1start, s2flipend) == 1
                    && isScrambleRec(s1end, s2flipstart) == 1) {
                return 1;
            }
        }

        return 0;
    }

    private boolean isMirrored(String s1, String s2) {
        int n = s1.length();
        for (int i = 0, j = n - 1; i < n;) {
            if (s1.charAt(i++) != s2.charAt(j--)) {
                return false;
            }
        }
        return true;
    }

    private boolean isCharacterHashEqual(String s1, String s2) {
        long hash1 = 1;
        long hash2 = 1;
        int n = s1.length();
        for (int i = 0; i < n; i++) {
            hash1 *= s1.charAt(i);
            hash2 *= s2.charAt(i);
        }

        return hash1 == hash2;
    }       
    

}
