/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.dynamicprogramming;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Dang
 */
public class BirthdayFood {

    public int solve(final List<Integer> friend, final List<Integer> capacity, final List<Integer> cost) {
        // sort knapsack by increasing value
        int n = capacity.size();
        Pair[] pl = new Pair[n];
        for (int i = 0; i < n; i++) {
            pl[i] = new Pair(capacity.get(i), cost.get(i));
        }
        Arrays.sort(pl, (p1, p2) -> { // sort by increasing capacity
            if (p1.cap == p2.cap) {
                return p1.cost - p2.cost; // push higher cost to left
            } else {
                return p1.cap - p2.cap;
            }           
        });

        // find friend with highest capacity
        int max = 0;
        int fs = friend.size();
        for (int i = 0; i < fs; i++) {
            max = max > friend.get(i) ? max : friend.get(i);
        }
        
        // initialize array with Integer.MAX for comparison
        int[] fc = new int[max + 1]; // friend capacity
        for (int i = 1; i <= max; i++) {
            fc[i] = Integer.MAX_VALUE;
        }
        
        // knapsack logic
        int repeat = -1;
        for (int pi = 0; pi < pl.length; pi++) {
            if (pl[pi].cap == repeat) {
                continue; // skip repeat capacity with higher cost
            }            
            for (int fi = 1; fi <= max; fi++) {
                if (pl[pi].cap <= fi) { // can use food item
                    fc[fi] = Math.min(fc[fi - pl[pi].cap] + pl[pi].cost, fc[fi]);
                }                
            }

            repeat = pl[pi].cap;
        }

        int tc = 0; // total cost
        for (int fi = 0; fi < fs; fi++) {
            tc += fc[friend.get(fi)];
        }
        return tc;
    }
    
    private class Pair {
        protected int cap;
        protected int cost;
        
        Pair(int ca, int co) {
            this.cap = ca;
            this.cost = co;
        }
    }
}
