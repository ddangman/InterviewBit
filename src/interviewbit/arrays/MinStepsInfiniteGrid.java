/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.arrays;

import java.util.ArrayList;

/**
 *
 * @author Dang
 */
public class MinStepsInfiniteGrid {
    // X and Y co-ordinates of the points in order.
    // Each point is represented by (X.get(i), Y.get(i))

    public int coverPoints(ArrayList<Integer> X, ArrayList<Integer> Y) {
        int steps = 0;

        for (int i = 1; i < X.size(); i++) {
            int x1 = X.get(i);
            int x2 = X.get(i-1);
            int y1 = Y.get(i);
            int y2 = Y.get(i-1);
            int xd = x1>x2 ? x1-x2 : x2-x1;
            int yd = y1>y2 ? y1-y2 : y2-y1;
            steps += xd>yd ? xd : yd;

        }
        return steps;
    }

}
