/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.arrays;

import java.util.ArrayList;

/**
 *
 * @author Dang
 */
public class FindPermutation {

    public ArrayList<Integer> findPerm(final String A, int B) {
        // auxillary arrays holding range
        int[] hiA = new int[B];
        int[] loA = new int[B];

        hiA[0] = B;
        loA[0] = 1;

        for (int i = 1; i < B; i++) {
            if (A.charAt(i-1) == 'D') {
                // decrease high range
                hiA[i] = hiA[i - 1] - 1;
                // maintain low range
                loA[i] = loA[i - 1];
            } else {
                // maintain high range
                hiA[i] = hiA[i - 1];
                // increase low range
                loA[i] = loA[i - 1] + 1;
            }
        }

        ArrayList<Integer> ar = new ArrayList<>(B);
        for (int i = 0; i < B; i++) {
            ar.add(0);
        }

        if (A.charAt(A.length() - 1) == 'D') {
            ar.set(A.length(), hiA[B-1]);
        } else {
            ar.set(A.length(),loA[B-1]);
        }
        for (int i = A.length() - 1; i >= 0; i--) {
            // i is index of String A
            if (A.charAt(i) == 'D') {
                ar.set(i, hiA[i]);
            } else {
                ar.set(i, loA[i]);
            }
        }
        return ar;
    }

}
