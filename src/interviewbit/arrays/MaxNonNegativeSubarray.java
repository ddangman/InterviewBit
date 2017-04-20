/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.arrays;

import java.util.ArrayList;

/**
 *
 * @author Dang
 */
public class MaxNonNegativeSubarray {

    public ArrayList<Integer> maxset(ArrayList<Integer> a) {
        ArrayList<Integer> maxArray = new ArrayList<>();
        int startIndex = 0;
        long arrLength = 0;
        long maxSum = 0;
        long runLength = 0;
        long runSum = 0;
        int runIndex = 0;

        for (int i = 0; i < a.size(); i++) {
            if (a.get(i) < 0 ) { // reset run
                // save best
                if (runSum > maxSum) {
                    maxSum = runSum;
                    arrLength = runLength;
                    startIndex = runIndex;
                } else if (runSum == maxSum && runLength > arrLength) {
                    // tied sum
                    arrLength = runLength;
                    startIndex = runIndex;
                }
                // reset run variables
                runLength = 0;
                runSum = 0;

            } else { // continue run
                if (runLength == 0) {
                    // new run
                    runIndex = i;
                }
                runLength++;
                runSum += a.get(i);
            }
        }

        if (runSum > maxSum) {
            maxSum = runSum;
            arrLength = runLength;
            startIndex = runIndex;
        } else if (runSum == maxSum && runLength > arrLength) {
            // tied sum
            arrLength = runLength;
            startIndex = runIndex;
        }

        // create maxArray
        for (int i = startIndex; i < startIndex + arrLength; i++) {
            maxArray.add(a.get(i));
        }
        return maxArray;
    }

}
