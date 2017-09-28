/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.trees;

import interviewbit.datastructures.TreeNode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeMap;

/**
 *
 * @author Dang
 */
public class VerticalOrderTraversalOfBInaryTree {

    public ArrayList<ArrayList<Integer>> verticalOrderTraversal(TreeNode A) {
        ArrayList<ArrayList<Integer>> vertical = new ArrayList<>();
        if (A == null) {
            return vertical;
        }        
        TreeMap<Integer, ArrayList<Integer>> map = new TreeMap<>();

        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(0, A));
        while (!q.isEmpty()) {
            // level order traversal
            Pair pop = q.poll();
            if (pop.node.left != null) {
                q.add(new Pair(pop.x - 1, pop.node.left));
            }          
            if (pop.node.right != null) {
                q.add(new Pair(pop.x + 1, pop.node.right));
            }   
            
            // vertical mapping
            ArrayList<Integer> m = map.get(pop.x);
            if (m != null) {
                map.get(pop.x).add(pop.node.val);
            } else {
                map.put(pop.x, new ArrayList<>(Arrays.asList(pop.node.val)));
            }           
        }
        
        // copy minimum heap to arraylist
        for (int y : map.keySet()) {
            vertical.add(map.get(y));
        }
//        while (!map.isEmpty()) {
//            int min = map.firstKey();
//            vertical.add(map.get(min));
//            map.remove(min);
//        }

        return vertical;
    }

    class Pair {

        final int x; // vertical position relative to root as zero
        final TreeNode node;

        Pair(int x, TreeNode n) {
            this.x = x;
            this.node = n;
        }
    }
}
