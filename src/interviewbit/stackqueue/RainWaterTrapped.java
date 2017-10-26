/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.stackqueue;

import java.util.List;
import utilities.ArrayUtilities;

/**
 *
 * @author Dang
 */
public class RainWaterTrapped {

    public int trap(final List<Integer> a) {
    /* calculate the walls that will hold the water */
        // contains height of tallest bar to left + including i
        int[] leftWall = new int[a.size()];
        // fill left[]
        leftWall[0] = a.get(0);
        for (int i = 1; i < a.size(); i++) {
            leftWall[i] = Math.max(leftWall[i - 1], a.get(i));
        }

        // contains height of tallest bar to right + including i
        int[] rightWall = new int[a.size()];
        // fill right[]
        rightWall[a.size() - 1] = a.get(a.size() - 1);
        for (int i = a.size() - 2; i >= 0; i--) {
            rightWall[i] = Math.max(rightWall[i + 1], a.get(i));
        }

    /* water level at each index will be height of shortest wall minus height at that index */
        int water = 0;
        // Calculate the accumulated water element by element
        // consider the amount of water on i'th bar, the
        // amount of water accumulated on this particular
        // bar will be equal to min(left[i], right[i]) - arr[i] .
        for (int i = 0; i < a.size(); i++) {
            water += Math.min(leftWall[i], rightWall[i]) - a.get(i);
        }

        return water;
    }

    private int trapDebug(final List<Integer> a) {
        // contains height of tallest bar to left + including i
        int[] left = new int[a.size()];
        // fill left[]
        left[0] = a.get(0);
        for (int i = 1; i < a.size(); i++) {
            left[i] = Math.max(left[i - 1], a.get(i));
        }

        // contains height of tallest bar to right + including i
        int[] right = new int[a.size()];
        // fill right[]
        right[a.size() - 1] = a.get(a.size() - 1);
        for (int i = a.size() - 2; i >= 0; i--) {
            right[i] = Math.max(right[i + 1], a.get(i));
        }

        int water = 0;
        // Calculate the accumulated water element by element
        // consider the amount of water on i'th bar, the
        // amount of water accumulated on this particular
        // bar will be equal to min(left[i], right[i]) - arr[i] .
        for (int i = 0; i < a.size(); i++) {
            int n = Math.min(left[i], right[i]) - a.get(i);
            System.out.print(n + " ");
            water += n;
        }

        // debug use only
        System.out.println("");
        ArrayUtilities.printArray(left);
        ArrayUtilities.printArray(right);

        return water;
    }

}
