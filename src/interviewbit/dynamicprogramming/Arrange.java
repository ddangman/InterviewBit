/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.dynamicprogramming;

/**
 *
 * @author Duy Dang
 */
public class Arrange {

    /**
     * You fill the horses into the stables preserving the relative order of horses. 
     * For instance, you cannot put horse 1 into stable 2 and horse 2 into stable 1. 
     * You have to preserve the ordering of the horses.
     * No stable should be empty and no horse should be left unaccommodated.
     * Take the product (number of white horses * number of black horses) for each 
     * stable and take the sum of all these products. This value should be the 
     * minimum among all possible accommodation arrangements
     */
    public int arrange(String horse, int stable) {
        int hl = horse.length();
        if (stable == 0 || stable > hl) {
            return -1;
        }
        
        // each cell represent minimum cost of (h-horses in s-stable)
        int[][] dp = new int[hl + 1][stable + 1];
        int[] w = new int[hl + 1];
        int[] b = new int[hl + 1];
        for (int h = 1; h <= hl; h++) {
            if (horse.charAt(h - 1) == 'W') {
                w[h] = w[h - 1] + 1;
                b[h] = b[h - 1];
            } else {
                w[h] = w[h - 1];
                b[h] = b[h - 1] + 1;
            }           
            dp[h][1] = w[h] * b[h];
        }
        
        for (int h = 1; h <= hl; h++) {
            for (int s = 2; s <= stable; s++) {
                dp[h][s] = Integer.MAX_VALUE;
                for (int k = 1; k <= h; k++) {
                    int now = (w[h] - w[k]) * (b[h] - b[k]) + dp[k][s - 1];
                    if (now < dp[h][s]) {
                        dp[h][s] = now;
                    }                    
                }
            }
        }
        
        return dp[hl][stable];
    }
}
