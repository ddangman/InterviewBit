/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.util.List;

/**
 *
 * @author Dang
 */
public class Sandbox {
    
    private int n;
    private List<Integer> input;
    
    public int longestSubsequenceLength(final List<Integer> input) {
        this.n = input.size();
        if (input.isEmpty()) {
            return 0;
        } else if (n == 1) {
            return 1;
        }
        this.input = input;
        
        int[] lis = generateLIS();
        int[] lds = generateLDS();
        
        int max = 0;
        for (int i = 0; i < n; i++) {
            int sum = lis[i] + lds[n - 1 - i] - 1;
            max = max > sum ? max : sum;
        }
        
        return max;
    }
    
    public int[] generateLIS() {
        int[] util = new int[n];
        int[] lis = new int[n];
        int len = 0;
        lis[0] = 1;
        util[0] = input.get(0);
        
        for (int i = 1; i < n; i++) {
            int x = input.get(i);
            if (x > util[len]) {
                util[++len] = x;
                lis[i] = len + 1;
            } else {
                int index = bisearch(x, len, util);
                util[index] = x;
                lis[i] = index + 1;
            }            
        }
        
        return lis;
    }
    
    public int[] generateLDS() {
        int[] util = new int[n];
        int[] lds = new int[n];
        int len = 0;
        util[0] = input.get(n - 1);
        lds[0] = 1;
        
        for (int i = n - 2; i >= 0; i--) {
            int x = input.get(i);
            if (x > util[len]) {
                util[++len] = x;
                lds[n - i - 1] = len + 1;
            } else {
                int index = bisearch(x, len, util);
                util[index] = x;
                lds[n - i - 1] = index + 1;
            }           
        }
        
        return lds;
    }
    
    private int bisearch(int x, int end, int[] util) {
        int start = 0;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (util[mid] == x) {
                return mid;
            } else if (util[mid] > x) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }            
        }
        
        return start;
    }
}
