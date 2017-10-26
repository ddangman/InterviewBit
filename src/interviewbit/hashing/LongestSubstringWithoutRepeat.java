/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.hashing;

/**
 *
 * @author Dang
 */
public class LongestSubstringWithoutRepeat {

    public int lengthOfLongestSubstring(String s) {
        int ans = 0;
        int[] index = new int[128]; // greatest index of character
        for (int j = 0, k = 0; j < s.length(); j++) {
            // get index of previous occurance of current character
            k = Math.max(index[s.charAt(j)], k);
            // (current index of char) - (index of previous occurance of char)
            // plus one equals number of unique characters in between
            ans = Math.max(ans, j - k + 1);
            index[s.charAt(j)] = j + 1; // update index of current character
        }
        return ans;
    }
}
