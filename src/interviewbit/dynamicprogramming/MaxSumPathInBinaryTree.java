/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.dynamicprogramming;

import interviewbit.datastructures.TreeNode;

/**
 * Given a binary tree, find the maximum path sum. 
 * The path may start and end at any node in the tree.
 * 
 * For each node there can be four ways that the max path goes through the node:
 *   1. Node only
 *   2. Max path through Left Child + Node
 *   3. Max path through Right Child + Node
 *   4. Max path through Left Child + Node + Max path through Right Child
 * 
 * The idea is to keep trace of four paths and pick up the max one in the end. 
 * An important thing to note is, root of every subtree need to return maximum 
 * path sum such that at most one child of root is involved. This is needed for 
 * parent function call. In below code, this sum is stored in ‘max_single’ and 
 * returned by the recursive function.
 * @author Dang
 */
public class MaxSumPathInBinaryTree {

    private int gMax; // global max
    public int maxPathSum(TreeNode root) {
        gMax = Integer.MIN_VALUE; // initialize global max
        findMaxUtil(root); // perform calculations
        return gMax;
    }
    
    private int findMaxUtil(TreeNode node) {
        // base case
        if (node == null) {
            return 0;
        }  
        
        // find max from both children
        int left = findMaxUtil(node.left);
        int right = findMaxUtil(node.right);
        
        // max returned from either child
        int max_child = Math.max(left, right) + node.val;
        // max with or without single child
        int max_parent = Math.max(max_child, node.val);
        // max of all combinations
        int max_trio = Math.max(max_parent, node.val + left + right);
        
        // update global max
        if (max_trio > gMax) {
            gMax = max_trio;
        } 
        
        return max_parent; // return node max for recursive calculation
    }

}
