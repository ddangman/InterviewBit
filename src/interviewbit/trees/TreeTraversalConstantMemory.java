/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.trees;

import interviewbit.datastructures.TreeNode;
import java.util.ArrayList;

/**
 * Morris traversal modifies the tree during the process. 
 * It establishes the right links while moving down the tree and 
 * resets the right links while moving up the tree. 
 * So the algorithm cannot be applied if write operations are not allowed.
 */
public class TreeTraversalConstantMemory {

    // Morris Inorder Tree Traversal
    public ArrayList<Integer> inorderTraversal(TreeNode a) {
        ArrayList<Integer> visit = new ArrayList<>();

        TreeNode pointer = a;
        while (pointer != null) {
            if (pointer.left == null) {
                // visit node after left is completely explored
                visit.add(pointer.val); // visit node
                pointer = pointer.right; // move right
            } else {
                // predecessor should be far right of pointer's left child
                TreeNode predecessor = pointer.left; // one step left
                while (predecessor.right != null && predecessor.right != pointer) {
                    predecessor = predecessor.right; // go far right
                }

                if (predecessor.right == null) {
                    // thread predecessor to pointer
                    predecessor.right = pointer; // thread
                    pointer = pointer.left; // continue exploring left
                } else {
                    // predecessor already threaded to pointer
                    // meaning current.left has already been completely explored
                    // and pointer is looping back down left side after climbing thread
                    predecessor.right = null; // remove thread
                    visit.add(pointer.val); // visit pointer
                    // move right down tree, up thread, or null to terminate
                    pointer = pointer.right; // move pointer right
                }
            }
        }

        return visit;
    }
    
    // Morris Preorder Tree Traversal
    public ArrayList<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> visit = new ArrayList<>();
        
        while (root != null) {
            if (root.left != null) {
                // send pointer to thread
                TreeNode pointer = root.left;
                while (pointer.right != root && pointer.right != null) {
                    pointer = pointer.right;
                }
                if (pointer.right == null) {
                    // thread needed
                    pointer.right = root;
                    visit.add(root.val); // visit root before exploring left
                    root = root.left;
                } else {
                    // pointer is threaded to root
                    pointer.right = null; // remove thread
                    // move right down tree, up thread, or null to terminate
                    root = root.right; 
                }               
            } else {
                // leftmost child reached
                visit.add(root.val); // visit left node
                // move right down tree, up thread, or null to terminate
                root = root.right;
            }           
        }
        
        return visit;
    }
}
