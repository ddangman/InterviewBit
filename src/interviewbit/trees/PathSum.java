/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.trees;

import interviewbit.datastructures.TreeNode;

/**
 *
 * @author Dang
 */
public class PathSum {

    public int hasPathSum(TreeNode root, int target) {
        return PathSum(root, target) ? 1 : 0;
    }
    
    public boolean PathSum(TreeNode root, int sum) {
        if(root == null) {
            return false;
        }
    
        if(root.left == null && root.right == null && sum - root.val == 0) {
            return true;
        }
    
        return PathSum(root.left, sum - root.val) || 
                PathSum(root.right, sum - root.val);
    }
}
