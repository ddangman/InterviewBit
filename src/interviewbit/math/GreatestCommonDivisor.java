/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.math;

/**
 *
 * @author Dang
 */
public class GreatestCommonDivisor {

    public int gcd(int a, int b) {
        //edge case
        if (a == 0) {
            return b;
        }
        if (b == 0) {
            return a;
        }

        // test lower number and decrement until solved
        int lower = 0;
        if (a < b) {
            lower = a;
        } else {
            lower = b;
        }
        while (true) {
            if (a % lower == 0 && b % lower == 0) {
                return lower;
            } else {
                lower--;
            }
        }
    }
}
