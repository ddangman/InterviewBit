/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.arrays;

import java.util.ArrayList;

/**
 *
 * @author Dang
 */
public class MaxAbsoluteDiff {

    public int maxArr(ArrayList<Integer> A) {
        int minAdd = Integer.MAX_VALUE;
        int maxAdd = Integer.MIN_VALUE;
        int minSub = Integer.MAX_VALUE;
        int maxSub = Integer.MIN_VALUE;
        for (int i = 0; i < A.size(); i++) {
            int add = A.get(i) + i;
            int sub = A.get(i) - i;
            if (add > maxAdd) {
                maxAdd = add;
            }
            if (add < minAdd) {
                minAdd = add;
            }
            if (sub > maxSub) {
                maxSub = sub;
            }
            if (sub < minSub) {
                minSub = sub;
            }
        }
        int x = maxAdd - minAdd;
        int y = maxSub - minSub;
        return x > y ? x : y;
    }

}
