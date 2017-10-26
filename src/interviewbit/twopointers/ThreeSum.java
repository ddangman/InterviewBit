/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Dang
 */
public class ThreeSum {

    private Trie root;

    private class Trie {
        Map<Integer, Trie> children;
        public Trie() {
            children = new HashMap<>();
        }
        public Trie(int i) {
            children = new HashMap<>();
            children.put(i, new Trie());
        }
    }

    public boolean searchTrie(int a, int b) {
        if (!root.children.containsKey(a)) {
            return false;
        }
        return root.children.get(a).children.containsKey(b);
    }

    public void addTrie(int a, int b) {
        if (!root.children.containsKey(a)) {
            root.children.put(a, new Trie(b));
        } else {
            root.children.get(a).children.put(b, new Trie());
        }
    }

    private boolean binarySearch(ArrayList<Integer> a, int start, int key) {
        int end = a.size() - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (a.get(mid) == key) {
                return true;
            } else if (key < a.get(mid)) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return false;
    }

    public ArrayList<ArrayList<Integer>> threeSum(ArrayList<Integer> a) {
        Collections.sort(a);
        root = new Trie();
        ArrayList<ArrayList<Integer>> ts = new ArrayList<>();

        for (int i = 0; i < a.size() - 2; i++) {
            for (int j = i + 1; j < a.size() - 1; j++) {
                if (!searchTrie(a.get(i), a.get(j))) {
                    int third = 0 - a.get(i) - a.get(j);
                    if (binarySearch(a, j + 1, third)) {
                        ts.add(new ArrayList<>(Arrays.asList(a.get(i), a.get(j), third)));
                        addTrie(a.get(i), a.get(j));
                    }
                }
            }
        }
        return ts;
    }

    // find sum of three closest to 'b'
    public int threeSumClosest(ArrayList<Integer> a, int b) {
        Collections.sort(a);

        long closest = Long.MAX_VALUE;
        long bestDifference = Long.MAX_VALUE;

        for (int i = 0; i < a.size(); i++) { // run int1 L->R
            for (int j = a.size() - 1; j >= 0; j--) { // run int2 R->L
                if (i < j) {
                    long difference = Long.MAX_VALUE;
                    long current = 0;
                    // run int3 L->R
                    int k = i + 1;
                    while (k < j && Math.abs(b - (a.get(i) + a.get(j) + a.get(k))) < difference) {
                        difference = Math.abs(b - (a.get(i) + a.get(j) + a.get(k)));
                        current = a.get(i) + a.get(j) + a.get(k);
                        k++;
                    }

                    // run int3 R->L
                    int l = j - 1;
                    while (l > k && Math.abs(b - (a.get(i) + a.get(j) + a.get(l))) < difference) {
                        difference = Math.abs(b - (a.get(i) + a.get(j) + a.get(l)));
                        current = a.get(i) + a.get(j) + a.get(l);
                        l--;
                    }

                    // perfect answer found
                    if (current == b) {
                        return (int) current;
                    }

                    // save best result
                    if (difference < bestDifference) {
                        bestDifference = difference;
                        closest = current;
                    }
                }

            }
        }
        return (int) closest;
    }
}
