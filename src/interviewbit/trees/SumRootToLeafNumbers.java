/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.trees;

import interviewbit.datastructures.TreeNode;
import java.math.BigInteger;

/**
 *
 * @author Dang
 */
public class SumRootToLeafNumbers {

    BigInteger sum;

    public int sumNumbers(TreeNode root) {
        sum = BigInteger.ZERO;

        addNodes(root, new StringBuilder());
        
        BigInteger mod = sum.mod(BigInteger.valueOf(1003));
        return mod.intValue();
    }
    
    public void addNodes(TreeNode node, StringBuilder sb) {
        if (node == null) { // exit condition
            return;
        }     
        
        sb.append(node.val);
        if (node.left == null && node.right == null) {  
            sum = sum.add(new BigInteger(sb.toString()));
        }  
        
        addNodes(node.left, sb);
        addNodes(node.right, sb);
        
        // remove node.val from shared StringBuilder
        sb.setLength(sb.length() - 1);
    }

}
