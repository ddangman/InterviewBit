/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.trees;

import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author Dang
 */
public class TreeTraversalIterative {

    public ArrayList<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> visit = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode ptr = root;

        while (!stack.isEmpty() || ptr != null) {
            if (ptr != null) {
                // stack all left child until null
                stack.add(ptr);
                ptr = ptr.left;
            } else {
                // left most child on top of stack
                ptr = stack.pop();
                visit.add(ptr.val);
                // stack first right child
                ptr = ptr.right;
            } // stack all left nodes on top of first right child
        }

        return visit;
    }

    public ArrayList<Integer> postorderTraversalTwoStack(TreeNode root) {
        ArrayList<Integer> visit = new ArrayList<>();
        Stack<TreeNode> traverse = new Stack<>();
        Stack<TreeNode> reverse = new Stack<>();
        TreeNode ptr;
        // root is visited last so it should be first to enter reverse stack
        traverse.add(root);

        // traverse tree
        while (!traverse.isEmpty()) {
            ptr = traverse.pop();
            reverse.add(ptr); // stack visit order in reverse

            // left is visited first, so it is pushed into traverse stack first
            // so it will end up on top of reverse stack
            if (ptr.left != null) {
                traverse.add(ptr.left);
            }
            // right child should be on top of traverse stack
            // so it will be lower in reverse stack
            if (ptr.right != null) {
                traverse.add(ptr.right);
            }
        }

        // visit in reverse
        while (!reverse.isEmpty()) {
            visit.add(reverse.pop().val);
        }

        return visit;
    }

    public ArrayList<Integer> postorderTraversalSingleStack(TreeNode root) {
        ArrayList<Integer> visit = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode ptr = root;

        while (ptr != null || !stack.isEmpty()) {
            if (ptr != null) {
                // stack all left child
                stack.add(ptr);
                ptr = ptr.left;
            } else {
                TreeNode peek = stack.peek().right;
                if (peek != null) {
                    // point to first right child to be stacked
                    // all of its left children will be stacked above first right
                    ptr = peek;
                } else { // right node already explored
                    TreeNode pop = stack.pop();
                    visit.add(pop.val); // visit top of stack

                    // top of stack is parent of pop
                    while (!stack.isEmpty() && stack.peek().right == pop) {
                        pop = stack.pop();
                        visit.add(pop.val);
                    }
                }
            }
        }
        return visit;
    }

    public ArrayList<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> visit = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        TreeNode pop;

        while (!stack.isEmpty()) {
            // visit
            pop = stack.pop();
            visit.add(pop.val);

            // stack right child
            if (pop.right != null) {
                stack.add(pop.right);
            }
            // stack left child
            if (pop.left != null) {
                stack.add(pop.left);
            }
        }

        return visit;
    }
}
