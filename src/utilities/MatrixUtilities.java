/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package utilities;

import java.util.ArrayList;
import java.util.Scanner;

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
     * @param n argument becomes number of rows and columns
     * @return NxN Number ArrayList matrix
     */
    public static ArrayList<ArrayList<Integer>> generateSquareMatrix(int n) {
        ArrayList<ArrayList<Integer>> a = new ArrayList<>();
        int k = 1;
        for (int i = 0; i < n; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                row.add(k);
                k++;
            }
            a.add(row);
        }
        return a;
    }

    /**
     * Generates a matrix from an ArrayList. Last row does not have to be filled
     * to the width.
     *
     * @param width maximum length of each matrix row
     * @param a ArrayList of values to be turned into matrix.
     * @return matrix of ArrayList values starting from row zero and filling
     * columns to size of width.
     */
    public static ArrayList<ArrayList<Integer>> IntegerArraylistToMatrix(int width, ArrayList<Integer> a) {
        ArrayList<ArrayList<Integer>> m = new ArrayList<>(); // matrix
        int ai = 0; // a index

        while (ai < a.size()) {
            int i = 0; // new list index
            ArrayList<Integer> build = new ArrayList<>();
            while (i++ < width) {
                build.add(a.get(ai++));
            }
            m.add(build);
        }

        return m;
    }

    /**
     * Generates a matrix from a String. Last row does not have to be filled
     * to the width.
     *
     * @param width maximum length of each matrix row
     * @param a String of integer values to be turned into matrix.
     * @return matrix of ArrayList values starting from row zero and filling
     * columns to size of width.
     */
    public static ArrayList<ArrayList<Integer>> StringToIntegerMatrix(int width, String a) {
        ArrayList<ArrayList<Integer>> m = new ArrayList<>(); // matrix
        Scanner scanner = new Scanner(a);
        ArrayList<Integer> build = new ArrayList<>();
        int count = 0;

        while (scanner.hasNextInt()) {
            build.add(scanner.nextInt());
            count++;
            if (count == width) {
                count = 0;
                // build.clear will not work since m points to build
                m.add(build);
                build = new ArrayList<>(); // points to new ArrayList
            }
        }

        return m;
    }

    /**
     * Generates a matrix from a String. Last row does not have to be filled
     * to the width.
     *
     * @param a String of integer values to be turned into matrix.
     * @return matrix of ArrayList values starting from row zero and filling
     * columns to size of width. Row's width is (row_number + 1)
     */
    public static ArrayList<ArrayList<Integer>> StringToIntegerTree(String a) {
        ArrayList<ArrayList<Integer>> m = new ArrayList<>(); // matrix
        Scanner scanner = new Scanner(a);
        ArrayList<Integer> build = new ArrayList<>();
        int count = 0;

        int width = 1;
        while (scanner.hasNextInt()) {
            build.add(scanner.nextInt());
            count++;
            if (count == width) {
                count = 0;
                width++;
                m.add(build);
                build = new ArrayList<>(); // points to new ArrayList
            }
        }

        return m;
    }

    /**
     * Generates a matrix from an ArrayList. Last row does not have to be filled
     * to the width.
     *
     * @param width maximum length of each matrix row
     * @param a ArrayList of values to be turned into matrix.
     * @return matrix of ArrayList values starting from row zero and filling
     * columns to size of width.
     */
    public static ArrayList<String> StringArraylistToMatrix(int width, String a) {
        ArrayList<String> m = new ArrayList<>(); // matrix
        int ai = 0; // a index

        while (ai < a.length()) {
            int i = 0; // new list index
            StringBuilder sb = new StringBuilder();
            while (i++ < width) {
                sb.append(a.charAt(ai++));
            }
            m.add(sb.toString());
        }

        return m;
    }

    /**
     * Prints Number matrix.
     *
     * @param m Number matrix
     */
    public static void printIntegerMatrix(ArrayList<ArrayList<Integer>> m) {
        System.out.println("");
        for (int i = 0; i < m.size(); i++) {
            for (int j = 0; j < m.get(i).size(); j++) {
                System.out.print(m.get(i).get(j));
                System.out.print(" ");
            }
            System.out.println("");
        }
    }

    public static void print2DArray(int[][] matrix) {
        System.out.println("");
        for (int[] matrix1 : matrix) {
            for (int j = 0; j < matrix1.length; j++) {
                System.out.print(matrix1[j]);
                System.out.print(" ");
            }
            System.out.println("");
        }
    }

    public static void print2DArray(boolean[][] matrix) {
        System.out.println("");
        for (boolean[] matrix1 : matrix) {
            for (int j = 0; j < matrix1.length; j++) {
                if (matrix1[j]) {
                    System.out.print("1");
                } else {
                    System.out.print("0");
                }
                System.out.print(" ");
            }
            System.out.println("");
        }
    }

    public static void print2DArray(long[][] matrix) {
        System.out.println("");
        for (long[] matrix1 : matrix) {
            for (int j = 0; j < matrix1.length; j++) {
                System.out.print(matrix1[j]);
                System.out.print(" ");
            }
            System.out.println("");
        }
    }

    public static void print2DArray(String[][] matrix) {
        System.out.println("");
        for (String[] matrix1 : matrix) {
            for (int j = 0; j < matrix1.length; j++) {
                System.out.print(matrix1[j]);
                System.out.print(" ");
            }
            System.out.println("");
        }
    }
}
