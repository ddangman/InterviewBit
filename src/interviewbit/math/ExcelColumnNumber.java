/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.math;

/**
 *
 * @author Dang
 */
public class ExcelColumnNumber {

    public int titleToNumber(String a) {
        char[] ch = a.toCharArray();
        int sum = 0;
        for (int i = 0; i < ch.length; i++) {
            int current = (ch[i] - 'A' ) + 1; // get letter value
            // multiply letter value by positional value
            // rightmost position has power 0, each position left has + 1
            current *= Math.pow(26, ch.length - 1 - i); // get position value
            sum += current;
        }
        return sum;
    }
}
