/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.trees;

import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * We can print spiral order traversal in O(n) time and O(n) extra space. 
 * The idea is to use two stacks. 
 * We can use one stack for printing from left to right 
 * and other stack for printing from right to left. 
 * In every iteration, we have nodes of one level in one of the stacks. 
 * We print the nodes, and push nodes of next level in other stack.
 */
public class ZigZagLevelOrderTraversalBT {

    public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode a) {
        ArrayList<ArrayList<Integer>> visit = new ArrayList<>();
        ArrayList<Integer> level;
        TreeNode node;
        Stack<TreeNode> right = new Stack<>();
        Stack<TreeNode> left = new Stack<>();

        right.add(a);
        while (!right.isEmpty() || !left.isEmpty()) {
            level = new ArrayList<>();
            
            if (!right.isEmpty()) {
                while (!right.isEmpty()) {
                    node = right.pop();
                    level.add(node.val); // visit node
                    
                    // add valid nodes to other stack
                    if (node.left != null) {
                        left.add(node.left);
                    }
                    if (node.right != null) {
                        left.add(node.right);
                    }
                }
                visit.add(level);
            } else {
                while (!left.isEmpty()) {
                    node = left.pop();
                    level.add(node.val);
                    if (node.right != null) {
                        right.add(node.right);
                    }
                    if (node.left != null) {
                        right.add(node.left);
                    }
                }
                visit.add(level);
            }
        }
        return visit;
    }
}
