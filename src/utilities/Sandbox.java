/*
 * class for testing codes
 */
package utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author Dang
 */
public class Sandbox {

    private HashMap<String, Vector> ladder;
    private ArrayList<ArrayList<String>> results;
    private String start;

    public ArrayList<ArrayList<String>> findLadders(String start, String end, ArrayList<String> dict) {
        this.results = new ArrayList<>();
        if (start.equals(end)) {
            results.add(new ArrayList<>(Arrays.asList(start)));
            return results;
        }        
        this.ladder = new HashMap<>();
        for (String str : dict) {
            ladder.put(str, new Vector());
        }
        this.start = start;
        ladder.put(end, new Vector(0));
        LinkedList<String> queue = new LinkedList<>();
        queue.add(end);
        
        int limit = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            String word = queue.pop();
            Vector v = ladder.get(word);
            if (v != null) {
                int steps = v.count + 1;
                if (steps > limit) {
                    break;
                }  
                for (int i = 0; i < word.length(); i++) {
                    StringBuilder sb = new StringBuilder(word);
                    for (char ch = 'a'; ch < 'z'; ch++) {
                        sb.setCharAt(i, ch);
                        
                        String newWord = sb.toString();
                        if (newWord.equals(start)) {
                            limit = steps;
                        }                        
                        Vector newV = ladder.get(newWord);
                        if (newV != null) {
                            if (steps > newV.count) {
                                continue;
                            }                            
                            if (newV.count > steps) {
                                queue.add(newWord);
                                newV.count = steps;
                            }   
                            
                            v.neighbors.add(newWord);
                        }                        
                    }
                }
            }            
        }
        
        backtrack(end, new LinkedList<>());
        
        return results;
    }
    
    private void backtrack(String str, LinkedList<String> build) {
        build.addFirst(str);
        if (str.equals(start)) {
            results.add(new ArrayList<>(build));
            return;
        }
        Vector v = ladder.get(str);
        for (String s : v.neighbors) {
            backtrack(s, build);
            build.removeFirst();
        }
        
    }

    private class Vector {

        LinkedList<String> neighbors;
        int count;

        Vector() {
            this(Integer.MAX_VALUE);
        }

        Vector(int i) {
            this.count = i;
            this.neighbors = new LinkedList<>();
        }
    }

}
