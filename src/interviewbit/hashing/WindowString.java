/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.hashing;

/**
 * Given a string 'text' and a string 'pattern', find the minimum window in text
 * which will contain all the characters in pattern If no such window exists,
 * return an empty string ""
 *
 * Solution involves using two pointers to represent the window boundary Expand
 * the window until all pattern characters are found Reduce window until window
 * is invalid. Once invalid, expand window until all pattern characters are
 * found. int[] map will be used to track characters in window. Pattern
 * characters are added to map. As string characters are added to window, map
 * value is subtracted. As string characters are removed from window, map value
 * is added. If map value is greater than 0, we know it is an unmatched
 * character from pattern.
 *
 */
public class WindowString {

    public String minWindow(String text, String pattern) {
        int counter = pattern.length(); // number of unmatched pattern characters
        int begin = 0, end = 0; // two pointers representing window boundary
        int head = 0;
        int minWin = Integer.MAX_VALUE; // smallest window found

        // integer array for ASCII 128 direct access table
        // index indicates ASCII character
        // positive value indicates number of unmatched characters
        int[] map = new int[128];
        // count characters in pattern
        for (int i = 0; i < counter; i++) {
            char c = pattern.charAt(i);
            map[c]++;
        }

        while (end < text.length()) {
            // decrease counter if map shows text char as unmatched
            if (map[text.charAt(end)] > 0) {
                counter--;
            }
            // add char to map 
            // If char does not exist in pattern, map will be negative
            map[text.charAt(end)]--;

            // move end to find a valid window
            end++;

            // when valid window is found, move start to decrease window
            while (counter == 0) {
                // use minWin to hold window size
                if (end - begin < minWin) {
                    head = begin;
                    minWin = end - begin;
                }
                // 
                map[text.charAt(begin)]++; // remove char from map
                // if char did not exist in pattern, it should have a negative value
                // if char belongs to pattern, increase counter
                if (map[text.charAt(begin)] > 0) {
                    counter++;
                }
                begin++; // reduce window size
            }
        }

        return minWin == Integer.MAX_VALUE ? "" : text.substring(head, head + minWin);
    }
}
