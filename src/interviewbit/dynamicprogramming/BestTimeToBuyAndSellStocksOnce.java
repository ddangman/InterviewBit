/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.dynamicprogramming;

import java.util.List;

/**
 *
 * @author Dang
 */
public class BestTimeToBuyAndSellStocksOnce {

    public int maxProfit(final List<Integer> a) {
        if (a.isEmpty()) {
            return 0;
        }
        int n = a.size();
        int min = a.get(0);
        int best = 0;

        for (int i = 1; i < n; i++) {
            int today = a.get(i) - min;
            if (today > best) {
                best = today;
            }
            if (a.get(i) < min) {
                min = a.get(i);
            }
        }

        return best;
    }
}
