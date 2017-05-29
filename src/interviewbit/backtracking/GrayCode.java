/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.backtracking;

import java.util.ArrayList;

/**
 *
 * @author Dang
 */
public class GrayCode {

    // The idea is simple. G(i) = i^ (i/2).
    public ArrayList<Integer> grayCode(int n) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < 1 << n; i++) {
            result.add(i ^ i >> 1);
        }
        return result;
    }

}
