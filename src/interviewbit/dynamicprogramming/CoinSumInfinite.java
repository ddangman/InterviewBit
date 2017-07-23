/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.dynamicprogramming;

import java.util.ArrayList;

/**
 *
 * @author Dang
 */
public class CoinSumInfinite {

    public int coinchange2(ArrayList<Integer> coins, int total) {
        final int mod = 1000007;
        long[] combo = new long[total + 1]; // stores combination for total == index
        combo[0] = 1; // only one way to make zero
        
        for (int i = 0; i < coins.size(); i++) {
            int value = coins.get(i);
            for (int t = 1; t <= total; t++) {
                // given infinite coins: each total where coin value can be used
                // adds another combination equal to current total - coin value
                if (value <= t) {
                    combo[t] += combo[t - value];
                    combo[t] %= mod;
                }                
            }
        }
        
        return (int) combo[total];
    }
}
