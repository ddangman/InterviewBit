/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.dynamicprogramming;

import java.util.List;

/**
 *
 * @author Duy Dang
 */
public class BestTimeToBuyAndSellStocksTwice {

    public int maxProfit(final List<Integer> prices) {
        int buy1 = Integer.MIN_VALUE, buy2 = Integer.MIN_VALUE;
        int sold1 = 0, sold2 = 0;                // Assume we only have 0 money at first
        for (int i : prices) {                     
            sold2 = Math.max(sold2, buy2 + i);   // The maximum if we've just sold 2nd stock so far.
            buy2 = Math.max(buy2, sold1 - i);    // The maximum if we've just buy  2nd stock so far.
            sold1 = Math.max(sold1, buy1 + i);   // The maximum if we've just sold 1nd stock so far.
            buy1 = Math.max(buy1, -i);           // The maximum if we've just buy  1st stock so far. 
        }
        return sold2; ///Since sold1 is initiated as 0, so sold2 will always higher than sold1.
    }
}
