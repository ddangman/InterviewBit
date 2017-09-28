/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.dynamicprogramming;

import java.util.ArrayList;

/**
 * given an array of power, get the maximum number of hits
 * while keeping sum of all power values under hit points
 * Return the first lexicographical answer array
 * @author Dang
 */
public class BirthdayKicks {

    public ArrayList<Integer> solve(int hp, ArrayList<Integer> power) {
        int n = power.size();
        if (power.isEmpty()) {
            return new ArrayList<>();
        }        
        
        
        // make a new array of useful power values
        // Key values are first and minimum. 
        // Include all descending values in between.
        int min = power.get(0);
        ArrayList<Pair> desc = new ArrayList<>();
        desc.add(new Pair(min, 0));
        for (int i = 1; i < n; i++) {
            int x = power.get(i);
            if (x < min) {
                min = x;
                desc.add(new Pair(x, i));
            }            
        }
        
        int maxHits = hp / min; // get maximum number of hits
        int rem = hp % min; // remainder that can be allocated to lower index
        ArrayList<Integer> solution = new ArrayList<>();
        for (int i = 0; i < desc.size(); i++) {
            int diff = desc.get(i).value - min;
            while (solution.size() != maxHits && rem - diff >= 0) {
                solution.add(desc.get(i).index);
                rem -= diff;
            }
            if (solution.size() == maxHits) {
                break;
            }            
        }
        
        return solution;
    }
    
    private class Pair {
        int value;
        int index;
        
        Pair(int v, int i) {
            this.value = v;
            this.index = i;
        }
    }
}
