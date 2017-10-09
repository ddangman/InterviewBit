/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.dynamicprogramming;

/**
 *
 * @author Duy Dang
 */
public class InterleavingStrings {

    public int isInterleave(String A, String B, String C) {
        int Al = A.length();
        int Bl = B.length();
        int Cl = C.length();
        if (Cl != Al + Bl) { // lengths do not add up correctly
            return 0;
        }

        boolean[] dp = new boolean[Bl + 1];
        dp[0] = true; // "" + "" = ""
        for (int Bi = 0; Bi < Bl; Bi++) {
            // 0th row: with A == "", B interleaves C. 
            // Compare B & C at same index. Previous index must also be true
            dp[Bi + 1] = dp[Bi] && B.charAt(Bi) == C.charAt(Bi);
        }

        // if char match, check for valid memoization without char
        for (int Ai = 0; Ai < Al; Ai++) {
            // 0th column: with B == "", A interleaves C. 
            // Compare A & C at same index. Previous index must also be true          
            dp[0] = dp[0] && A.charAt(Ai) == C.charAt(Ai);
            for (int Bi = 0; Bi < Bl; Bi++) {
                // +2 since a & b starts at index 0, offset to actual position.
                // -1 to go from c position to c.index. +2 - 1 = +1
                int ci = Ai + Bi + 1;
                if (dp[Bi + 1] && A.charAt(Ai) == C.charAt(ci)) {
                    continue;
                }
                if (B.charAt(Bi) == C.charAt(ci)) {
                    dp[Bi + 1] = dp[Bi];
                    continue;
                }
                dp[Bi + 1] = false;
            }
        }
        return dp[Bl] ? 1 : 0;
    }

    // dynamic programming time and space: O(A.length * B.length)
    // string C is interleaved string of A & B
    public boolean isInterleaved(char[] A, char[] B, char[] C) {
        // A runs horizontally, B runs vertically
        boolean T[][] = new boolean[A.length + 1][B.length + 1];

        // string C can be an interleaving of string A and string B only if 
        // sum of lengths A & B is equal to length of C
        if (A.length + B.length != C.length) {
            return false;
        }

        // a: position of stringA, b: position of stringB
        for (int a = 0; a < T.length; a++) { //fill matrix topRow down 
            for (int b = 0; b < T[a].length; b++) { //fill matrix column left2right
                int c = a + b - 1; //c: position on C
                // two empty strings have an empty string as interleaving
                if (a == 0 && b == 0) {
                    T[a][b] = true;
                } // A is empty
                else if (a == 0) {
                    if (C[c] == B[b - 1]) {
                        T[a][b] = T[a][b - 1];
                    }
                } // B (column) is empty
                else if (b == 0) {
                    if (A[a - 1] == C[c]) {
                        T[a][b] = T[a - 1][b];
                    }
                } else {
                    // charA matches charC ? check if previous charB is valid ||
                    // charB matches charC ? check if previous charA is valid
                    T[a][b] = (A[a - 1] == C[c] ? T[a - 1][b] : false)
                            || (B[b - 1] == C[c] ? T[a][b - 1] : false);
                }
            }
        }
        return T[A.length][B.length];
    }
}
