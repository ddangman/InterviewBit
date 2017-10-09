/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package utilities;

import interviewbit.datastructures.TreeNode;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author Duy Dang
 */
public class TreeBuilder {
    public static TreeNode build(String s) {
        Scanner scanner = new Scanner(s);
        TreeNode root = new TreeNode(scanner.nextInt());
        LinkedList<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (scanner.hasNextInt()) {
            TreeNode pop = q.pop();
            int x = scanner.nextInt();
            
            if (x != -1) {
                pop.left = new TreeNode(x);
                q.add(pop.left);
            }   
            if (!scanner.hasNextInt()) {
                break;
            }            
            int y = scanner.nextInt();
            if (y != -1) {
                pop.right = new TreeNode(y);
                q.add(pop.right);
            }            
        }
        return root;
    }
}
