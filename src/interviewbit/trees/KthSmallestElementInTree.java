/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.trees;

import interviewbit.datastructures.TreeNode;

/**
 *
 * @author Dang
 */
public class KthSmallestElementInTree {

    // Morris Inorder Traversal. Deduct k at every visit and return after k visits
    public int kthsmallest(TreeNode root, int k) {
        
        while (root != null) {
            if (root.left == null) {
                k--; // visit
                if (k == 0) {
                    return root.val;
                }                
                root = root.right;
            } else {
                TreeNode pointer = root.left;
                
                // find rightmost child
                while (pointer.right != null && pointer.right != root) {
                    pointer = pointer.right;
                }
                if (pointer.right == null) {
                    // thread
                    pointer.right = root;
                    root = root.left;
                } else {
                    pointer.right = null; // remove thread
                    k--; // visit
                    if (k == 0) {
                        return root.val;
                    }                   
                    root = root.right;
                }               
            }           
        } 
        return -1;
    }
}
