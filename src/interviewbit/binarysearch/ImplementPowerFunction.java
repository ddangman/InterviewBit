/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.binarysearch;

import java.math.BigInteger;

/**
 *
 * @author Dang
 */
public class ImplementPowerFunction {

    public static int pow(int x, int n, int d) {
        BigInteger mod = BigInteger.valueOf(x)
                .modPow(BigInteger.valueOf(n), BigInteger.valueOf(d));
        int res = mod.intValue();
        if (res >= 0) {
            return res;
        }
        return res + d;
    }
}
