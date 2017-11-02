/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.arrays;

import java.util.ArrayList;

/**
 * Given an array of real numbers greater than zero in form of strings.
 * Find if there exists a triplet (a,b,c) between 1 and 2. 
 * Return 1 for true or 0 for false. 
 *
 * @author Duy Dang
 */
public class TripletsWithSumBetweenRange {

    public int solve(ArrayList<String> A) {
        int i = 0;
        double a = 0, b = 0, c = 0;
        while (i < A.size()) {
            if (a == 0) {
                a = Double.parseDouble(A.get(i));
            } else if (b == 0) {
                b = Double.parseDouble(A.get(i));
            } else if (c == 0) {
                c = Double.parseDouble(A.get(i));
            }
            if ((a != 0) && (b != 0) && (c != 0)) {
                if ((a + b + c > 1) && (a + b + c < 2)) {
                    //System.out.println(a+b+c);
                    return 1;
                } else {
                    if (a + b + c < 1) {
                        // underflow: drop lowest double
                        if ((a < b) && (a < c)) {
                            a = 0;
                        } else if ((b < a) && (b < c)) {
                            b = 0;
                        } else {
                            c = 0;
                        }
                    } else {
                        // overflow: drop greatest double
                        if ((a > b) && (a > c)) {
                            a = 0;
                        } else if ((b > a) && (b > c)) {
                            b = 0;
                        } else {
                            c = 0;
                        }
                    }
                }
            }
            i++;
        }
        return 0;
    }
}
