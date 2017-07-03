/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.trees;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Dang
 */
public class ShortestUniquePrefix {

    public ArrayList<String> prefix(ArrayList<String> input) {
        ArrayList<String> unique = new ArrayList<>();
        Trie root = new Trie();

        // initialize Trie from input
        Trie pointer = root;
        for (String s : input) {
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (!pointer.child.containsKey(c)) {
                    pointer.child.put(c, new Trie()); // add child
                }
                pointer = pointer.child.get(c); // traverse to child node
            }
            pointer = root; // reset pointer
        }

        // find shortest unique prefix
        for (String s : input) {
            int end = s.length(); // initialize unique substring to entire length
            // traverse entire length of string to find multiple nodes
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                pointer = pointer.child.get(c); // advance pointer to child node
                if (pointer.child.size() > 1) {
                    // multiple nodes found, reset substring to entire length
                    end = s.length();
                } else if (pointer.child.size() == 1) {
                    // node is unique, keep smallest value
                    end = i + 1 > end ? end : i + 1;
                }
            }
            unique.add(s.substring(0, end)); // add unique substring
            pointer = root; // reset pointer
        }

        return unique;
    }

    private class Trie {
        HashMap<Character, Trie> child;

        Trie() {
            child = new HashMap<>();
        }
    }
}
