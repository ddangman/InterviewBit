/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.trees;

import interviewbit.datastructures.TreeNode;
import java.util.ArrayList;

/**
 *
 * @author Dang
 */
public class RootToLeafPathsWithSum {

    private ArrayList<ArrayList<Integer>> validPaths;

    public ArrayList<ArrayList<Integer>> pathSum(TreeNode root, int sum) {
        validPaths = new ArrayList<>();

        findPath(root, new ArrayList<Integer>(), sum);
        
        return validPaths;
    }

    private void findPath(TreeNode root, ArrayList<Integer> path, int sum) {
        if (root == null) { // exit condition
            return;
        }
        
        // update variables
        path.add(root.val);
        int diff = sum - root.val;
        
        // valid path found
        if (root.left == null && root.right == null && sum - root.val == 0) {
            validPaths.add(new ArrayList<>(path));
        }

        // enter recursion
        findPath(root.left, path, diff);
        findPath(root.right, path, diff);
        
        // remove root.val from shared list
        path.remove(path.size() - 1);
    }
}
