/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.bitmanipulation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Dang
 */
public class MinXorValue {

    private class TrieBit {

        Map<Integer, TrieBit> children;
        int value; // used in leaf node

        public TrieBit() {
            children = new HashMap<>();
        }
    }

    public TrieBit newNode() {
        TrieBit tb = new TrieBit();
        return tb;
    }

    public void insertIterative(TrieBit root, int key) {
        TrieBit currentNode = root;
        // start from the most significant bit, 
        // insert all bit of key one-by-one into trie
        for (int i = Integer.SIZE - 1; i >= 0; i--) {
            // find current bit in given prefix
            int currentBit = (key >> i) & 1;
            
            // get next node
            TrieBit nextNode = currentNode.children.get(currentBit);
            // check node for bit
            if (nextNode == null) {
                nextNode = newNode();
                currentNode.children.put(currentBit, nextNode);
            }
            currentNode = nextNode;
        }

        // store key in leaf node
        currentNode.value = key;
    }

    public int minXorUtil(TrieBit root, int key) {
        TrieBit currentNode = root;

        for (int i = Integer.SIZE - 1; i >= 0; i--) {
            // find current bit in given prefix
            int currentBit = (key >> i) & 1;

            // traverse Trie for prefix with same bit
            if (currentNode.children.containsKey(currentBit)) {
                currentNode = currentNode.children.get(currentBit);
            } else if (currentNode.children.containsKey(1-currentBit)) {
                // if there is no same bit, look for opposite bit
                currentNode = currentNode.children.get(1-currentBit);
            }
        }
        // return xor value of minimum bit difference value
        // so we get minimum xor value
        return key ^ currentNode.value;
    }

    // O(n) time
    public int findMinXorTrie(ArrayList<Integer> A) {
        int minXor = Integer.MAX_VALUE;

        // initialize root and add first element
        final TrieBit root = newNode();
        insertIterative(root, A.get(0));

        // traverse ArrayList and find minimum xor for every element
        for (int i = 1; i < A.size(); i++) {
            // Find minimum XOR value of current element with
            // previous elements inserted in Trie
            minXor = Math.min(minXorUtil(root, A.get(i)), minXor);

            // insert current array value into Trie
            insertIterative(root, A.get(i));
        }

        return minXor;
    }

    // O(n^2)
    public int findMinXorBrute(ArrayList<Integer> A) {
        int res = Integer.MAX_VALUE;
        int temp;
        for (int i = 0; i < A.size(); i++) {
            for (int j = i + 1; j < A.size(); j++) {
                temp = A.get(i) ^ A.get(j);
                if (temp < res) {
                    res = temp;
                }
            }
        }
        return res;
    }

    // Time complexity: O(N * logN) to sort the array and O(N) to find the smallest XOR 
    // Space complexity: O(N)
    public int findMinXorSorted(ArrayList<Integer> A) {
        Collections.sort(A);
        int res = Integer.MAX_VALUE;
        int temp;
        for (int i = 1; i < A.size(); i++) {
            temp = A.get(i) ^ A.get(i - 1);
            if (temp < res) {
                res = temp;
            }
        }
        return res;
    }
}
