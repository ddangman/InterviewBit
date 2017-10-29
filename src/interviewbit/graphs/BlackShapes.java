/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.graphs;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Finds number of shapes. 
 * A shape consists of one or more adjacent 'X' (diagonals not included)
 * @author Duy Dang
 */
public class BlackShapes {

    private boolean[][] visited;
    private LinkedList<Pair> q;
    private int row, col;
    private ArrayList<String> input;

    public int black(ArrayList<String> a) {
        input = a;
        this.row = a.size();
        this.col = a.get(0).length();
        this.visited = new boolean[row][col];
        q = new LinkedList<>();
        int count = 0;

        for (int r = 0; r < row; r++) {
            String cr = a.get(r); // current row
            for (int c = 0; c < col; c++) {
                if (visited[r][c]) {
                    continue;
                }
                visited[r][c] = true;
                if (cr.charAt(c) == 'X') {
                    q.add(new Pair(r, c));
                    count++;
                    bfs();
                }
            }
        }

        return count;
    }

    private void bfs() {
        while (!q.isEmpty()) {
            Pair pop = q.pop();
            // up
            if (pop.r != 0 && !visited[pop.r - 1][pop.c]) {
                visited[pop.r - 1][pop.c] = true;
                if (input.get(pop.r - 1).charAt(pop.c) == 'X') {
                    q.add(new Pair(pop.r - 1, pop.c));
                }
            }
            // down
            if (pop.r + 1< row && !visited[pop.r + 1][pop.c]) {
                visited[pop.r + 1][pop.c] = true;
                if (input.get(pop.r + 1).charAt(pop.c) == 'X') {
                    q.add(new Pair(pop.r + 1, pop.c));
                }
            }
            // left
            if (pop.c != 0 && !visited[pop.r][pop.c - 1]) {
                visited[pop.r][pop.c - 1] = true;
                if (input.get(pop.r).charAt(pop.c - 1) == 'X') {
                    q.add(new Pair(pop.r, pop.c - 1));
                }
            }
            // right
            if (pop.c + 1 < col && !visited[pop.r][pop.c + 1]) {
                visited[pop.r][pop.c + 1] = true;
                if (input.get(pop.r).charAt(pop.c + 1) == 'X') {
                    q.add(new Pair(pop.r, pop.c + 1));
                }
            }
        }
    }

    private class Pair {

        int r;
        int c;

        Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

}
