/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.bitmanipulation;

/**
 *
 * @author Dang
 */
public class ReverseBits {

    public long reverse(long a) {
        if (a == 0) {
            return a;
        }

        long b = 0;
        for (int i = 0; i < 32; i++) {
            b <<= 1; // shift result left

            if ((a & 1) == 1) { // right bit of input is set
                b++; // add 1 to result to set rightmost bit
            }
            a >>= 1; // shift input right
        }
        return b;
    }
}
