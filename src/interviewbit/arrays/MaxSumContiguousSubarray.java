/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.arrays;

import java.util.List;

/**
 *
 * @author Dang
 */
public class MaxSumContiguousSubarray {
    	public int maxSubArray(final List<Integer> a) {
            int maxAll = Integer.MIN_VALUE;
            int run = 0;
            for (int i = 0; i < a.size(); i++) {
                run += a.get(i);
                if (run > maxAll) {
                    maxAll = run;
                }                
                if (run < 0 ) {
                    run = 0;
                }                
            }
            return maxAll;
	}
}
