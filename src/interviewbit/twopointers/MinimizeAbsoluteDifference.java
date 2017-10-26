/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.twopointers;

import java.util.ArrayList;

/**
 *
 * @author Dang
 */
public class MinimizeAbsoluteDifference {

    public int solve(ArrayList<Integer> A, ArrayList<Integer> B, ArrayList<Integer> C) {
        int res = Integer.MAX_VALUE;
        int a = A.size() - 1;
        int b = B.size() - 1;
        int c = C.size() - 1;
        do {
            int ad = absDiff(A.get(a), B.get(b), C.get(c));
            if (ad < res) {
                res = ad;
                if (res == 0) {
                    break;
                }
            }

            if (A.get(a) > B.get(b) && A.get(a) > C.get(c) && a > 0) {
                a--;
            } else if (B.get(b) > A.get(a) && B.get(b) > C.get(c) && b > 0) {
                b--;
            } else if (C.get(c) > A.get(a) && C.get(c) > B.get(b) && c > 0) {
                c--;
            } else if (A.get(a) >= B.get(b) && a > 0) {
                a--;
            } else if (B.get(b) >= C.get(c) && b > 0) {
                b--;
            } else if (c > 0) {
                c--;
            } else if (b > 0) {
                b--;
            } else if (a > 0) {
                a--;
            }

        } while (a != 0 || b != 0 || c != 0);
        return res;
    }

    private int absDiff(int a, int b, int c) {
        int max;
        int min;
        if (a > b) {
            max = a;
            min = b;
        } else {
            max = b;
            min = a;
        }
        if (c > max) {
            max = c;
        }
        if (c < min) {
            min = c;
        }

        return Math.abs(max - min);
    }

}
