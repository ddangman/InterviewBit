package interviewbit;

/**
 * Main class for running InterviewBit solutions.
 *
 * @author Dang
 */
public class InterviewBit {

    public static void main(String[] args) {
        int[] nums = {3, 1, 5, 8};
        maxBalloonBottomUpDp(nums);
    }

        public static int maxBalloonBottomUpDp(int[] nums) {

        int T[][] = new int[nums.length][nums.length];

        for (int len = 1; len <= nums.length; len++) {
            for (int s = 0; s <= nums.length - len; s++) { // s: start index
                int e = s + len - 1; // e: end index relative to subsequence length
                // k cuts the length (1') subsequence into a 2' subsequence
                // matrix stores the values of length (1') subsequence from s-e
                // 1' subsequence == 2' subsequence before k + 2' subsequence after k
                // + (balloon before length * balloon k * balloon after length)
                for (int k = s; k <= e; k++) { // k: last balloon to burst
                    //leftValue/rightValue is initially 1. If there is element on
                    // left/right of k then left/right value will take that value.
                    int leftValue = 1; //balloon value to left
                    int rightValue = 1; // balloon value to right
                    if (s != 0) {
                        leftValue = nums[s-1]; // balloon before length
                    }
                    if (e != nums.length -1) {
                        rightValue = nums[e+1]; // balloon after length
                    }

                    //before is initially 0. If k is i then before will
                    //stay 0 otherwise it gets value T[i][k-1]
                    //after is similarly 0 initially. if k is j then after will
                    //stay 0 other will get value T[k+1][j]
                    //subsequence range must fall within bottom-up length
                    int before = 0; // subsequence before k
                    int after = 0; // subsequence after k
                    if (s != k) {
                        before = T[s][k-1];
                    }
                    if (e != k) {
                        after = T[k+1][e];
                    }
                    // keep previous max, or save current last balloon 'k'
                    T[s][e] = Math.max(T[s][e], 
                            leftValue * nums[k] * rightValue + before + after);
                }
            }
        }
        utilities.MatrixUtilities.print2DArray(T);
        return T[0][nums.length - 1];
    }
}
