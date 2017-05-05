/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.strings;

/**
 *
 * @author Dang
 */
public class PalindromeString {
    	public int isPalindrome(String a) {
            String san = a.toLowerCase().replaceAll("[^A-Za-z0-9]", "");
            int s = 0;
            int e = san.length() - 1;
            while (s < e) {
                if (san.charAt(s) != san.charAt(e)) {
                    return 0;
                }            
                s++;
                e--;
            }
            return 1;
	}
}
