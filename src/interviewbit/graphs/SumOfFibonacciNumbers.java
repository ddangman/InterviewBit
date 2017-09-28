/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.graphs;

import java.util.ArrayList;

/**
 *
 * @author Duy Dang
 */
public class SumOfFibonacciNumbers {

    private int[] mem; // memoize sequence
    private int target;
    private int i;

    public int fibsum(int a) {
        this.target = a;
        mem = new int[47]; // anything higher will exceed Integer.MAX_VALUE
        mem[0] = mem[1] = 1;
        i = 2;
        buildFib();
        return greedy();
    }

    // build fibonacci sequence up to or greater than target
    private void buildFib() {
        mem[i] = mem[i - 1] + mem[i - 2];
        if (i == 47 || mem[i] >= target) {
            return;
        }
        i++;
        buildFib();
    }
    
    // use greedy subtration to find minimum numbers needed
    private int greedy() {
        int count = 0;
        while (0 < target) {
            if (target - mem[i] >= 0) {
                target -= mem[i];
                count++;
            } else {
                i--;
            }           
        }
        return count;
    }
}
