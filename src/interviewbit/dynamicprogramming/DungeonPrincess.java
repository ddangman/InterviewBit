/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.dynamicprogramming;

import java.util.ArrayList;

/**
 *
 * @author Dang
 */
public class DungeonPrincess {

    public int calculateMinimumHP(ArrayList<ArrayList<Integer>> a) {
        int rows = a.size();
        int col = a.get(0).size();
        int[][] hp = new int[rows + 1][col + 1]; // min hp to survive room
        hp[rows - 1][col] = hp[rows][col - 1] = 1;
        for (int r = rows - 2; r >= 0; r--) {
            hp[r][col] = Integer.MAX_VALUE;
        }
        for (int c = col - 2; c >= 0; c--) {
            hp[rows][c] = Integer.MAX_VALUE;
        }
        
        for (int r = rows - 1; r >= 0; r--) {
            for (int c = col - 1; c >= 0; c--) {
                int minHp = Math.min(hp[r][c + 1], hp[r + 1][c]) - a.get(r).get(c);
                hp[r][c] = minHp < 1 ? 1 : minHp;
            }
        }
        
        return hp[0][0];
    }
    
}
