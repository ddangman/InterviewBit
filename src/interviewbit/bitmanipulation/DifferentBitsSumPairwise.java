/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.bitmanipulation;

import java.util.ArrayList;

/**
 *
 * @author Dang
 */
public class DifferentBitsSumPairwise {

    public int cntBits(ArrayList<Integer> A) {
        long res = 0;

        // traverse over all bits
        for (int i = 0; i < 32; i++) {
            // count number of elements with ith bit set
            int count = 0;
            for (int j : A) {
                int isSet = j & (1 << i);
                if (isSet != 0) {
                    count++;
                }
            }
            // (A.size - count) are numbers with ith bit clear
            res += modularMultiplication(count, (A.size() - count) * 2, 1000000007);
            if (res > 1000000007) {
                res %= 1000000007;
            }            
        }
        return (int) res % 1000000007;
    }
    
    private long modularMultiplication(long a, long b, long mod) {
        long res = 0;
        a %= mod;
        while (b > 0) {
            // if b is odd, add a to result
            if (b % 2 == 1) {
                res = (res + a) % mod;
            }            
            
            // multiply 'a' with 2
            a = (a * 2) % mod;
            
            // divide 'b' by 2
            b /= 2;
        }
        
        return res % mod;
    }
}
