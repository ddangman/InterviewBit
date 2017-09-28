/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.dynamicprogramming;

import java.util.ArrayList;

/**
 *
 * @author Dang
 */
public class WordBreakTwo {

    ArrayList<String> result;
    ArrayList<Pair>[] sMap;
    int len;

    public ArrayList<String> wordBreak(String input, ArrayList<String> dict) {
        Trie root = new Trie();
        for (String s : dict) {
            insert(s, root);
        }

        len = input.length();
        sMap = new ArrayList[len + 1];
        sMap[len] = new ArrayList<>();

        for (int i = len - 1; i >= 0; i--) {
            Trie ptr = root;
            for (int j = i; ptr != null && j < len; j++) {
                char c = input.charAt(j);
                ptr = ptr.child[c - 'a'];
                if (ptr != null && ptr.end && sMap[j + 1] != null) {
                    ArrayList<Pair> p = sMap[i];
                    if (p == null) {
                        p = sMap[i] = new ArrayList<>();
                    }
                    p.add(new Pair(j + 1, input.substring(i, j + 1)));
                }
            }
        }

        result = new ArrayList<>();
        dfs(new StringBuilder(), 0);
//        Collections.sort(result);
        return result;
    }

    private void dfs(StringBuilder sb, int i) {
        if (i == len) {
            result.add(sb.toString());
            return;
        }

        ArrayList<Pair> ap = sMap[i];
        if (ap == null) {
            return;
        }
        int revert = sb.length();
        for (Pair p : ap) {
            if (revert > 0) {
                sb.append(" ");
            }
            sb.append(p.word);
            dfs(sb, p.index);
            sb.setLength(revert);
        }
    }

    private void insert(String s, Trie node) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (node.child[c - 'a'] == null) {
                node.child[c - 'a'] = new Trie();
            }
            node = node.child[c - 'a'];
        }
        node.end = true;
    }

    private class Pair {

        int index;
        String word;

        Pair(int i, String w) {
            this.index = i;
            this.word = w;
        }
    }

    private class Trie {

        Trie[] child;
        boolean end;

        Trie() {
            child = new Trie[26];
            end = false;
        }
    }

    /**
     * Starting at index zero, 
     * recursively check all input substrings against dictionary.
     */
    private class GreedySolution {

        private String input;
        private ArrayList<String> dict;
        private int len;
        private ArrayList<String> res; // result

        public ArrayList<String> wordBreak(String input, ArrayList<String> dict) {
            this.input = input;
            this.dict = dict;
            this.len = input.length();
            this.res = new ArrayList<>();

            wordBreakUtil(0, new ArrayList<>());

            return res;
        }

        private void wordBreakUtil(int start, ArrayList<String> build) {
            if (start >= len) {
                stringFound(build);
                return;
            }

            for (int i = start; i < len; i++) {
                String sub = input.substring(start, i + 1);
                if (dict.contains(sub)) {
                    build.add(sub);
                    wordBreakUtil(i + 1, build);
                    build.remove(build.size() - 1);
                }
            }
        }

        // add valid string to result
        private void stringFound(ArrayList<String> build) {
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < build.size() - 1; i++) {
                sb.append(build.get(i));
                sb.append(' ');
            }
            sb.append(build.get(build.size() - 1));
            res.add(sb.toString());
        }
    }
}
