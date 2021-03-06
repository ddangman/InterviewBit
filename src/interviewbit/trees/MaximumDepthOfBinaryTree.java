/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.trees;

import interviewbit.datastructures.TreeNode;

/**
 *
 * @author Dang
 */
public class MaximumDepthOfBinaryTree {

    public int maxDepth(TreeNode a) {
        return findDepth(a, 0);
    }
    
    private int findDepth(TreeNode tn, int depth) {
        if (tn == null) {
            return depth;
        }        
        
        int left = findDepth(tn.left, depth + 1);
        int right = findDepth(tn.right, depth + 1);
        
        return left > right ? left : right;
    }
}
