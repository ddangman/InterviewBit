/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

/**
 *
 * @author Dang
 */
public class FourSum {

    ArrayList<ArrayList<Integer>> result;
    ArrayList<Integer> input;
    int length;
    int max;

    public ArrayList<ArrayList<Integer>> fourSum(ArrayList<Integer> input, int target) {
        result = new ArrayList<>();
        this.input = input;

        length = input.size();
        if (length < 4) {
            return result;
        }

        Collections.sort(input);

        max = input.get(input.size() - 1);
        if (target < 4 * input.get(0) || 4 * max < target) {
            return result;
        }

        for (int i = 0; i < length; i++) {
            int a = input.get(i);
            if (i > 0 && input.get(i - 1) == a) { // avoid duplicate
                continue;
            }
            if (a + 3 * max < target) { // x is too small
                continue;
            }
            if (4 * a > target) { // x is too large
                break;
            }
            // x is the boundary
            if (4 * a == target && i + 3 < length && input.get(i + 3) == a) {
                result.add(new ArrayList<>(Arrays.asList(a, a, a, a)));
                break;
            }

            threeSumForFourSum(i + 1, target - a, a);
        }
        return result;
    }

    /*
     * Find all possible distinguished three numbers adding up to the target
     * in sorted array input between indices low and length. 
     * If there are, add all of them into the ArrayList result.
     */
    public void threeSumForFourSum(int low, int target, int a) {
        if (low + 2 > length) {
            return;
        }

        if (target < 3 * input.get(low) || 3 * max < target) {
            return;
        }

        for (int i = low; i < length - 2; i++) {
            int b = input.get(i);
            if (i > low && b == input.get(i - 1)) { // avoid duplicate
                continue;
            }
            if (b + 2 * max < target) { // y is too small
                continue;
            }
            if (3 * b > target) { // y is too large
                break;
            }
            // y is the boundary
            if (3 * b == target && i + 3 < length && input.get(i + 3) == b) {
                result.add(new ArrayList<>(Arrays.asList(a, b, b, b)));
                break;
            }

            twoSumForFourSum(i + 1, target - b, a, b);
        }
    }

    /*
     * Find all possible distinguished two numbers adding up to the target
     * in sorted array input between indices low and length. 
     * If there are, add all of them into the ArrayList result.
     */
    public void twoSumForFourSum(int low, int target, int a, int b) {
        if (low + 1 > length) {
            return;
        }

        if (2 * input.get(low) > target || 2 * max < target) {
            return;
        }

        int i = low;
        int j = length - 1;
        while (i < j) {
            int c = input.get(i);
            int d = input.get(j);
            int sum = c + d;
            if (sum == target) {
                result.add(new ArrayList<>(Arrays.asList(a, b, c, d)));

                // avoid duplicate
                while (++i < j && c == input.get(i)) {

                }
                while (i < --j && d == input.get(j)) {

                }
            }

            if (sum < target) {
                i++;
            }
            if (sum > target) {
                j--;
            }
        }
    }

    // Solution using HashSet
    public ArrayList<ArrayList<Integer>> fourSumHash(ArrayList<Integer> input, int target) {
        Collections.sort(input);
        HashSet<ArrayList<Integer>> set = new HashSet<>();
        ArrayList<ArrayList<Integer>> solution = new ArrayList<>();

        int li = input.size() - 1; // last index
        int lv = input.get(li); // last value
        int a, b, c, d, i, j, k, l, diff, sum;
        for (i = 0; i < input.size() - 3; i++) {
            a = input.get(i);
            if (3 * lv + a < target) { // a too low
                continue;
            }
            for (j = i + 1; j < input.size() - 2; j++) {
                b = input.get(j);
                diff = target - a - b;
                if (diff + 2 * lv < 0) { // diff too low
                    continue;
                }
                k = j + 1;
                l = li;
                while (k < l) {
                    c = input.get(k);
                    d = input.get(l);
                    sum = diff - c - d;
                    if (sum == 0) {
                        ArrayList<Integer> temp = new ArrayList<>(Arrays.asList(a, b, c, d));
                        if (!set.contains(temp)) {
                            solution.add(temp);
                            set.add(temp);
                        }

                        k++;
                        l--;
                    } else if (sum > 0) { // c is too low
                        k++;
                    } else if (sum < 0) { // d is too high
                        l--;
                    }
                }
            }
        }
        return solution;
    }

    // HashSet solution modified to skip duplicates
    public ArrayList<ArrayList<Integer>> fourSumCondensed(ArrayList<Integer> input, int target) {
        Collections.sort(input);
        ArrayList<ArrayList<Integer>> solution = new ArrayList<>();

        int li = input.size() - 1; // last index
        int lv = input.get(li); // last value
        int a, b, c, d, i, j, k, l, diff, sum;
        for (i = 0; i < input.size() - 3; i++) {
            a = input.get(i);
            if (3 * lv + a < target) { // a too low
                continue;
            }
            if (i > 0 && a == input.get(i - 1)) { // skip duplicate
                continue;
            }
            for (j = i + 1; j < input.size() - 2; j++) {
                b = input.get(j);
                diff = target - a - b;
                if (diff + 2 * lv < 0) { // diff too low
                    continue;
                }
                if (j > i + 1 && b == input.get(j - 1)) { // skip duplicate
                    continue;
                }
                k = j + 1;
                l = li;
                while (k < l) {
                    c = input.get(k);
                    if (k > j + 1 && c == input.get(k - 1)) { // skip duplicate
                        k++;
                        continue;
                    }
                    d = input.get(l);
                    if (l < li && d == input.get(l + 1)) { // skip duplicate
                        l--;
                        continue;
                    }
                    sum = diff - c - d;
                    if (sum == 0) {
                        ArrayList<Integer> temp = new ArrayList<>(Arrays.asList(a, b, c, d));
                        solution.add(temp);

                        k++;
                        l--;
                    } else if (sum > 0) { // c is too low
                        k++;
                    } else if (sum < 0) { // d is too high
                        l--;
                    }

                }
            }
        }
        return solution;
    }
}
