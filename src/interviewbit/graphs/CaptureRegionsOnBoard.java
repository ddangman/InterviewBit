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
public class CaptureRegionsOnBoard {

    private ArrayList<ArrayList<Character>> board;
    private int row, col;

    public void solve(ArrayList<ArrayList<Character>> board) {
        this.row = board.size();
        this.col = board.get(0).size();
        if (row == 0 || col == 0) {
            return;
        }
        if (row < 2 || col < 2) {
            return;
        }
        this.board = board;

        //Any 'O' connected to a boundary can't be turned to 'X', so ...
        //Start from first and last column, turn 'O' to '*'.
        for (int r = 0; r < row; r++) {
            if (board.get(r).get(0) == 'O') {
                boundaryDFS(r, 0);
            }
            if (board.get(r).get(col - 1) == 'O') {
                boundaryDFS(r, col - 1);
            }
        }
        //Start from first and last row, turn '0' to '*'
        for (int c = 0; c < col; c++) {
            if (board.get(0).get(c) == 'O') {
                boundaryDFS(0, c);
            }
            if (board.get(row - 1).get(c) == 'O') {
                boundaryDFS(row - 1, c);
            }
        }

        //post-prcessing, turn 'O' to 'X', '*' back to 'O', keep 'X' intact.
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (board.get(r).get(c) == 'O') {
                    board.get(r).set(c, 'X');
                } else if (board.get(r).get(c) == '*') {
                    board.get(r).set(c, 'O');
                }
            }
        }
    }

    //Use DFS algo to turn internal however boundary-connected 'O' to '*';
    private void boundaryDFS(int r, int c) {
        if (r < 0 || r > row - 1 || c < 0 || c > col - 1) {
            return;
        }
        if (board.get(r).get(c) == 'O') {
            board.get(r).set(c, '*');
        }
        if (r > 1 && board.get(r - 1).get(c) == 'O') {
            boundaryDFS(r - 1, c);
        }
        if (r < row - 2 && board.get(r + 1).get(c) == 'O') {
            boundaryDFS(r + 1, c);
        }
        if (c > 1 && board.get(r).get(c - 1) == 'O') {
            boundaryDFS(r, c - 1);
        }
        if (c < col - 2 && board.get(r).get(c + 1) == 'O') {
            boundaryDFS(r, c + 1);
        }
    }

    private class BFS {

        private int row, col;
        private boolean[][] invalid;
        private ArrayList<ArrayList<Character>> grid;
        private LinkedList<Pair> q;

        public void solve(ArrayList<ArrayList<Character>> grid) {
            this.row = grid.size();
            this.col = grid.get(0).size();
            if (row <= 2 || col <= 2) {
                return;
            }
            this.grid = grid;
            this.q = new LinkedList<>();
            invalid = new boolean[row][col];

            // invalidate all border 'O' and all connected 'O'
            int y = row - 1; // bottom edge
            for (int c = 0; c < col; c++) {
                if (!invalid[0][c]) {
                    invalid[0][c] = true;
                    if (grid.get(0).get(c) == 'O') {
                        q.add(new Pair(0, c));
                        bfs();
                    }
                }
                if (!invalid[y][c]) {
                    invalid[y][c] = true;
                    if (grid.get(y).get(c) == 'O') {
                        q.add(new Pair(y, c));
                        bfs();
                    }
                }
            }

            int x = col - 1; // right edge
            for (int r = 0; r < y; r++) {
                if (!invalid[r][0]) {
                    invalid[r][0] = true;
                    if (grid.get(r).get(0) == 'O') {
                        q.add(new Pair(r, 0));
                        bfs();
                    }
                }
                if (!invalid[r][x]) {
                    invalid[r][x] = true;
                    if (grid.get(r).get(x) == 'O') {
                        q.add(new Pair(r, x));
                        bfs();
                    }
                }
            }

            // replace inside 'O' with 'X'
            for (int r = 1; r < y; r++) {
                for (int c = 0; c < x; c++) {
                    if (!invalid[r][c] && grid.get(r).get(c) == 'O') {
                        grid.get(r).set(c, 'X');
                    }
                }
            }
        }

        private void bfs() {
            while (!q.isEmpty()) {
                Pair pop = q.pop();
                if (pop.y > 0 && !invalid[pop.y - 1][pop.x]) { // up
                    invalid[pop.y - 1][pop.x] = true;
                    if (grid.get(pop.y - 1).get(pop.x) == 'O') {
                        q.add(new Pair(pop.y - 1, pop.x));
                    }
                }
                if (pop.y + 1 < row && !invalid[pop.y + 1][pop.x]) { // down
                    invalid[pop.y + 1][pop.x] = true;
                    if (grid.get(pop.y + 1).get(pop.x) == 'O') {
                        q.add(new Pair(pop.y + 1, pop.x));
                    }
                }
                if (pop.x > 0 && !invalid[pop.y][pop.x - 1]) { // left
                    invalid[pop.y][pop.x - 1] = true;
                    if (grid.get(pop.y).get(pop.x - 1) == 'O') {
                        q.add(new Pair(pop.y, pop.x - 1));
                    }
                }
                if (pop.x + 1 < col && !invalid[pop.y][pop.x + 1]) { // right
                    invalid[pop.y][pop.x + 1] = true;
                    if (grid.get(pop.y).get(pop.x + 1) == 'O') {
                        q.add(new Pair(pop.y, pop.x + 1));
                    }
                }
            }
        }

        private class Pair {

            final int x;
            final int y;

            Pair(int y, int x) {
                this.y = y;
                this.x = x;
            }
        }
    }
}
