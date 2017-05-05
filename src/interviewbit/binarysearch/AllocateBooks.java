/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.binarysearch;

import java.util.ArrayList;

/**
 *
 * @author Dang
 */
public class AllocateBooks {
    
    // binary solution
    public int books(ArrayList<Integer> a, int b) {
        if (b > a.size()) {
                return -1;
        }        
        int min = Integer.MAX_VALUE;
        int max = 0;
        for (int i = 0; i < a.size(); i++) {
            max += a.get(i);
            if (a.get(i) < min) {
                min = a.get(i);
            }            
        }

        // binary search
        int start = min;
        int end = max;
        int mid = 0;
        int best = Integer.MAX_VALUE;
        while (start <= end) {
            mid = (start + end) / 2;
            if (isPossible(a, b, mid)) {
                end = mid - 1;
                if (mid < best) {
                    best = mid;
                }
            } else {
                start = mid + 1;
            }
        }
        
        return best;
    }
    
    public boolean isPossible(ArrayList<Integer> a, int b, int max) {
        int count = 0;
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i) > max) {
                return false;
            }            
            if (count + a.get(i) <= max) {
                count += a.get(i);
            } else {
                b--;
                if (b == 0) {
                    return false;
                }                
                count = a.get(i);
            }            
        }
        return true;
    }
    
    // dynamic programming solution
    public int booksDP(ArrayList<Integer> a, int b) {
        if (b > a.size()) {
            return -1;
        } else if ( b == a.size()) {
            // one book per person, return book with max pages
            return a.stream().mapToInt(Integer::intValue).max().getAsInt();
        }
        
        // matrix column correlates to pages
        // rows correlates to readers
        // value is best spread of pages for given readers
        int[][] m = new int[a.size()][b];
        // initialize column 0 with sum(0,i) of input array
        m[0][0] = a.get(0);
        for (int i = 1; i < a.size(); i++) {
            m[i][0] = m[i-1][0] + a.get(i);
        }
        
        for (int c = 1; c < b; c++) {
            for (int r = 0; r < a.size(); r++) {
                
                // find best for each sublength r
                // including when left/right partition equals 0
                int best = Integer.MAX_VALUE;
                // k separate left partition from right partition
                for (int k = 0; k <= r; k++) {
                    // left will be best spread of pages
                    // for length k and (r - 1) readers
                    int left = m[k][c-1];
                    // right will be total pages from length - k
                    int right = m[r][0] - m[k][0];
                    // most pages given to a reader for current sublength
                    int max = left > right ? left : right;                    
                    if (max < best) {
                        // keep minimal max
                        best = max;
                    }                    
                }
                
                // save best spread of pages across readers to matrix
                m[r][c] = best;
            }
            
        }
       
        return m[a.size()-1][b-1];
    }
}
