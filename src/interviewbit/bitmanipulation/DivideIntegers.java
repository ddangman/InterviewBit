/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.bitmanipulation;

/**
 *
 * @author Dang
 */
public class DivideIntegers {

    public int divide(int dividend, int divisor) {
        if (divisor == 1) {
            return dividend;
        }

        long res = division(dividend, divisor);
        if (res < Integer.MIN_VALUE || res > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        return (int) res;
    }

    private long add(long a, long b) {
        long partialSum, carry;
        do {
            // Sum of bits of a and b where at least one of the bits is not set
            partialSum = a ^ b;
            
            // carry now contains common set bits of a and b
            carry = a & b;
            
            // Carry is shifted by one so that adding it to partialSum gives the required sum
            b = carry << 1;
            a = partialSum;
        } while (carry != 0); // Iterate till there is no carry  
        return partialSum;
    }

    private long subtract(long a, long b) {
        return add(a, add(~b, 1));
    }

    // The steps are similar to how you would perform long division in decimal and in fact, 
    // long division is easier in binary since the quotient at each bit can only be 0 or 1.
    // assume 32 bits 2's complement integer
    private long division(long dividend, long divisor) {
        boolean negative = false;
        if ((dividend & (1 << 31)) == (1 << 31)) { // Check for signed bit
            negative = !negative;
            dividend = add(~dividend, 1);  // Negation
        }
        if ((divisor & (1 << 31)) == (1 << 31)) { // Check for signed bit
            negative = !negative;
            divisor = add(~divisor, 1);  // Negation
        }
        long quotient = 0;
        long r;
        for (long i = 30; i >= 0; i = subtract(i, 1)) {
            r = (divisor << i);
            // Left shift divisor until it's smaller than dividend
            if (r < Integer.MAX_VALUE && r >= 0) { // Avoid cases where comparison between long and int doesn't make sense
                if (r <= dividend) {
                    quotient |= (1 << i);
                    dividend = subtract(dividend, (int) r);
                }
            }
        }
        if (negative) {
            quotient = add(~quotient, 1);
        }
        return quotient;
    }
}
