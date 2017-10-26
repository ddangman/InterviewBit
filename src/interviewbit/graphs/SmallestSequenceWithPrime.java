/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.graphs;

import interviewbit.datastructures.HashPriorityQueue;
import java.util.ArrayList;

/**
 *
 * @author Duy Dang
 */
public class SmallestSequenceWithPrime {

    public ArrayList<Integer> solve(int a, int b, int c, int k) {
        HashPriorityQueue<Integer> hpq = new HashPriorityQueue();
        hpq.add(a);
        hpq.add(b);
        hpq.add(c);

        ArrayList<Integer> results = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            int poll = hpq.poll();
            results.add(poll);
            hpq.add(poll * a);
            hpq.add(poll * b);
            hpq.add(poll * c);
        }

        return results;
    }
}
