/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.math;

/**
 *
 * @author Dang
 */
public class ReverseInteger {

    public int reverse(int n) {
        boolean negative = false;
        if (n < 0) {
            negative = true;
            n*=-1;
        }        

        long res = 0;
        while (n > 0) {
            res*=10;
            long mod = n % 10;
            res+=mod;
            n/=10;
        }
        if (negative) {
            res *= -1;
        } 
        // check for overflow or underflow
        if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE) {
            return 0;
        }        
        return (int) res;
    }

}
