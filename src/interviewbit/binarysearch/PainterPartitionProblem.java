/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.binarysearch;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 *
 * @author Dang
 */
public class PainterPartitionProblem {

    /*** binary search solution ***/
    public int paint(int p, int pt, ArrayList<Integer> arr) {
        int min = arr.get(0); // only a single painter can work on single largest unit
        int max = arr.get(0); // sum of all units to be painted
//        int max = arr.stream().mapToInt(i->i).sum(); // sum of input array

        // iterate since array is not sorted
        for (int i = 1; i < arr.size(); i++) {
            max += arr.get(i);
            if (arr.get(i) > min) {
                min = arr.get(i);
            }
        }

        // binary search for smallest isPossible time
        int mid = 0;
        while (min <= max) {
            mid = min + ((max - min) / 2);
            if (isPossible(p, mid, arr)) {
                max = mid - 1;
            } else {
                min = mid + 1;
                mid = min; // corrects for lowering max out of isPossible range
            }
        }

        BigInteger bi = BigInteger.valueOf(mid);
        BigInteger pro = bi.multiply(BigInteger.valueOf(pt));
        BigInteger mod = pro.mod(new BigInteger("10000003"));

        return mod.intValue();
    }
    // return if job can be done in alloted time
    private boolean isPossible(int p, int time, ArrayList<Integer> arr) {
        int count = 0;
        for (int i = 0; i < arr.size(); i++) {
            if (count + arr.get(i) <= time) {
                count += arr.get(i);
            } else {
                p--;
                count = arr.get(i);
                if (p == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    
    
    
    
    
    
    /*** dynamic programming solution ***/
    // p: painters, pt: paint time, arr: list of units to be painted
    public int paintDP(int p, int pt, ArrayList<Integer> arr) {
        int len = arr.size();

        // index here hold corresponding unit to paint from input array
        // plus all cumulative units to left
        // represents left subproblem
        long[] cum = new long[len + 1];
        // matrix columns holds number of painters
        // matrix rows corresponds to units paint
        //   column 1 painter is copy of cum[]
        // rows will be filled out as left-subproblem
        //  where row number is length of subproblem
        //  and k separates left/right partition to be painted
        // Value of matrix represents optimal combination at length r
        //  where maximum value from left/right partition is minimized
        long[][] M = new long[len + 1][p + 1];

        for (int r = 1; r <= len; r++) {
            cum[r] = cum[r - 1] + arr.get(r - 1);
        }
        for (int r = 1; r <= len; r++) {
            M[r][1] = cum[r];
        }
        for (int c = 2; c <= p; c++) { // c: column
            for (int r = 2; r <= len; r++) { // r: row
                // row represents length of subproblem
                // best is smallest maxPartition for subLength
                long best = Long.MAX_VALUE;
                // iterate every left/right partition of subLength r
                for (int k = 1; k <= r; k++) { // k: divides left/right partition
                    // maximum partition value
                    // M[k][c - 1] is left partition
                    // cum[r] - cum[k] is right partition
                    long max = M[k][c - 1] > cum[r] - cum[k] ? M[k][c - 1] : cum[r] - cum[k];
                    best = best < max ? best : max;
                }
                M[r][c] = best;
            }
        }

        // debug use only
//        for (int i = 0; i < len; i++) {
//            System.out.print(arr.get(i) + "   ");
//        }
//        System.out.println("\n");
//        for (int i = 0; i <= len; i++) {
//            System.out.print(cum[i] + "   ");
//        }
//        System.out.println("\n");
//        for (int i = 0; i <= len; i++) {
//            for (int j = 0; j <= p; j++) {
//                System.out.print(M[i][j] + "   ");
//            }
//            System.out.println("");
//        }
        BigInteger bi = BigInteger.valueOf(M[len][p]);
        BigInteger pro = bi.multiply(BigInteger.valueOf(pt));
        BigInteger mod = pro.mod(new BigInteger("10000003"));

        return mod.intValue();
    }

    
    

    
    // dynamic programming solution with matrix, minus cumulative array
    public int paintMatrix(int p, int pt, ArrayList<Integer> arr) {
        int[][] m = new int[arr.size() + 1][p + 1];
        m[0][0] = arr.get(0);
        for (int i = 1; i < arr.size(); i++) {
            m[i][0] = m[i - 1][0] + arr.get(i);
        }

        for (int r = 0; r < arr.size(); r++) {
            for (int c = 1; c < p; c++) {
                
                // find best of sublength r
                int best = Integer.MAX_VALUE;
                for (int k = 0; k <= r; k++) {
                    int right = m[r][0] - m[k][0];
                    // left subproblem's size correlates to k divider
                    int left = m[k][c - 1];
                    int max = left > right ? left : right;
                    best = max < best ? max : best;
                }
                m[r][c] = best;
            }
        }

        //debug
//        for (int r = 0; r < arr.size(); r++) {
//            for (int c = 0; c < p; c++) {
//                System.out.print(m[r][c] + " ");
//            }
//            System.out.println("");
//        }
        BigInteger bi = BigInteger.valueOf(m[arr.size() - 1][p - 1]);
        BigInteger pro = bi.multiply(BigInteger.valueOf(pt));
        BigInteger mod = pro.mod(new BigInteger("10000003"));
        return mod.intValue();
    }






    
    /*** recursive solution ***/
    // This method only return the first sum from right.
    // arr: list of units to be printed, length: length to calculate,
    // p: number of painters, level: when level==i, entire array is calculated
    public int partition(ArrayList<Integer> arr, int length, int p, int level) {
        if (p == 1) {
            return rangeSum(arr, 0, length - 1);
        }

        if (length == 1) {
            return arr.get(0);
        }

        int best = Integer.MAX_VALUE;
        for (int i = 1; i <= length; i++) {
            // recursion using sublength i
            int left = partition(arr, i, p - 1, level++);
            int right = rangeSum(arr, i, length - 1);

            // the maximum sum over all the partitions should be minimized
            int maximum = left > right ? left : right;
            best = best < maximum ? best : maximum;

            // debug use only
//            if (level == i) {
//                System.out.println("left:" + left + "   right:" + right + "   max:" + maximum);
//            }            
//            System.out.println("Llen:" + i + "   Rlen:" + (length - i) + "   Lpart:" + left 
//                    + "     Rpart:" + right + "        level" + level + "       p:" + (p-1)
//                    + "     max:" + maximum + "        best:" + best + "       len:" + length);
        }
        return best;
    }
    // get sum of units to be painted from start to end (inclusively)
    // use % 10000003 to avoid bit overflow
    private int rangeSum(ArrayList<Integer> arr, int start, int end) {
        int total = 0;
        for (int i = start; i <= end; i++) {
            total += arr.get(i);
        }
        return total % 10000003;
    }
    
    
    
    
    
    private int[] rightAr; // global array
    public int paintRecustion(int p, int pt, ArrayList<Integer> arr) {
        rightAr = new int[arr.size()];
        rightAr[0] = arr.get(0);
        for (int i = 1; i < arr.size(); i++) {
            rightAr[i] = arr.get(i) + rightAr[i-1];
        }
        int best = paintRecursion(arr, arr.size()-1, p);

        BigInteger bi = BigInteger.valueOf(best);
        BigInteger pro = bi.multiply(BigInteger.valueOf(pt));
        BigInteger mod = pro.mod(new BigInteger("10000003"));
        return mod.intValue();
    }
    private int paintRecursion(ArrayList<Integer> arr, int length, int p){
        // base case
        if (length == 0) {
            return arr.get(0);
        }        
        if (p == 1) {
            return rightAr[length];
        }        
        
        int best = Integer.MAX_VALUE;
        for (int i = 0; i <= length; i++) {
            int right = rightAr[length] - rightAr[i];
            int left = paintRecursion(arr, i, p-1);
            int max = left > right ? left : right;
            if (max < best) {
                best = max;
            }            
        }
        return best;
    }
}
