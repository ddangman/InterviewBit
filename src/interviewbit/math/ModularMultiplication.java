/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.math;

/**
 *
 * @author Dang
 */
public class ModularMultiplication {

    public long modularMultiplication(long a, long b, long mod) {
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
