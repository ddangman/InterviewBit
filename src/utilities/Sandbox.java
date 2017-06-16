/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.util.HashMap;
import java.util.Map.Entry;

/**
 *
 * @author Dang
 */
public class Sandbox {

    public int findRank(String a) {
        long rank = 1;
        long run = 1; // running factorial
        HashMap<Character, Integer> set = new HashMap<>();
        for (int i = a.length() - 1; i >= 0; i--) {
            char c = a.charAt(i);
            System.out.println(c);
            int cCount = set.containsKey(c) ? set.get(c) + 1 : 1;
            set.put(c, cCount);
            for (Entry<Character, Integer> e : set.entrySet()) {
                if (e.getKey() < c) {
                    long p = run * e.getValue() / cCount;
                    System.out.print(e.getKey());
                    System.out.println(" " + p);
                    rank += p;
                }
            }
            run *= a.length() - i;
            run /= cCount;
        }
        return (int) rank;
    }
}
