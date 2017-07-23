/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 *
 * @author Dang
 */
public class DistributeCandy {

    public int candy(ArrayList<Integer> ratings) {
        int n = ratings.size();
        int[] give = new int[n];

        // give more than previous
        give[0] = 1;
        for (int i = 1; i < n; i++) {
            if (ratings.get(i) > ratings.get(i - 1)) {
                give[i] = give[i - 1] + 1;
            } else {
                give[i] = 1;
            }
        }

        // give more than next
        for (int i = n - 2; i >= 0; i--) {
            if (ratings.get(i) > ratings.get(i + 1)) {
                give[i] = Math.max(give[i + 1] + 1, give[i]);
            }
        }

        return Arrays.stream(give).sum();
    }

    // optimized solution: one pass and constant space
    public int candyOnce(ArrayList<Integer> ratings) {
        int deslen = 0; //descending length
        int prev = 1;
        int total = 1;

        for (int i = 1; i < ratings.size(); i++) {
            // rating is greater than the previous one
            // give (previous + 1) candies.
            if (ratings.get(i) >= ratings.get(i - 1)) {
                // calculate descending candies
                if (deslen > 0) {
                    // summation formula
                    total += deslen * (deslen + 1) / 2;

                    // check if previous is at least the size of descending length
                    if (deslen >= prev) {
                        // add difference between descending length and previous
                        // so they are same height, plus one so previous is taller
                        total += deslen - prev + 1;
                    }

                    // reset values
                    deslen = 0;
                    prev = 1;
                }
                
                // give more candy if rating is greater than previous
                // when equals to previous one, set to 1. Else set to prev + 1
                prev = Objects.equals(ratings.get(i), ratings.get(i - 1)) ? 1 : prev + 1;               
                total += prev;
            } else {
                deslen++;
            }
        }

        // check if decending in the end
        if (deslen > 0) {
            total += deslen * (deslen + 1) / 2;
            if (deslen >= prev) {
                total += deslen - prev + 1;
            }
        }

        return total;
    }
}
