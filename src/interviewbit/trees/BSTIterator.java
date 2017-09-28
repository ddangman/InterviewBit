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
public class BSTIterator {

    public class Solution {

        private TreeNode pointer;
        private Stack<TreeNode> stack;

        public Solution(TreeNode root) {
            stack = new Stack<>();
            if (root == null) {
                return;
            }
            pointer = root;

            while (pointer != null) {
                stack.push(pointer);
                pointer = pointer.left;
            }
        }

        /**
         * @return whether we have a next smallest number
         */
        public boolean hasNext() {
            if (pointer == null && stack.isEmpty()) {
                return false;
            }            
            
            if (pointer != null && pointer.right != null) {
                stack.push(pointer.right);
                pointer = pointer.right;
                while (pointer.left != null) {
                    stack.push(pointer.left);
                    pointer = pointer.left;
                }
            }
            
            if (!stack.isEmpty()) {
                pointer = stack.pop();
                return true;
            }            
            return false;
        }

        /**
         * @return the next smallest number
         */
        public int next() {
            return pointer.val;
        }
    }
}
