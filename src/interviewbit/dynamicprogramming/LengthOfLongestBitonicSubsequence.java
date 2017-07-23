/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.dynamicprogramming;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dang
 */
public class LengthOfLongestBitonicSubsequence {

    private int size;

    // O(n*log n)
    public int longestSubsequenceLength(final List<Integer> input) {
        if (input.isEmpty()) {
            return 0;
        } else if (input.size() == 1) {
            return 1;
        }

        size = input.size();

        int[] lis = lisArray(input); // O(n*log n)
        int[] lds = ldsArray(input); // O(n*log n)

        int max = 0;
        int sum;
        for (int i = 0; i < size; i++) { // O(n)
            sum = lis[i] + lds[size - i - 1] - 1;
            max = max > sum ? max : sum;
        }

        return max;
    }

    public int binSearchIndex(int a[], int low, int high, int x) {
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (a[mid] < x) {
                low = mid + 1;
            } else if (a[mid] > x) {
                high = mid;
            } else {
                return mid;
            }
        }

        return low;
    }

    /**
     * Returns an array of Longest Increasing Sequence (LIS) results such that
     * input.get(i) holds the result of the LIS calculation up to that index
     * included.
     *
     * @param input The target array.
     * @return An array of LIS results.
     */
    public int[] lisArray(List<Integer> input) { // O(n*logn) {
        // searchArray stores input.get(values) in increasing order
        // searchArray index + 1 equals length of best LIS
        // searchArray[value] stores lowest possible input.value for LIS length
        int[] searchArray = new int[size];
        searchArray[0] = input.get(0);
        int length = 0;

        // Longest Increasing Sequence (LIS) array
        // lis.index correlates to input.index
        // value is LIS
        int[] lis = new int[size]; // array for LIS answers
        // start at 1 (longest sub array of a single element is 1)
        // add one to all calculations to include current element
        lis[0] = 1;

        for (int i = 1; i < size; i++) { // O(n) * O(logn) 
            int x = input.get(i);
            if (x <= searchArray[length]) {
                int index = binSearchIndex(searchArray, 0, length, x);
                // index is LIS value
                searchArray[index] = x;
                lis[i] = index + 1;
            } else { // current input.value is greatest so far
                // length will be LIS value
                searchArray[++length] = x;
                lis[i] = length + 1;
            }
        }

        return lis;
    }

    public int[] ldsArray(List<Integer> input) {
        ArrayList<Integer> reverse = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            reverse.add(input.get(size - i - 1));
        }
        return lisArray(reverse);
    }

    /**
     * Solution that does not copy input in reverse order.
     * Longest Decreasing Subsequence has its own generation function.
     */
    class noReverseCopy {

        private int n;
        private List<Integer> input;

        public int longestSubsequenceLength(final List<Integer> input) {
            this.n = input.size();
            if (input.isEmpty()) {
                return 0;
            } else if (n == 1) {
                return 1;
            }
            this.input = input;

            int[] lis = generateLIS();
            int[] lds = generateLDS();

            int max = 0;
            for (int i = 0; i < n; i++) {
                int sum = lis[i] + lds[n - 1 - i] - 1;
                max = max > sum ? max : sum;
            }

            return max;
        }

        public int[] generateLIS() {
            int[] util = new int[n];
            int[] lis = new int[n];
            int len = 0;
            lis[0] = 1;
            util[0] = input.get(0);

            for (int i = 1; i < n; i++) {
                int x = input.get(i);
                if (x > util[len]) {
                    util[++len] = x;
                    lis[i] = len + 1;
                } else {
                    int index = bisearch(x, len, util);
                    util[index] = x;
                    lis[i] = index + 1;
                }
            }

            return lis;
        }

        public int[] generateLDS() {
            int[] util = new int[n];
            int[] lds = new int[n];
            int len = 0;
            util[0] = input.get(n - 1);
            lds[0] = 1;

            for (int i = n - 2; i >= 0; i--) {
                int x = input.get(i);
                if (x > util[len]) {
                    util[++len] = x;
                    lds[n - i - 1] = len + 1;
                } else {
                    int index = bisearch(x, len, util);
                    util[index] = x;
                    lds[n - i - 1] = index + 1;
                }
            }

            return lds;
        }

        private int bisearch(int x, int end, int[] util) {
            int start = 0;
            while (start <= end) {
                int mid = (start + end) / 2;
                if (util[mid] == x) {
                    return mid;
                } else if (util[mid] > x) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }

            return start;
        }
    }
}
