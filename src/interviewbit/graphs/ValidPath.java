/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.graphs;

import java.util.ArrayList;

/**
 * Is there a path from bottom/left (0,0) to top/right (X,Y) without touching
 * circles? There are 'n' circles with radius 'R'. Coordinates are in
 * corresponding index of first array(x) and second(y)
 *
 * Solution: Combine intersecting circles into sets. If any set intersects
 * top/bottom, top/right, left/right, left,bottom no path exists
 *
 * @author Duy Dang
 */
public class ValidPath {

    private int x, y, n, r;
    private int[] rep; // holds set representative
    private boolean[] visited; // visited set
    private ArrayList<Integer> X, Y;

    public String solve(int A, int B, int C, int D, ArrayList<Integer> E, ArrayList<Integer> F) {
        init(A, B, C, D, E, F);
        int R = D * D * 4; // R = c^2. c = 2r

        for (int i = 0; i < n; i++) {
            int x1 = X.get(i);
            int y1 = Y.get(i);
            for (int j = i + 1; j < n; j++) {
                int x2 = X.get(j);
                int y2 = Y.get(j);

                int dx = x2 - x1; // difference x
                int dy = y2 - y1; // difference y
                // two circles belong in same set if their centers are less than
                // 2R apart. Determined by: a^2 + b^2 = c^2
                if (dx * dx + dy * dy <= R) {
                    union(i, j);
                }
            }
        }

        for (int i = 0; i < n; i++) { // update set representatives
            rep[i] = getRep(i);
        }

        for (int i = 0; i < n; i++) {
            int set = rep[i]; // iterate all set representatives
            if (!visited[set]) { // skip shared sets
                visited[set] = true;
                if (isBlocked(set)) {
                    return "NO";
                }
            }
        }

        return "YES";
    }

    private boolean isBlocked(int set) {
        // initialize values opposite limit
        int up = Integer.MIN_VALUE;
        int down = Integer.MAX_VALUE;
        int right = Integer.MIN_VALUE;
        int left = Integer.MAX_VALUE;
//        int up = -1;
//        int down = Y;
//        int left = X;
//        int right = -1;

        for (int i = 0; i < n; i++) {
            if (getRep(i) == set) {
                // use greedy to find axial limits of sets
                up = Math.max(up, Y.get(i) + r);
                down = Math.min(down, Y.get(i) - r);
                right = Math.max(right, X.get(i) + r);
                left = Math.min(left, X.get(i) - r);
            }
        }

        // if set intersects grid border, it can create blockage
        boolean upblock = up >= y;
        boolean downblock = down <= 0;
        boolean rightblock = right >= x;
        boolean leftblock = left <= 0;

        // if set intersects two grid borders, path to corner or edge is blocked
        if ((upblock && downblock)
                || (upblock && rightblock)
                || (leftblock && rightblock)
                || (leftblock && downblock)) {
            return true;
        }

        return false;
    }

    private void init(int A, int B, int C, int D, ArrayList<Integer> E, ArrayList<Integer> F) {
        this.x = A;
        this.y = B;
        this.r = D;
        this.n = C;
        this.X = E;
        this.Y = F;

        this.rep = new int[n];
        for (int i = 0; i < n; i++) {
            rep[i] = i; // initialize set representative as self
        }

        this.visited = new boolean[n];
    }

    // representative should point to self or recurse until self
    private int getRep(int r) {
        if (rep[r] != r) {
            // traverse up parent and compress path through recursion
            rep[r] = getRep(rep[r]);
        }
        return rep[r];
    }

    private void union(int i, int j) {
        // get set representative
        i = getRep(i);
        j = getRep(j);
        if (i != j) { // union as needed
            rep[i] = j;
        }
    }
}
