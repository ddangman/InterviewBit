/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.graphs;

import java.util.ArrayList;

/**
 * Determine if string exists in grid.
 * @author Duy Dang
 */
public class WordSearchBoard {

    private ArrayList<String> grid;
    private String str;
    private int row, col, n;
    public int exist(ArrayList<String> a, String b) {
        this.grid = a;
        this.str = b;
        this.row = a.size();
        this.col = a.get(0).length();
        this.n = b.length();
        
        for (int r = 0; r < row; r++) {
            String now = grid.get(r);
            for (int c = 0; c < col; c++) {
                if (now.charAt(c) == b.charAt(0)) {
                    if (dfs(r, c, 1)) {
                        return 1;
                    }                    
                }                
            }
        }
        
        return 0;
    }
    
    private boolean dfs(int y, int x, int i) {
        if (y < 0 || y >= row || x < 0 || x >= col) {
            return false;
        }   
        if (i == n) {
            return true;
        }  
        
        boolean up = false;
        if (y > 0 && grid.get(y - 1).charAt(x) == str.charAt(i)) {
            up = dfs(y - 1, x, i + 1);
        } 
        
        boolean down = false;
        if (y + 1 < row && grid.get(y + 1).charAt(x) == str.charAt(i)) {
            down = dfs(y + 1, x, i + 1);
        }        
        
        boolean left = false;
        if (x > 0 && grid.get(y).charAt(x - 1) == str.charAt(i)) {
            left = dfs(y, x - 1, i + 1);
        }        
        
        boolean right = false;
        if (x + 1 < col && grid.get(y).charAt(x + 1) == str.charAt(i)) {
            right = dfs(y, x + 1, i + 1);
        }        
        
        return up || down || left || right;
    }
}
