/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.math;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Dang
 */
public class PointsInsideRectangle {

    public int solve(ArrayList<Integer> A, ArrayList<Integer> B, ArrayList<Integer> C, ArrayList<Integer> D) {
        double areaR = area(A, B);
        int count = 0;
        for (int i = 0; i < C.size(); i++) {
            double areaP = triArea(A, B, C.get(i), D.get(i));
            if (areaP == areaR) {
                count++;
            }
        }
        return count;
    }

    private double area(ArrayList<Integer> A, ArrayList<Integer> B) {
        double sum = 0.0;
        int n = A.size();
        for (int i = 0; i < n; i++) {
            sum += crossProduct(A.get(i), B.get(i), A.get((i + 1) % n), B.get((i + 1) % n));
        }
        sum = sum > 0.0 ? sum / 2 : sum / -2;
        return sum;
    }

    // creates triangles with vertex point and returns total area
    private double triArea(ArrayList<Integer> A, ArrayList<Integer> B, int x, int y) {
        double sum = 0.0;
        for (int i = 0; i < A.size(); i++) {
            ArrayList<Integer> m = new ArrayList<>(Arrays.asList(A.get(i), A.get((i + 1) % 4), x));
            ArrayList<Integer> n = new ArrayList<>(Arrays.asList(B.get(i), B.get((i + 1) % 4), y));
            double area = area(m, n);
            sum += area;
        }
        return sum;
    }

    private double crossProduct(int ax, int ay, int bx, int by) {
        return (ax * by) - (ay * bx);
    }

    // positive means left of line
    // negative means right of line
    // zero is inline    
    private double rightHandRule(int ax, int ay, int bx, int by, int px, int py) {
        // modify line ab so a is at origin
        int baseX = bx - ax;
        int baseY = by - ay;
        int pointX = px - ax;
        int pointY = py - ay;
        return crossProduct(baseX, baseY, pointX, pointY);
    }

}
