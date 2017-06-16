/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.heapmap;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 *
 * @author Dang
 */
public class MagicianAndChocolates {

    public int nchoc(int A, ArrayList<Integer> B) {
        long eat = 0;
        int mod = 1000000007;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        
        // initialize map
        for (int i = 0; i < B.size(); i++) {
            int n = B.get(i);
            map.compute(n, (k, v) -> {
                if (v == null) {
                    return 1;
                } else {
                    return v + 1;
                }               
 
            });
        }
        
        for (int i = 0; i < A; i++) {
            // get most chocolate
            Entry<Integer, Integer> e = map.lastEntry();
            
            // add chocolate to eat
            int c = e.getKey();
            eat += c;
            if (eat > mod) {
                eat %= mod;
            }  
            
            // remove eaten chocolate
            int ev = e.getValue();
            if (ev == 1) {
                map.remove(e.getKey());
            } else {
                map.put(c, ev - 1);
            }    
            
            // return half chocolate to bag
            int h = c / 2;
            map.compute(h, (k, v) -> {
                if (v == null) {
                    return 1;
                } else {
                    return v + 1;
                }               
            });
        }
        
        return (int) eat;
    }
}
