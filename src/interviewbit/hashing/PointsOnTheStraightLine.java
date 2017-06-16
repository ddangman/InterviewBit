/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.hashing;

import java.util.ArrayList;
import java.util.HashMap;

public class PointsOnTheStraightLine {

    // brute force all possible slopes
    public int maxPoints(ArrayList<Integer> a, ArrayList<Integer> b) {
        if (a.size() <= 2) {
            return a.size();
        }
        int result = 0;
        for (int i = 0; i < a.size(); i++) {
            HashMap<Double, Integer> map = new HashMap<>();
            int samex = 1;
            for (int j = 0; j < a.size(); j++) {
                if (j != i) {
                    if (a.get(i) == a.get(j)) {
                        // calculate vertical line separately
                        // prevents divide by 0 exception
                        samex++;
                        continue;
                    }
                    // use slope as key, count as value
                    double key = (double) (b.get(i) - b.get(j))
                            / (double) (a.get(i) - a.get(j));
                    map.compute(key, (k, v) -> {
                        if (v != null) {
                            return v + 1;
                        }
                        return 2;
                    });
                    result = Math.max(result, map.get(key));
                }
            }
            result = Math.max(result, samex);
        }
        return result;
    }
}
