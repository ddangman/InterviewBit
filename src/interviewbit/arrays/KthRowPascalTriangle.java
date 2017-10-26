/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.arrays;

import java.util.ArrayList;

/**
 *
 * @author Dang
 */
public class KthRowPascalTriangle {

    public ArrayList<Integer> getRow(int a) {
        ArrayList<Integer> row = new ArrayList<>();
        row.add(1);
        if (a == 0) {
            return row;
        }
        if (a == 1) {
            row.add(1);
            return row;
        }  
        if (a == 2) {
            row.add(2);
            row.add(1);
            return row;
        }   
        row.add(a);
        int mid = (a / 2) + 1;
        for (int i = 3; i <= mid; i++) {
            row.add(rowUtil(a, i-1));
        }

        // mirror
        if (a % 2 == 1) {
            for (int i = a / 2; i >= 0; i--) {
                row.add(row.get(i));
            }
        } else {
            for (int i = (a / 2) - 1; i >= 0; i--) {
                row.add(row.get(i));
            }
        }
        return row;
    }

    private int rowUtil(int row, int col) {
        long fact = row;
        int diff = row - col;
        int hi = 0;
        int lo = 0;
        if (diff > col) {
            hi = diff;
            lo = col;
        } else {
            hi = col;
            lo = diff;
        }
        for (int i = row - 1; i > hi; i--) {
            fact *= i;
        }
        long den = lo;
        for (int i = lo - 1; i > 1; i--) {
            den *= i;
        }
        fact /= den;
        return (int) fact;
    }
}
