/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.math;

/**
 *
 * @author Dang
 */
public class PalindromeInteger {

    public boolean isPalindrome(int n) {
        //negative numbers are not palindrome
        if (n < 0) {
            return false;
        }

        /* bugs over 10 digits
        int len = (int) (Math.log10(n) + 1); //length of input
        int div = (int) Math.pow(10, len);
         */
        
        // initialize divider equal to number of digits
        int div = 1;
        while (n / div >= 10) {
            div *= 10;
        }

        while (n > 0) {
            // get first digit using divider based on length
            // if leading zeroes were dropped, will be noticed here
            int first = n / div;
            int last = n % 10;
            if (first != last) {
                return false;
            }
            n %= div; // remove 1st digit
            n /= 10; // remove last digit
            div /= 100; // adjust divider for new length
        }
        return true;
    }

}
