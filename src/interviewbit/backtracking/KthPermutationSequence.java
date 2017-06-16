/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dang
 */
public class KthPermutationSequence {

    public String getPermutation(int n, int k) {
        StringBuilder sb = new StringBuilder();

        // create an array of factorial lookup
        // factorial[] = {1, 1, 2, 6, 24, ... n!}
        int[] factorial = new int[n + 1];
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }

        // create a list of numbers to get indices
        // numbers = {1, 2, 3, 4}
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            numbers.add(i);
        }

        k--; // subtract 1 because of things always starting at 0
        for (int i = 1; i <= n; i++) {
            // numbersIndex = permutation / (n - answerIndex)!
            int index = k / factorial[n - i];
            sb.append(String.valueOf(numbers.get(index)));
            numbers.remove(index);
            k -= index * factorial[n - i];
        }

        return String.valueOf(sb);
    }
}
