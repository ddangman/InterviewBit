package interviewbit;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Dang
 */
public class InterviewBit {

    public static void main(String[] args) {
       

    }

    public static ArrayList<ArrayList<Integer>> generateMatrix(int n) {
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

    public static void printMatrix(ArrayList<ArrayList<Integer>> m) {
        for (int i = 0; i < m.size(); i++) {
            for (int j = 0; j < m.get(i).size(); j++) {
                System.out.print(m.get(i).get(j));
                System.out.print(" ");
            }
            System.out.println("");
        }
    }

    public static void test3() {
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> row1 = new ArrayList<Integer>(Arrays.asList(1, 1, 2, 2, 5, 6, 6, 6, 7));
        ArrayList<Integer> row2 = new ArrayList<Integer>(Arrays.asList(9, 10, 10, 12, 12, 13, 14, 21, 21));
        ArrayList<Integer> row3 = new ArrayList<Integer>(Arrays.asList(23, 26, 26, 29, 29, 31, 32, 35, 37));
        ArrayList<Integer> row4 = new ArrayList<Integer>(Arrays.asList(38, 39, 39, 39, 41, 41, 42, 42, 43));
        ArrayList<Integer> row5 = new ArrayList<Integer>(Arrays.asList(45, 45, 46, 46, 46, 47, 48, 48, 51));
        ArrayList<Integer> row6 = new ArrayList<Integer>(Arrays.asList(60, 61, 61, 62, 63, 64, 65, 66, 67));
        ArrayList<Integer> row7 = new ArrayList<Integer>(Arrays.asList(89, 93, 94, 94, 97, 98, 98, 98, 98));
//        ArrayList<Integer> row8 = new ArrayList<Integer>(Arrays.asList(78));
//        ArrayList<Integer> row9 = new ArrayList<Integer>(Arrays.asList(85));
        matrix.add(row1);
        matrix.add(row2);
        matrix.add(row3);
        matrix.add(row4);
        matrix.add(row5);
        matrix.add(row6);
        matrix.add(row7);
//        matrix.add(row8);
//        matrix.add(row9);
    }

}
