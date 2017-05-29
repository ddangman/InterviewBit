/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.bitmanipulation;

import java.util.List;

/**
 *
 * @author Dang
 */
public class SingleNumber {

    // find the number that occurs only once when all others have 2 copies
    public int singleNumberPairs(final List<Integer> a) {
        int xor = a.get(0);
        for (int i = 1; i < a.size(); i++) {
            xor ^= a.get(i);
        }
        return xor;
    }

    // find the number that occurs only once when all others have 3 copies
    public int singleNumberTriple(final List<Integer> a) {
        int result = 0;
        int x, sum, y;

        // iterate through every bit
        for (int i = 0; i < Integer.SIZE; i++) {
            // find sum of set bits at ith position in all array elements
            sum = 0;
            x = 1 << i;
            for (int j = 0; j < a.size(); j++) {
                y = x & a.get(j);
                if (y != 0) {
                    sum++;
                }
            }

            // The bits with sum not multiple of 3, are the
            // bits of element with single occurrence.
            if (sum % 3 != 0) {
                result |= x;
            }            
        }
        
        return result;
    }
}
