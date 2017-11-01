/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.graphs;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author Duy Dang
 */
public class WordLadderCount {

    private int n, ds;
    private boolean[] visited;
    private LinkedList<Vector> q;

    public int ladderLength(String start, String end, ArrayList<String> dictV) {
        if (start.equals(end)) {
            return 1;
        }        
        this.n = start.length(); // universal string length
        this.ds = dictV.size(); // dictionary size
        this.visited = new boolean[ds];
        this.q = new LinkedList<>();
        
        for (int i = 0; i < ds; i++) {
            String s = dictV.get(i);
            if (s == end) {
                return 1;
            }            
            if (validEdge(start, s)) {
                visited[i] = true;
                q.add(new Vector(s, 2));
            }            
        }
        
        // breadth first search
        while (!q.isEmpty()) {
            Vector v = q.pop();
            if (v.str.equals(end)) {
                return v.cycle;
            }  
            if (validEdge(v.str, end)) {
                return v.cycle + 1;
            }            
            for (int i = 0; i < ds; i++) {
                if (!visited[i]) {
                    String dest = dictV.get(i);
                    if (validEdge(v.str, dest)) {
                        visited[i] = true;
                        q.add(new Vector(dest, v.cycle + 1));
                    }                    
                }                
            }
        }
        
        return 0;
    }
    

    private boolean validEdge(String source, String dest) {
        boolean change = false;
        for (int i = 0; i < n; i++) {
            if (source.charAt(i) != dest.charAt(i)) {
                if (change) {
                    return false;
                }
                change = true;
            }
        }

        return change;
    }
    
    private class Vector {
        final String str;
        final int cycle;
        
        Vector(String s, int c) {
            this.str = s;
            this.cycle = c;
        }
    }
}
