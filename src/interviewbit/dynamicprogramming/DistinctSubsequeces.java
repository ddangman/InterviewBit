/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.dynamicprogramming;

/**
 * Given two sequences 'cut', 'key', count number of unique ways in sequence 'cut', 
 * to form a subsequence that is identical to the sequence 'key'.
 * 
 * Solution: For each char in 'key', iterate through 'cut' and find matching char.
 * While iterating through 'cut' char, number of distinct subsequences are carried
 * forward. When a matching char is found, add distinct subsequences of minus
 * current char to the distinct subsequence being carried forward.
 * 
 * https://discuss.leetcode.com/topic/9488/easy-to-understand-dp-in-java
 * @author Dang
 */
public class DistinctSubsequeces {

    // cut is the string whose subsequence should match key
    public int numDistinct(String cut, String key) {
        int kl = key.length();
        int cl = cut.length();
        
        // mem[k + 1][c + 1] = number of distinct subsequences of cut[c] matching key[k]
        int[][] mem = new int[kl + 1][cl + 1];

        // filling the zeroth row: with 1s
        // an empty string is a subsequence of any string one time
        for (int c = 0; c <= cl; c++) {
            mem[0][c] = 1;
        }

        // the first column is 0 by default in every other rows but the first
        // an empty key can not contain a non-empty string as a substring
        for (int k = 0; k < key.length(); k++) {
            for (int c = 0; c < cut.length(); c++) {
                if (key.charAt(k) == cut.charAt(c)) {
                    // add previous item (same row) to previous item in previous row
                    mem[k + 1][c + 1] = mem[k + 1][c] + mem[k][c];
                } else {
                    // character doesn't match. we have same number of distint
                    // subsequence as we did without the new character
                    mem[k + 1][c + 1] = mem[k + 1][c];
                }
            }
        }

        return mem[key.length()][cut.length()];
    }
}
