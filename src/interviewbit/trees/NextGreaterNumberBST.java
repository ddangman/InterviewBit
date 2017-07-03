/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.trees;

/**
 *
 * @author Dang
 */
public class NextGreaterNumberBST {

    public TreeNode getSuccessor(TreeNode a, int b) {
        // points to next greater value uptree
        TreeNode uptree = null;
        TreeNode traverse = a;

        // find value
        while (traverse.val != b) {
            if (traverse.val > b) {
                uptree = traverse;
                traverse = traverse.left;
            } else {
                traverse = traverse.right;
            }
            if (traverse == null) {
                return null;
            }
        }
        
        if (traverse.right == null) {
            return uptree;
        } else {
            // find next greater value down tree
            traverse = traverse.right;
            while (traverse.left != null) {
                traverse = traverse.left;
            }
            return traverse;
        }
    }
}
