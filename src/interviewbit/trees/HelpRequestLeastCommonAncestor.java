/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.trees;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Dang
 */
public class HelpRequestLeastCommonAncestor {

    ArrayList<Integer> al1 = new ArrayList();
    ArrayList<Integer> al2 = new ArrayList();

    public int lca(TreeNode a, int val1, int val2) {
        traverse1(a, val1);
        traverse2(a, val2);
        int i = 0;
        if (al1.size() == 0 || al2.size() == 0) {
            return -1;
        }
        if (val1 == val2) {
            return val1;
        }
        for (i = 0; i < Math.min(al1.size(), al2.size()); i++) {
            if (!Objects.equals(al1.get(i), al2.get(i))) {
                break;
            }
        }
        if (i == 0) {
            return al1.get(0);
        }        
        return al1.get(i - 1);
    }

    public boolean traverse1(TreeNode a, int val) {
        if (a == null) {
            return false;
        }
        al1.add(a.val);
        if (a.val == val) {
            return true;
        }
        if (traverse1(a.left, val)) {
            return true;
        }
        if (traverse1(a.right, val)) {
            return true;
        }
        al1.remove(al1.size() - 1);
        return false;
    }

    public boolean traverse2(TreeNode a, int val) {
        if (a == null) {
            return false;
        }
        al2.add(a.val);
        if (a.val == val) {
            return true;
        }
        if (traverse2(a.left, val)) {
            return true;
        }
        if (traverse2(a.right, val)) {
            return true;
        }
        al2.remove(al2.size() - 1);
        return false;
    }
}
