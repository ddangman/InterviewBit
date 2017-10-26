/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.trees;

import interviewbit.datastructures.TreeNode;

/**
 *
 * @author Dang
 */
public class MinimumDepthOfBinaryTree {

    private int min;

    public int minDepth(TreeNode a) {
        min = Integer.MAX_VALUE;
        findDepth(a, 1);
        return min;
    }

    private void findDepth(TreeNode tn, int level) {
        if (tn == null) {
            return;
        }
        
        if (tn.left == null && tn.right == null && level < min) {
            min = level;
        }        

        findDepth(tn.left, level + 1);
        findDepth(tn.right, level + 1);

    }
}
