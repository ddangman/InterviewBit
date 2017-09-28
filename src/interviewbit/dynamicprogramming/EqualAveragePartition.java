/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.dynamicprogramming;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Duy Dang
 */
public class EqualAveragePartition {

    private ArrayList<Integer> array;
    private boolean[][][] dp;
    private ArrayList<Integer> indexSetA;
    private int n;

    public ArrayList<ArrayList<Integer>> avgset(ArrayList<Integer> array) {
        if (array == null || array.isEmpty()) {
            return new ArrayList<>();
        }

        this.array = array;
        Collections.sort(this.array);

        int sum = 0;
        for (int element : array) {
            sum += element;
        }

        this.n = array.size();

        // memoization table by three states : (index, sum of array, size of array)
        this.dp = new boolean[n][1 + sum][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 1 + sum; j++) {
                for (int k = 0; k < n; k++) {
                    // initialize as true so invalid states won't be recalculated
                    this.dp[i][j][k] = true;
                }
            }
        }

        this.indexSetA = new ArrayList<>();

        // iterate for third state : size of setA which varies from 1 to n-1
        for (int sizeA = 1; sizeA < n; sizeA++) { // sizeA should not be zero
            if ((sum * sizeA) % n != 0) {
                continue; // sizeA will yield invalid sumA
            }

            int sumA = (sum * sizeA) / n;

            if (isPartitionPossible(0, sumA, sizeA) == true) {
                break;
            }
        }

        return generatePartitions();
    }

    private boolean isPartitionPossible(final int index, final int sumA, final int sizeA) {
        if (sizeA == 0) {
            return sumA == 0;
        }
        if (index >= n) {
            return false;
        }
        if (this.dp[index][sumA][sizeA] == false) {
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

    private ArrayList<ArrayList<Integer>> generatePartitions() {
        int i = 0, j = 0;
        int sizeA = this.indexSetA.size();

        if (sizeA == n || sizeA == 0) {   // no solution exists
            return new ArrayList<>();
        }

        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        result.add(new ArrayList<>());

        // index i is used to iterate over all elements and index j is used to iterate over indexSetA
        while (i < n && j < sizeA) {
            if (i == this.indexSetA.get(j)) {
                result.get(0).add(this.array.get(i));
                j++;
            } else {
                result.get(1).add(this.array.get(i));
            }
            i++;
        }

        while (i < n) {
            result.get(1).add(this.array.get(i));
            i++;
        }

        return result;
    }

    static class moreEfficient {
        // same logic as outer class, but does not repeat dp[true]

        private int n;
        private int sumt;
        private int[][][] dp;
        private ArrayList<Integer> a;
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
            this.a = A;
            this.indexA = new ArrayList<>();
            this.sumt = a.stream().mapToInt(Integer::intValue).sum();
            // build matrix: index, sum of array, size of array
            this.dp = new int[n][sumt][n];

            for (int size1 = 1; size1 < n; size1++) {
                if ((size1 * sumt) % n != 0) {
                    continue;
                }

                int sum1 = (sumt * size1) / n;

                if (isPossible(0, sum1, size1)) {
                    generateResults();
                    break;
                }
            }

            return result;
        }

        private boolean isPossible(int index, int sum, int size) {
            if (size == 0) {
                return sum == 0;
            }
            if (index >= n) {
                return false;
            }
            if (dp[index][sum][size] != 0) {
                return dp[index][sum][size] == 1;
            }

            int now = a.get(index);
            if (sum >= now) {
                indexA.add(index);
                if (isPossible(index + 1, sum - now, size - 1)) {
                    dp[index][sum][size] = 1;
                    return true;
                }
                indexA.remove(indexA.size() - 1);
            }
            if (isPossible(index + 1, sum, size)) {
                dp[index][sum][size] = 1;
                return true;
            }

            dp[index][sum][size] = -1;
            return false;
        }

        private void generateResults() {
            ArrayList<Integer> setA = new ArrayList<>();
            ArrayList<Integer> setB = new ArrayList<>();
            int ptr = 0;
            int as = indexA.size();
            for (int i = 0; i < n; i++) {
                if (ptr < as && i == indexA.get(ptr)) {
                    setA.add(a.get(i));
                    ptr++;
                } else {
                    setB.add(a.get(i));
                }
            }

            result.add(setA);
            result.add(setB);
        }
    }

    static class noMemoization {

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