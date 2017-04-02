/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interviewbit.binarysearch;

import java.util.ArrayList;

/**
 *
 * @author Dang
 */
public class BinarySearchMatrix {

    public int searchMatrix(ArrayList<ArrayList<Integer>> a, int b) {
        int r = a.size();
        int c = a.get(0).size();

        int start = 0;
        int end = r - 1;
        // default value is last row for edge case
        int biRow = r - 1; // row to search column

        //binary search 1st value of rows
        int mid = 0;
        while (start <= end) {
            mid = (start + end) / 2;
            // value is on biRow
            if (a.get(mid).get(0) <= b && b <= a.get(mid).get(c - 1)) {
                break;
            }
            if (b < a.get(mid).get(0)) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        biRow = mid;
        //binary search column of biRow
        start = 0;
        end = c - 1;
        while (start <= end) {
            mid = (start + end) / 2;
            if (b == a.get(biRow).get(mid)) {
                return 1;
            }
            if (b < a.get(biRow).get(mid)) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return 0;
    }
}
