/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package utilities;

import java.util.ArrayList;

/**
 * This class contains utilities for ArrayList matrices.
 *
 * @author Dang
 */
public class MatrixUtilities {

    /**
     * Generates a square matrix of input size holding counting numbers starting
     * from 1.
     *
     * @return NxN Integer ArrayList matrix
     */
    public static ArrayList<ArrayList<Integer>> generateIntMatrix(int n) {
        ArrayList<ArrayList<Integer>> a = new ArrayList<ArrayList<Integer>>();
        int k = 1;
        for (int i = 0; i < n; i++) {
            ArrayList<Integer> row = new ArrayList<Integer>();
            for (int j = 0; j < n; j++) {
                row.add(k);
                k++;
            }
            a.add(row);
        }
        return a;
    }

    /**
     * Prints Integer matrix.
     */
    public static void printIntMatrix(ArrayList<ArrayList<Integer>> m) {
        System.out.println("");
        for (int i = 0; i < m.size(); i++) {
            for (int j = 0; j < m.get(i).size(); j++) {
                System.out.print(m.get(i).get(j));
                System.out.print(" ");
            }
            System.out.println("");
        }
    }

    public static void printInt2DArray(int[][] matrix) {
        System.out.println("");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j]);
                System.out.print(" ");
            }
            System.out.println("");
        }
    }
}
