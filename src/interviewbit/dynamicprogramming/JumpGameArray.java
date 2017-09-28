/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.dynamicprogramming;

import java.util.ArrayList;

/**
 *
 * @author Dang
 */
public class JumpGameArray {

    public int canJump(ArrayList<Integer> a) {
        int n = a.size();
        boolean[] canLand = new boolean[n];
        canLand[0] = true;
        for (int i = 0; i < n; i++) {
            int count = a.get(i);
            for (int j = 0; j <= count && i + j < n; j++) {
                if (!canLand[i]) {
                    break;
                }
                canLand[i + j] = true;
            }
        }

        return canLand[n - 1] ? 1 : 0;
    }
}
