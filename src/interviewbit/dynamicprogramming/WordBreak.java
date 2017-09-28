/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.dynamicprogramming;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Dang
 */
public class WordBreak {

    public int wordBreakHashset(String s, ArrayList<String> array) {
        Set<String> dict = new HashSet<>(array);
        
        // f[i] stands for whether subarray(0, i) can be segmented into words 
        // from the dictionary. So f[0] means whether subarray(0, 0) 
        // (which is an empty string) can be segmented, 
        // and of course the answer is yes.
        boolean[] f = new boolean[s.length() + 1];
        // The default value for boolean array is false. 
        // Therefore we need to set f[0] to be true.        
        f[0] = true;

        // check each index as END of valid dictionary string
        for (int i = 1; i <= s.length(); i++) {
            for (String str : dict) { 
                if (str.length() <= i) { // valid length
                    if (f[i - str.length()]) { // previous index is valid word
                        if (s.substring(i - str.length(), i).equals(str)) {
                            f[i] = true;
                            break;
                        }
                    }
                }
            }
        }

        return f[s.length()] ? 1 : 0;
    }

    public int wordBreakTrie(String input, ArrayList<String> dict) {
        // initialize Trie
        Trie root = new Trie();
        for (String s : dict) {
            insertTrie(root, s);
        }

        // memoization
        int len = input.length();
        // stores starting index of valid strings
        boolean[] dp = new boolean[len + 1];
        dp[len] = true;

        // build memoization array
        for (int i = len - 1; i >= 0; i--) {
            Trie ptr = root;
            for (int j = i; ptr != null && j < len; j++) {
                char c = input.charAt(j);
                ptr = ptr.child[c - 'a'];
                // substring is valid if not null, end of word, and 
                // index after end of word is start of next valid word
                if (ptr != null && ptr.end && dp[j + 1]) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[0] ? 1 : 0;
    }

    /**
     *
     * @param node root of Trie
     * @param s string to insert into trie
     */
    private void insertTrie(Trie node, String s) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (node.child[c - 'a'] == null) {
                node.child[c - 'a'] = new Trie();
            }
            node = node.child[c - 'a'];
        }
        node.end = true;
    }

    /**
     * Trie data structure designed for only lowercase alphabet characters
     */
    private class Trie {

        Trie[] child;
        boolean end;

        Trie() {
            child = new Trie[26];
            end = false;
        }
    }
}
