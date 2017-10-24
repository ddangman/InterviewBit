/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.dynamicprogramming;

/**
 * https://youtu.be/YDf982Lb84o
 * @author Duy Dang
 */
public class UniqueBinarySearchTrees {

    // how many structually unique BST for values 1, ..., limit
    public int numTrees(int limit) {
        int[] dp = new int[limit + 1];
        dp[0] = dp[1] = 1;
        
        // subproblem: find permutations of size 'n' in increasing order
        for (int n = 2; n <= limit; n++) {
            // sub-subproblem: solve for each number (1, ..., n) as root
            for (int root = 1; root <= n; root++) {
                // since values are in natural order,
                // permutations is product of leftChild and rightChild
                /*            (root)
                             /      \
                   (root - 1)        (n - root)                       */
                dp[n] += dp[root - 1] * dp[n - root];
            }
        }
        
        return dp[limit];
    }
}
