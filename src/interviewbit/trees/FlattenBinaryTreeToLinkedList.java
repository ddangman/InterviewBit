/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.trees;

import interviewbit.datastructures.TreeNode;
import java.util.Stack;

/**
 *
 * @author Dang
 */
public class FlattenBinaryTreeToLinkedList {
    public TreeNode flatten(TreeNode root) {
        TreeNode chain, primer;
        chain = new TreeNode(0);
        primer = chain;
        Stack<TreeNode> stack = new Stack<>();
        
        // add to chain using preorder traversal
        // visit top of stack, push right child, push left child
        stack.add(root);
        while (!stack.isEmpty()) {
            // add top of stack to chain
            root = stack.pop();
            chain.right = root;
            
            // add right child to stack
            if (root.right != null) {
                stack.add(root.right);
            }        
            
            // add left child to stack
            if (root.left != null) {
                stack.add(root.left);
            }  
            
            chain.left = null; // every left must be null
            chain = chain.right; // traverse right of chain
        }
        chain.right = null;

        return primer.right;
    }
}
