/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.dynamicprogramming;

/**
 *
 * @author Duy Dang
 */
public class EditDistance {

    // uses array space
    //            [Bi-1][Bi ][old array]
    // [new array][prev][cur]
    public int minDistanceArray(String A, String B) {
        int Al = A.length();
        int Bl = B.length();
        int[] memo = new int[Bl + 1];

        // initialize the memo
        for (int Bi = 1; Bi <= Bl; Bi++) {
            // cost for converting from "" to B.substring(Bi)
            memo[Bi] = Bi;
        }

        // converting stringA to stringB
        // memo[Bi - 1] == dp[ai - 1][bi - 1], replace charAi with charBi
        // memo[Bi] == dp[ai - 1][bi], insert charAi
        // prev = dp[ai][bi - 1], remove charBi
        for (int Ai = 1; Ai <= Al; Ai++) {
            int prev = Ai; // changes needed to make substingA(0, Ai) from charB(0). 0th col in matrix
            for (int Bi = 1; Bi <= Bl; Bi++) {
                int cur = 0; // arbitrary initialization
                if (A.charAt(Ai - 1) == B.charAt(Bi - 1)) {
                    cur = memo[Bi - 1]; // if chars are equal, no cost for converting
                } else {
                    // min{replace charAi with charBi, add charAi, remove charBi}
                    cur = 1 + Math.min(memo[Bi - 1], Math.min(memo[Bi], prev));
                }
                memo[Bi - 1] = prev; // update dp[ai - 1][bi - 1] to current row dp[ai][bi - 1]
                prev = cur; // update dp[ai][bi - 1] to current calculation before bi++
            }
            memo[Bl] = prev; // holds 'cur' value, no longer in scope
        }
        return memo[Bl];
    }

    public int minDistanceMatrix(String a, String b) {
        int al = a.length(); // shorter string
        int bl = b.length(); // longer string
        int[][] dp = new int[al][bl];
        boolean found = true;
        if (a.charAt(0) != b.charAt(0)) {
            dp[0][0] = 1;
            found = false;
        }
        for (int ai = 1; ai < al; ai++) {
            if (!found && a.charAt(ai) == b.charAt(0)) {
                found = true;
                dp[ai][0] = dp[ai - 1][0];
            } else {
                dp[ai][0] = dp[ai - 1][0] + 1;
            }
        }
        found = dp[0][0] == 0 ? true : false;
        for (int bi = 1; bi < bl; bi++) {
            if (!found && a.charAt(0) == b.charAt(bi)) {
                found = true;
                dp[0][bi] = dp[0][bi - 1];
            } else {
                dp[0][bi] = dp[0][bi - 1] + 1;
            }
        }

        // converting stringA to stringB
        for (int ai = 1; ai < al; ai++) {
            for (int bi = 1; bi < bl; bi++) {
                if (a.charAt(ai) == b.charAt(bi)) {
                    dp[ai][bi] = dp[ai - 1][bi - 1]; // no change
                } else {
                    int replace = dp[ai - 1][bi - 1] + 1; // replace charAi with charBi
                    // min{insert charAi, remove charBi}
                    int inout = Math.min(dp[ai - 1][bi], dp[ai][bi - 1]) + 1;
                    dp[ai][bi] = Math.min(replace, inout);
                }
            }
        }

        return dp[al - 1][bl - 1];
    }
}
