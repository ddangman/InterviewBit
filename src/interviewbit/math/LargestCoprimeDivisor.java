/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.math;

import java.util.TreeSet;

/**
 *
 * @author Dang
 */
public class LargestCoprimeDivisor {

    public int cpFact(int a, int b) {
        for (int i = a; i >= 1;) {
            if (a % i == 0) {
                if (gcd(i, b) == 1) {
                    return i;
                }
                i = i / gcd(i, b);
            } else {
                i--;
            }
        }
        return 0;
    }

    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    // A function to find all prime factors
    // of a given number n
    private static TreeSet<Integer> primeFactors(int n) {

        TreeSet<Integer> ts = new TreeSet<>();
        ts.add(n);
        // Print the number of 2s that divide n
        while (n % 2 == 0) {
            n /= 2;
            ts.add(2);
            ts.add(n);
        }
        int sqrt = (int) Math.sqrt(n);

        // n must be odd at this point.  So we can
        // skip one element (Note i = i +2)
        for (int i = 3; i <= sqrt; i += 2) {
            // While i divides n, print i and divide n
            while (n % i == 0) {
                ts.add(i);
                n /= i;
                ts.add(n);
            }
        }
        return ts;
    }
}
