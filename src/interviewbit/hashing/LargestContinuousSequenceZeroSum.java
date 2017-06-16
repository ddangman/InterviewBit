/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 *
 * @author Dang
 */
public class LargestContinuousSequenceZeroSum {

    // hashing solution
    public ArrayList<Integer> lszero(ArrayList<Integer> a) {
        // holds sum, index
        HashMap<Integer, Integer> map = new HashMap<>();
        int start = 0;
        int end = 0;
        int max = 0;
        int sum = a.get(0);
        for (int i = 1; i <= a.size(); i++) {
            // valid length (from 0 to i) if sum is 0
            if (sum == 0 && i > max) {
                start = 0;
                end = i;
                max = i + 1;
            }
            
            // valid length (from map index to i) if sum already exists in map
            Integer iMap = map.get(sum);
            if (iMap != null && i - iMap > max) {
                end = i;
                max = i - iMap;
                start = iMap + 1;
            } else if (iMap == null) {
                map.put(sum, i - 1);
            }
            
            if (i < a.size()) {
                sum += a.get(i);
            }
        }
        if (end == 0) {

            return new ArrayList<>();
        }
        return new ArrayList<>(a.subList(start, end));
    }

    // dynamic programming solution
    public ArrayList<Integer> lszeroDP(ArrayList<Integer> a) {
        boolean returnZero = false;
        int start = 0;
        int end = 0;
        int max = 0;

        int[] sum = new int[a.size()];
        sum[0] = a.get(0);
        if (sum[0] == 0) {
            returnZero = true;
        }
        for (int i = 1; i < a.size(); i++) {
            sum[i] = sum[i - 1] + a.get(i);
            if (a.get(i) == 0) {
                returnZero = true;
            }
            if (sum[i] == 0 && i > max) {
                end = i + 1;
                max = i + 1;
            }
        }

        for (int i = 0; i < a.size() - 1; i++) {
            for (int j = i + 1; j < a.size(); j++) {
                if (sum[i] == sum[j] && j - i > max) {
                    start = i + 1;
                    end = j + 1;
                    max = j - i;
                }
            }
        }

        if (end == 0) {
            if (returnZero) {
                return new ArrayList<>(Arrays.asList(0));
            }
            return new ArrayList<>();
        }
        return new ArrayList<>(a.subList(start, end));
    }

    // brute force solution rolling sum left and right
    public ArrayList<Integer> lszeroBF(ArrayList<Integer> a) {
        int start, end, max, i, j;
        end = max = i = j = 0;
        boolean forward = true;
        int sum = a.get(i);
        int n = start = a.size() - 1;

        while (i != 0 || j != n) {
            if (forward && j == n) {
                forward = !forward;
                sum += a.get(--i);
            } else if (!forward && i == 0) {
                forward = !forward;
                sum += a.get(++j);
            } else if (forward) {
                sum -= a.get(i++);
                sum += a.get(++j);
            } else if (!forward) {
                sum -= a.get(j--);
                sum += a.get(--i);
            }

            if (sum == 0 && (j - i > max || (j - i == max && i <= start))) {
                max = j - i;
                start = i;
                end = j + 1;
            }
        }

        if (end == 0) {
            if (a.contains(0)) {
                return new ArrayList<>(Arrays.asList(0));
            }
            return new ArrayList<>();
        }
        return new ArrayList<>(a.subList(start, end));
    }
}
