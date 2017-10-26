/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.dynamicprogramming;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Given an array with non negative numbers, divide the array into two parts
 * such that the average of both the parts is equal. Return both parts (if
 * possible). If there is no solution. return an empty list.
 * 
 * sA = sum of partition A, sB = sum of partition B
 * nA = number of items in partition A, nB = number of items in partition B
 * Equal averages means: sA/nA == sB/nB
 * 
 * s_all == sA + sB 
 * n_all == nA + nB
 * (sA + sB) / (nA + nB) == s_all / n_all
 * 
 * Logic:
 * 1. Find a valid sumA/nA. It will equal s_all/n_all
 * 2. Find exactly nA inputValues that will add up to exactly sumA (0/1 knapsack)
 *    Use greedy so nA is smallest. Initialize nA as 1.
 *    Sort input so output is sorted. Start with sortedInput(0) to maintain order.
 *
 * @author Duy Dang
 */
public class EqualAveragePartition {

    private ArrayList<Integer> array;
    // memoization table by three states : 
    // [index of sorted array] [sum of array_A] [size of array_A]
    private boolean[][][] dp;
    private ArrayList<Integer> indexSetA;
    private int n;

    public ArrayList<ArrayList<Integer>> avgset(ArrayList<Integer> array) {
        if (array == null || array.isEmpty()) { // edge case
            return new ArrayList<>();
        }

        this.array = array;
        this.n = array.size();
        this.indexSetA = new ArrayList<>();

        Collections.sort(this.array); // guarantees lexicographic order

        int sum = 0; // get summation of array
        for (int element : array) {
            sum += element;
        }

        // [index of sorted array] [sum of array_A] [size of array_A]
        this.dp = new boolean[n][1 + sum][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 1 + sum; j++) {
                for (int k = 0; k < n; k++) {
                    // initialize as true so invalid states won't be recalculated
                    this.dp[i][j][k] = true;
                }
            }
        }

        // iterate for third state : size of setA which varies from 1 to n-1
        for (int sizeA = 1; sizeA < n; sizeA++) { // sizeA should not be zero
            if ((sum * sizeA) % n != 0) {
                continue; // sumA/sizeA != sumAll/sizeAll if remainder exists
            }

            // sumA/sizeA == sumAll/sizeAll
            int sumA = (sum * sizeA) / n;

            if (isPartitionPossible(0, sumA, sizeA) == true) {
                break;
            }
        }

        return generatePartitions();
    }

    private ArrayList<ArrayList<Integer>> generatePartitions() {
        int e = 0, i = 0;
        int sizeA = this.indexSetA.size();

        if (sizeA == n || sizeA == 0) {   // no solution exists
            return new ArrayList<>();
        }

        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        result.add(new ArrayList<>());

        // index e is used to iterate over all elements and
        // index i is used to iterate over indexSetA
        while (e < n && i < sizeA) {
            if (e == this.indexSetA.get(i)) {
                result.get(0).add(this.array.get(e));
                i++;
            } else {
                result.get(1).add(this.array.get(e));
            }
            e++;
        }

        while (e < n) {
            result.get(1).add(this.array.get(e));
            e++;
        }

        return result;
    }

    // attempt to reach target sum using available elements 
    // (elements may be skipped as needed). Top-down recursion with memoization
    private boolean isPartitionPossible(final int index, final int sumA, final int sizeA) {
        if (sizeA == 0) { // base case
            return sumA == 0;
        }
        if (index >= n) { // index exceeds number of items
            return false;
        }
        if (this.dp[index][sumA][sizeA] == false) { // return memoize
            return false;
        }

        int element = this.array.get(index);
        if (sumA >= element) {
            // include the current index i.e. include the current element in setA
            this.indexSetA.add(index);
            if (isPartitionPossible(index + 1, sumA - element, sizeA - 1) == true) {
                return true;
            }
            // current element should not be in solution 
            this.indexSetA.remove(this.indexSetA.size() - 1);
        }

        if (isPartitionPossible(index + 1, sumA, sizeA)) {
            // skip the current index i.e. don't include the current element in setA
            return true;
        }

        // reaching this line means partition is NOT possible
        this.dp[index][sumA][sizeA] = false;
        return this.dp[index][sumA][sizeA];
    }

    public static class moreEfficient {
        // same logic as outer class using int dp[][][] = 0 for unvisited,
        // 1 for true, -1 for false

        private int n;
        private int sumAll;
        private int[][][] dp;
        private ArrayList<Integer> sorted;
        private ArrayList<Integer> indexA;
        // 1 is possible, -1 is invalid, 0 is undetermined
        private ArrayList<ArrayList<Integer>> result;

        public ArrayList<ArrayList<Integer>> avgset(ArrayList<Integer> A) {
            this.result = new ArrayList<>();
            if (A == null || A.isEmpty()) {
                return result;
            }
            Collections.sort(A);
            this.n = A.size();
            this.sorted = A;
            this.indexA = new ArrayList<>();
            this.sumAll = sorted.stream().mapToInt(Integer::intValue).sum();
            // build matrix: index, sum of array, size of array
            this.dp = new int[n][sumAll][n];

            for (int size1 = 1; size1 < n; size1++) {
                if ((size1 * sumAll) % n != 0) {
                    continue;
                }

                int sum1 = (sumAll * size1) / n;

                if (isPossible(0, sum1, size1)) {
                    generateResults();
                    break;
                }
            }

            return result;
        }

        private boolean isPossible(int index, int sum1, int size1) {
            if (size1 == 0) {
                return sum1 == 0;
            }
            if (index >= n) {
                return false;
            }
            if (dp[index][sum1][size1] != 0) {
                return dp[index][sum1][size1] == 1;
            }

            int element = sorted.get(index);
            if (sum1 >= element) { // use element if sum allows
                indexA.add(index);
                // increment index, reduce sum1 by element, decrement size1
                if (isPossible(index + 1, sum1 - element, size1 - 1)) {
                    dp[index][sum1][size1] = 1;
                    return true;
                }
                indexA.remove(indexA.size() - 1);
            }
            // increment index, no change to sum1, no change to size1
            if (isPossible(index + 1, sum1, size1)) {
                dp[index][sum1][size1] = 1;
                return true;
            }

            dp[index][sum1][size1] = -1;
            return false;
        }

        private void generateResults() {
            ArrayList<Integer> setA = new ArrayList<>();
            ArrayList<Integer> setB = new ArrayList<>();
            int ptr = 0;
            int as = indexA.size();
            for (int i = 0; i < n; i++) {
                if (ptr < as && i == indexA.get(ptr)) {
                    setA.add(sorted.get(i));
                    ptr++;
                } else {
                    setB.add(sorted.get(i));
                }
            }

            result.add(setA);
            result.add(setB);
        }
    }

    public static class noMemoization {

        public ArrayList<ArrayList<Integer>> avgset(ArrayList<Integer> array) {
            ArrayList<ArrayList<Integer>> result = new ArrayList<>();

            Set<Integer> aIndex = new HashSet<>();

            int N = array.size();

            int totalSum = 0;
            for (int i = 0; i < N; i++) {
                totalSum += array.get(i);
            }

            Collections.sort(array);

            for (int aSize = 1; aSize < N; aSize++) {
                float aSum = (float) totalSum * aSize / N;
                if (aSum != (int) aSum) {
                    continue;
                }

                for (int i = 0; i < N; i++) {
                    if (find(i, aSize, aSum, array, aIndex)) {
                        buildResult(result, array, aIndex);
                        return result;
                    }
                }
            }
            return result;
        }

        private boolean find(int index, int aSize, float aSum, ArrayList<Integer> array, Set<Integer> a) {
            if (index >= array.size() || aSize == 0) {
                return false;
            }

            int value = array.get(index);
            if (aSize == 1 && value == aSum) {
                a.add(index);
                return true;
            }

            if (find(index + 1, aSize - 1, aSum - value, array, a)) {
                a.add(index);
                return true;
            } else if (find(index + 1, aSize, aSum, array, a)) {
                return true;
            }

            return false;
        }

        private void buildResult(ArrayList<ArrayList<Integer>> result, ArrayList<Integer> array,
                Set<Integer> aIndex) {
            ArrayList<Integer> a = new ArrayList<>();
            ArrayList<Integer> b = new ArrayList<>();
            for (int i = 0; i < array.size(); i++) {
                if (aIndex.contains(i)) {
                    a.add(array.get(i));
                } else {
                    b.add(array.get(i));
                }
            }
            result.add(a);
            result.add(b);
        }
    }
}
