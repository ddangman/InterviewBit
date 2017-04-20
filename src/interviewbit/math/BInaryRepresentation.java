/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.math;

/**
 *
 * @author Dang
 */
public class BInaryRepresentation {

    public String findDigitsInBinary(int a) {
        // corner case
        if (a == 0) {
            return "0";
        }
        String binary = "";
        while (a > 0) {
            String s = Integer.toString(a % 2);
            // append result to left of solution
            binary = s + binary;
            a /= 2;
        }
        return binary;
    }
}
