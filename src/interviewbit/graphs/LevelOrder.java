/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.graphs;

import interviewbit.datastructures.TreeNode;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author Duy Dang
 */
public class LevelOrder {

    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode a) {
        LinkedList<TreeNode> q1 = new LinkedList<>();
        LinkedList<TreeNode> q2 = new LinkedList<>();
        q1.add(a);
        ArrayList<ArrayList<Integer>> traversal = new ArrayList<>();
        while (!q1.isEmpty() || !q2.isEmpty()) {
            ArrayList<Integer> build = new ArrayList<>();
            while (!q1.isEmpty()) {
                TreeNode pop = q1.pop();
                build.add(pop.val);
                if (pop.left != null) {
                    q2.add(pop.left);
                }
                if (pop.right != null) {
                    q2.add(pop.right);
                }
            }
            if (build.size() != 0) {
                traversal.add(build);
                build = new ArrayList<>();
            }
            while (!q2.isEmpty()) {
                TreeNode pop = q2.pop();
                build.add(pop.val);
                if (pop.left != null) {
                    q1.add(pop.left);
                }
                if (pop.right != null) {
                    q1.add(pop.right);
                }
            }
            if (build.size() != 0) {
                traversal.add(build);
                build = new ArrayList<>();
            }
        }
        
        return traversal;
    }
}
