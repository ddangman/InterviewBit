/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.trees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Dang
 */
public class HotelReviews {

    // solution using Trie
    public ArrayList<Integer> solve(String keyword, ArrayList<String> reviews) {
        ArrayList<Pair> pa = new ArrayList<>(); // pair array
        ArrayList<Integer> order = new ArrayList<>();
        Trie root = new Trie(); // initialize root

        // insert keywords into Trie
        Trie pointer = root;
        for (int i = 0; i < keyword.length(); i++) {
            char c = keyword.charAt(i);
            if (c == '_') {
                pointer.isEnd = true; // end of word
                pointer = root; // reset pointer
            } else {
                if (pointer.key[c - 'a'] == null) {
                    pointer.key[c - 'a'] = new Trie(); // add character
                }
                pointer = pointer.key[c - 'a']; // advance pointer to next node
            }
        }
        pointer.isEnd = true; // validate last keyword

        // rate reviews
        for (int i = 0; i < reviews.size(); i++) {
            pointer = root;
            String s = reviews.get(i);
            int points = 0;
            for (int j = 0; j < s.length(); j++) {
                char c = s.charAt(j);

                if (c == '_') { // end of keyword
                    pointer = root; // reset pointer
                } else if (pointer.key[c - 'a'] == null) {
                    // invalid character, advance to end of keyword
                    while (j + 1 < s.length() && s.charAt(j + 1) != '_') {
                        j++;
                    }
                } else { // valid character
                    pointer = pointer.key[c - 'a']; // advance pointer
                    // points should only be given for exact keyword matches
                    if (pointer.isEnd
                            && (j + 1 == s.length() || s.charAt(j + 1) == '_')) {
                        points++;
                    }
                }
            }
            pa.add(new Pair(points, i)); // store pair in array
        }

        // stable sort reviews
        Collections.sort(pa, (o1, o2) -> {
            if (o1.points == o2.points) {
                // sort by ascending index if points match
                return o1.index - o2.index;
            }
            // sort by decreasing points
            return o2.points - o1.points;
        });

        // copy indices to order
        pa.forEach((p) -> {
            order.add(p.index);
        });

        return order;
    }

    private class Trie {

        Trie[] key;
        boolean isEnd;

        Trie() {
            // all characters are lowercase alphabet
            // subtract 'a' from character 
            // so that 'a' has index of 0 and 'z' has index 25
            key = new Trie[26];
        }
    }

    private class Pair {

        int points;
        int index;

        Pair(int p, int i) {
            points = p;
            index = i;
        }
    }

    public class HashSetSolution {

        private class Pair {

            private int goodness;
            private int index;

            public Pair(int goodness, int index) {
                this.goodness = goodness;
                this.index = index;
            }

            public Pair() {
            }

            public int getGoodness() {
                return goodness;
            }

            public int getIndex() {
                return index;
            }

            public void setGoodness(int goodness) {
                this.goodness = goodness;
            }

            public void setIndex(int index) {
                this.index = index;
            }
        }

        public ArrayList<Integer> solve(String A, ArrayList<String> B) {
            Set<String> set = new HashSet<String>();

            for (String good : A.split("_")) {
                set.add(good);
            }

            int n = B.size();
            List<Pair> rank = new ArrayList<Pair>();

            for (int i = 0; i < n; i++) {
                String review = B.get(i);
                int cnt = 0;
                for (String word : review.split("_")) {
                    if (set.contains(word)) {
                        cnt += 1;
                    }
                }
                Pair pair = new Pair();
                // set pair values:
                pair.setGoodness(cnt);
                pair.setIndex(i);
                rank.add(pair);
            }

            Collections.sort(rank, new Comparator<Pair>() {
                @Override
                public int compare(Pair lhs, Pair rhs) {
                    if (rhs.getGoodness() > lhs.getGoodness()) {
                        return 1;
                    } else if (rhs.getGoodness() < lhs.getGoodness()) {
                        return -1;
                    } else {
                        if (rhs.getIndex() > lhs.getIndex()) {
                            return -1;
                        } else {
                            return 1;
                        }
                    }
                }
            });

            ArrayList<Integer> ans = new ArrayList<Integer>();
            for (Pair p : rank) {
                ans.add(p.getIndex());
            }

            return ans;

        }
    }
}
