/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interviewbit;

import interviewbit.binarysearch.BinarySearchMatrix;
import interviewbit.math.UnderKAndNDigits;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Dang
 */
public class InterviewBit {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
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
        BinarySearchMatrix sol = new BinarySearchMatrix();
        System.out.println(sol.searchMatrix(matrix, 92));
    }

}
