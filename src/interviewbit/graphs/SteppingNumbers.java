/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Duy Dang
 */
public class SteppingNumbers {

    private ArrayList<Integer> sorted;

    public ArrayList<Integer> stepnum(int n, int m) {
        sorted = new ArrayList<>();

        // For every single digit Number 'i'
        // find all the Stepping Numbers starting with i
        for (int i = 0; i <= 9; i++) {
            bfs(n, m, i);
        }

        Collections.sort(sorted);
        return sorted;
    }

    // Saves all stepping numbers reachable from num
    // and in range [n, m]
    private void bfs(int n, int m, int i) {
        // Queue will contain all the stepping Numbers
        Queue<Integer> q = new LinkedList<Integer>();

        q.add(i);

        while (!q.isEmpty()) {
            // Get the front element and pop from the queue
            int stepNum = q.poll();

            // If the Stepping Number is in the range [n,m] then save
            if (stepNum <= m && stepNum >= n) {
                sorted.add(stepNum);
            }

            // If Stepping Number is 0 or greater then m, 
            // need to explore the neighbors
            if (stepNum == 0 || stepNum > m) {
                continue;
            }

            // Get the last digit of the currently visited Stepping Number
            int lastDigit = stepNum % 10;
            stepNum *= 10;

            // There can be 2 cases: digit to be appended is 
            // (lastDigit + 1) or
            // (lastDigit - 1)
            int plus = stepNum + (lastDigit - 1);
            int minus = stepNum + (lastDigit + 1);

            // If lastDigit is 0 then only possible digit after 0 
            // can be 1 for a Stepping Number
            if (lastDigit == 0) {
                q.add(minus);
            } else if (lastDigit == 9) {
                // If lastDigit is 9 then only possible digit after 9 
                // can be 8 for a Stepping Number
                q.add(plus);
            } else {
                q.add(plus);
                q.add(minus);
            }
        }
    }

    private class DFS {

        private ArrayList<Integer> sorted;
        private int m, n;

        public ArrayList<Integer> stepnum(int a, int b) {
            this.m = a;
            this.n = b;
            this.sorted = new ArrayList<>();
            if (a == 0) {
                sorted.add(0);
            }
            for (int i = 1; i < 10; i++) {
                dfs(i);
            }

            Collections.sort(sorted);
            return sorted;
        }

        private void dfs(int num) {
            if (num > n || num < 0) {
                return;
            }

            if (num >= m) {
                sorted.add(num);
            }

            int last = num % 10;
            num *= 10;

            if (last < 9) {
                dfs(num + (last + 1));
            }
            if (last > 0) {
                dfs(num + (last - 1));
            }
        }
    }
}
