/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.bitmanipulation;

/**
 *
 * (a - 1) will flip the rightmost bit and everything right of it
 * a & (a - 1) will clear out the rightmost bit
 */
public class Numberof1Bits {
    public int numSetBits(long a) {
        int count = 0;
        while (a > 0) {
            a = a & (a - 1);
            count++;
        }
        return count;
    }
}
