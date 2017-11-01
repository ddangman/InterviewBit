/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.graphs;

import java.util.LinkedList;

/**
 *
 * @author Duy Dang
 */
public class KnightOnChessBoard {

    private int[][] grid;
    private int X, Y;
    private LinkedList<Vector> q;

    public int knight(int n, int m, int xs, int ys, int xd, int yd) {
        this.grid = new int[n][m];
        this.q = new LinkedList<>();
        this.X = n;
        this.Y = m;
        xs--;
        ys--;
        xd--;
        yd--;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                grid[i][j] = -1;
            }
        }

        grid[xs][ys] = 0;
        q.add(new Vector(xs, ys, 0));
        bfs();

        return grid[xd][yd];
    }

    private void bfs() {
        while (!q.isEmpty()) {
            Vector v = q.pop();

            // movement based on clock direction
            if (v.x + 1 < X && v.y + 2 < Y) { // 1
                if (grid[v.x + 1][v.y + 2] == -1
                        || grid[v.x + 1][v.y + 2] > v.move + 1) {
                    grid[v.x + 1][v.y + 2] = v.move + 1;
                    q.add(new Vector(v.x + 1, v.y + 2, v.move + 1));
                }
            }

            if (v.x + 2 < X && v.y + 1 < Y) { // 2
                if (grid[v.x + 2][v.y + 1] == -1
                        || grid[v.x + 2][v.y + 1] > v.move + 1) {
                    grid[v.x + 2][v.y + 1] = v.move + 1;
                    q.add(new Vector(v.x + 2, v.y + 1, v.move + 1));
                }
            }

            if (v.x + 2 < X && v.y - 1 >= 0) { // 4
                if (grid[v.x + 2][v.y - 1] == -1
                        || grid[v.x + 2][v.y - 1] > v.move + 1) {
                    grid[v.x + 2][v.y - 1] = v.move + 1;
                    q.add(new Vector(v.x + 2, v.y - 1, v.move + 1));
                }
            }

            if (v.x + 1 < X && v.y - 2 >= 0) { // 5
                if (grid[v.x + 1][v.y - 2] == -1
                        || grid[v.x + 1][v.y - 2] > v.move + 1) {
                    grid[v.x + 1][v.y - 2] = v.move + 1;
                    q.add(new Vector(v.x + 1, v.y - 2, v.move + 1));
                }
            }

            if (v.x - 1 >= 0 && v.y - 2 >= 0) { // 7
                if (grid[v.x - 1][v.y - 2] == -1
                        || grid[v.x - 1][v.y - 2] > v.move + 1) {
                    grid[v.x - 1][v.y - 2] = v.move + 1;
                    q.add(new Vector(v.x - 1, v.y - 2, v.move + 1));
                }
            }

            if (v.x - 2 >= 0 && v.y - 1 >= 0) { // 8
                if (grid[v.x - 2][v.y - 1] == -1
                        || grid[v.x - 2][v.y - 1] > v.move + 1) {
                    grid[v.x - 2][v.y - 1] = v.move + 1;
                    q.add(new Vector(v.x - 2, v.y - 1, v.move + 1));
                }
            }

            if (v.x - 2 >= 0 && v.y + 1 < Y) { // 10
                if (grid[v.x - 2][v.y + 1] == -1
                        || grid[v.x - 2][v.y + 1] > v.move + 1) {
                    grid[v.x - 2][v.y + 1] = v.move + 1;
                    q.add(new Vector(v.x - 2, v.y + 1, v.move + 1));
                }
            }
            
            if (v.x - 1 >= 0 && v.y + 2 < Y) { // 11
                if (grid[v.x - 1][v.y + 2] == -1
                        || grid[v.x - 1][v.y + 2] > v.move + 1) {
                    grid[v.x - 1][v.y + 2] = v.move + 1;
                    q.add(new Vector(v.x - 1, v.y + 2, v.move + 1));
                }
            }
        }
    }

    private class Vector {

        final int x; // x coordinate
        final int y; // y coordinate
        final int move; // count of moves

        Vector(int x, int y, int move) {
            this.x = x;
            this.y = y;
            this.move = move;
        }
    }
}
