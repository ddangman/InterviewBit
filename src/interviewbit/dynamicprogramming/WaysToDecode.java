/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.dynamicprogramming;

/**
 *
 * @author Dang
 */
public class WaysToDecode {
    
    public int numDecodings(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        int x = Character.getNumericValue(s.charAt(n - 1));
        dp[n] = 1;
        dp[n - 1] = x != 0 ? 1 : 0;
        
        for (int i = n - 2; i >= 0; i--) {
            x = Character.getNumericValue(s.charAt(i));
            if (x != 0) {
                int y = Integer.parseInt(s.substring(i, i + 2));
                dp[i] = y <= 26 ? dp[i + 1] + dp[i + 2] : dp[i + 1];
            }            
        }
        
        return dp[0];
    }

}
