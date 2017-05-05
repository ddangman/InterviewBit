/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.strings;

/**
 *
 * @author Dang
 */
public class LengthLastWord {

    public int lengthOfLastWord(final String a) {
        boolean endTrimmed = false;
        int count = 0;
        int end = a.length() - 1;
        while (end >= 0) {
            if (a.charAt(end) == ' ' && endTrimmed) {
                break;
            }
            if (a.charAt(end) != ' ') {
                endTrimmed = true;
            }            
            if (endTrimmed) {
                count++;
            }            
            end--;
        }
        return count;
    }
}
