/*
 * The real problem in this question is to find largest contiguous sub array 
 * for which difference 'numberOfZeros - numberOfOnes' is the biggest 
 * to maximize the sum of ones after flipping zeros. 
 */
package interviewbit.arrays;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Dang
 */
public class Flip {

    public ArrayList<Integer> flip(String s) {
        int start = 0;
        int startRun = 0;
        int end = 0;
        int max = Integer.MIN_VALUE; 
        int count = 0; // counts net gain in 1's
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                // increase set bit if flip
                count++;
            } else {
                count--; // clear bit if flip, decrease set count
            }
            if (count > max) {
                max = count; // update new max
                end = i; // save end index
                start = startRun; // save start index
            }  
            if (count < 0) {
                // reset subarray run
                startRun = i + 1; // potential start index
                count = 0;
            }
        }

        if (max == -1) { // all bits already set
            return new ArrayList<Integer>();
        }

        return new ArrayList<Integer>(Arrays.asList(start + 1, end + 1));
    }

    // used for debugging
    public ArrayList<Integer> flipPrint(String s) {
        int start = 0;
        int startRun = 0;
        int end = 0;
        int max = 0;
        int count = 0;
        int origOnes = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                // increase set bit if flip
                count++;
            } else {
                count--; // clear bit if flip, decrease set count
                origOnes++; // count original ones
            }
            System.out.print(i + " count:" + count);
            if (count > max) {
                max = count; // update new max
                end = i; // save end index
                start = startRun; // save start index
                System.out.print(" max:" + max + " end:" + end + " start:" + start);
            } else if (count < 0) {
                // reset subarray run
                startRun = i + 1; // potential start index
                count = 0;
                System.out.print("reset! sr:" + startRun);
            }
            System.out.println("");
        }

        if (origOnes == s.length()) {
            return new ArrayList<Integer>();
        }

        return new ArrayList<Integer>(Arrays.asList(start + 1, end + 1));
    }

    // modified Kadane's algorithm
    // https://www.careercup.com/question?id=6262507668766720
    public ArrayList<Integer> flipBits(String a) {

        int maxDiff = 0;
        int flipStartIndex = 0;
        int flipEndIndex = 0;
        int onesToFlip = 0;
        int totalNumberOfOnes = 0;

        int currentDiff = 0;
        int currentStart = 0;
        int currentOnesToFlip = 0;

        for (int i = 0; i < a.length(); i++) {
            // converts 0 to -1 and computes the sum 
            // till the sum is <=0 (there is more 0s then 1s). 
            if (a.charAt(i) == '0') {
                currentDiff -= 1;
            } else {
                currentDiff += 1;
                currentOnesToFlip++;
                totalNumberOfOnes++;
            }
            if (currentDiff < maxDiff) {
                maxDiff = currentDiff;
                flipStartIndex = currentStart;
                flipEndIndex = i;
                onesToFlip = currentOnesToFlip;
            } else if (currentDiff > 0) {
                // If the sum is > 0 starts from the beginning
                currentDiff = 0;
                currentStart = i + 1;
                currentOnesToFlip = 0;
            }
        }
        if (totalNumberOfOnes == a.length()) {
            return new ArrayList<>();
        }
        return new ArrayList<>(Arrays.asList(flipStartIndex + 1, flipEndIndex + 1));
    }

    public ArrayList<Integer> flipBitsPrint(String a) {

        int maxDiff = 0;
        int flipStartIndex = 0;
        int flipEndIndex = 0;
        int onesToFlip = 0;
        int totalNumberOfOnes = 0;

        int currentDiff = 0;
        int currentStart = 0;
        int currentOnesToFlip = 0;

        for (int i = 0; i < a.length(); i++) {
            System.out.print(i);
            // converts 0 to -1 and computes the sum 
            // till the sum is <=0 (there is more 0s then 1s). 
            if (a.charAt(i) == '0') {
                currentDiff -= 1;
            } else {
                currentDiff += 1;
                currentOnesToFlip++;
                totalNumberOfOnes++;
            }
            System.out.print("  cDiff:" + currentDiff
                    + "  c1F:" + currentOnesToFlip
                    + "  tot1:" + totalNumberOfOnes);
            if (currentDiff < maxDiff) {
                maxDiff = currentDiff;
                flipStartIndex = currentStart;
                flipEndIndex = i;
                onesToFlip = currentOnesToFlip;
                System.out.print("  maxD:" + maxDiff
                        + "  returnStart:" + flipStartIndex
                        + "  returnEnd:" + flipEndIndex);
            } else if (currentDiff > 0) {
                // If the sum is > 0 starts from the beginning
                currentDiff = 0;
                currentStart = i + 1;
                currentOnesToFlip = 0;
                System.out.print("  maxDiff !> cDiff> 0: reset cDiff, cStart, c1F");
            }
            System.out.println("");
        }
        if (totalNumberOfOnes == a.length()) {
            return new ArrayList<>();
        }
        return new ArrayList<>(Arrays.asList(flipStartIndex + 1, flipEndIndex + 1));
    }
}
