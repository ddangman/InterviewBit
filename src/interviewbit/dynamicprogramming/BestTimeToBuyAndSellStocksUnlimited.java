/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.dynamicprogramming;

import java.util.List;

/**
 *
 * @author Dang
 */
public class BestTimeToBuyAndSellStocksUnlimited {

    public int maxProfit(final List<Integer> a) {
        int n = a.size();
        if (n <= 1) {
            return 0;
        }        
        int[] profit = new int[n + 1];
        int balance = -a.get(0); // balance is initially cost of day zero
        for (int i = 1; i <= n; i++) {
            int price = a.get(i - 1);
            
            // best profit is either from no transaction (take prior day's profit)
            // or sum of balance and selling price
            profit[i] = Math.max(profit[i - 1], balance + price);
            
            // check if removing day's price from profit is better than balance
            balance = Math.max(balance, profit[i] - price); 
        }

        return profit[n];
    }
}
