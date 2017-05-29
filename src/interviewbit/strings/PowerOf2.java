/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.strings;

import java.math.BigInteger;

/**
 *
 * @author Dang
 */
public class PowerOf2 {

    public int powerBigInteger(String a) {

        if (a.length() == 1 && a.charAt(0) == '1') {
            return 0;
        }
        BigInteger bi = new BigInteger(a);
        if (isPowerOfTwo(bi)) {
            return 1;
        }
        return 0;
    }

    /**
     * Returns {@code true} if {@code x} represents a power of two.
     */
    public static boolean isPowerOfTwo(BigInteger x) {
        return x.signum() > 0 && x.getLowestSetBit() == x.bitLength() - 1;
    }

    public int isPowerOf2(String A) {

        String dividend = A;
        StringBuilder str;

        if (A == null || A.length() == 0) {
            return 0;
        }

        if (A.length() == 1 && A.charAt(0) == '0') {
            return 0;
        }

        while (dividend.length() > 0 && !dividend.equalsIgnoreCase("2")) {
            str = new StringBuilder();
            int carry = 0;
            int n = dividend.length();

            if (n > 0) {
                int num = dividend.charAt(n - 1) - '0';

                if (num % 2 == 1) {
                    return 0;
                }
            }

            for (int i = 0; i < n; i++) {

                char c = (char) (dividend.charAt(i) - '0');
                int res = c + 10 * carry;

                c = (char) (res / 2 + '0');
                carry = res % 2;

                str.append(c);
            }

            if (str.charAt(0) == '0') {
                str.deleteCharAt(0);
            }

            dividend = str.toString();
        }

        return 1;

    }

}
