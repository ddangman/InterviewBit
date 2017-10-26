/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Find the largest distance between two nodes in a tree.
 * 
 * Pivot of longest distance may not be root.
 * Find 2 farthest children and add their depths to find best span going through
 * parent node. Subtract parent's depth twice to adjust for count to tree's root.
 * @author Duy Dang
 */
public class LargestDistanceBetweenTreeNodes {

    private HashMap<Integer, ArrayList<Integer>> map;
    private int bestSpan;

    public int solve(ArrayList<Integer> a) {
        if (a.size() <= 2) {
            return a.size() - 1;
        }
        map = new HashMap<>();
        int root = -1;
        for (int c = 0; c < a.size(); c++) {
            int p = a.get(c);
            if (p == -1) {
                root = c;
            }
            ArrayList<Integer> neighbors = map.get(p);
            if (neighbors == null) {
                map.put(p, new ArrayList<>(Arrays.asList(c)));
            } else {
                neighbors.add(c);
            }
        }
        dfs(root, 0);

        return bestSpan;
    }

    private int dfs(int root, int count) {
        ArrayList<Integer> neighbors = map.get(root);
        if (neighbors == null) {
            return count;
        }

        int first = 0;
        int second = 0;
        for (Integer nb : neighbors) {
            int depth = dfs(nb, count + 1);

            if (depth > Math.min(first, second)) {
                first = Math.max(first, second);
                second = depth;
            }

        }
        int span = first + second - count - count;
        if (bestSpan < span) {
            bestSpan = span;
        }
        return Math.max(first, second);
    }
    
    
    public int dynamicProgramming(ArrayList<Integer> A) {
        int[] dp = new int[A.size()]; // store length to deepest child
        int max = 0; // store maximum distance between two nodes
        
        for (int i = A.size() - 1; i > 0; i--) {
            int parent = A.get(i);
            int depth = dp[i] + 1; // max depth formed by new child
            
            max = Math.max(max, depth + dp[parent]);
            dp[parent] = Math.max(dp[parent], depth);
        }
        
        return max;
    }
}
