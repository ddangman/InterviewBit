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
public class ValidPath {

    int xmax;
    int ymax;
    ArrayList<Integer> E;
    ArrayList<Integer> F;
    long radsqr;
    int n;
    public String solve(int xmax, int ymax, int n, int r, ArrayList<Integer> E, ArrayList<Integer> F) {
        if (xmax == 0 || ymax == 0) {
            return "NO";
        }
        this.xmax = xmax;
        this.ymax = ymax;
        this.E = E;
        this.F = F;
        radsqr = r * r;
        this.n = n;
        boolean[][] grid = new boolean[xmax][ymax];
        LinkedList<Pair> ll = new LinkedList<>();
        grid[0][0] = inCircle(0, 0);
        if (!grid[0][0]) {
            qNeighbors(grid, ll, 0, 0);
        }
        grid[xmax - 1][ymax - 1] = inCircle(xmax - 1, ymax - 1);
        if (grid[xmax - 1][ymax - 1]) {
            return "NO";
        }

        while (!ll.isEmpty()) {
            Pair pop = ll.pop();
            qNeighbors(grid, ll, pop.x, pop.y);
            if (grid[xmax - 1][ymax - 1]) {
                return "YES";
            }
        }

        return "NO";
    }

    private boolean inCircle(int x, int y) {
        for (int i = 0; i < n; i++) {
            int e = Math.abs(E.get(i) - x);
            int f = Math.abs(F.get(i) - y);
            long distance = e * e * f * f;
            if (distance < radsqr) {
                return true;
            }
        }
        return false;
    }

    private void qNeighbors(boolean[][] grid, LinkedList<Pair> ll, int x, int y) {
        boolean top = y + 1 < ymax;
        boolean bottom = y > 0;
        boolean left = x > 0;
        boolean right = x + 1 < xmax;
        if (top && !grid[x][y + 1]) {
            if (!inCircle(x, y + 1)) {
                ll.add(new Pair(x, y + 1));
            }            
            grid[x][y + 1] = true;
        }
        if (bottom && !grid[x][y - 1]) {
            if (!inCircle(x, y - 1)) {
                ll.add(new Pair(x, y - 1));
            }            
            grid[x][y - 1] = true;
        }
        if (right && !grid[x + 1][y]) {
            if (!inCircle(x + 1, y)) {
                ll.add(new Pair(x + 1, y));
            }            
            grid[x + 1][y] = true;
        }
        if (left && !grid[x - 1][y]) {
            if (!inCircle(x - 1, y)) {
                ll.add(new Pair(x - 1, y));
            }            
            grid[x - 1][y] = true;
        }
        if (top && right && !grid[x + 1][y + 1]) {
            if (!inCircle(x + 1, y + 1)) {
                ll.add(new Pair(x + 1, y + 1));
            }            
            grid[x + 1][y + 1] = true;
        }
        if (top && left && !grid[x - 1][y + 1]) {
            if (!inCircle(x - 1, y + 1)) {
                ll.add(new Pair(x - 1, y + 1));
            }            
            grid[x - 1][y + 1] = true;
        }
        if (bottom && right && !grid[x + 1][y - 1]) {
            if (!inCircle(x + 1, y - 1)) {
                ll.add(new Pair(x + 1, y - 1));
            }            
            grid[x + 1][y - 1] = true;
        }
        if (bottom && left && !grid[x - 1][y - 1]) {
            if (!inCircle(x - 1, y - 1)) {
                ll.add(new Pair(x - 1, y - 1));
            }            
            grid[x - 1][y - 1] = true;
        }
    }

    private class Pair {

        int x;
        int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
