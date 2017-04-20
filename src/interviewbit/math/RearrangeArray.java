package interviewbit.math;

import java.util.ArrayList;

public class RearrangeArray {

    public void arrange(ArrayList<Integer> a) {
        int n = a.size();
        
        // First step: Increase all values by (arr[arr[i]]%n)*n
        // adding ( finalIndex * arraySize) 
        // since step 1 is always adding a multiple of arraySize,
        // % arraySize will always allow retrieval of orginal value
        for (int i = 0; i < n; i++) {
            int value = a.get(i);
            int combine = (
                            ( 
                              ( 
                                a.get( value ) 
                              ) % n 
                            ) * n
                          ) + value;
            a.set(i, combine);
        }
        
        // Second Step: Divide all values by n
        // floor division will only return finalIndex
        // and discard the value of originalIndex since it is always less than n
        for (int i = 0; i < n; i++) {
            int div = a.get(i) / n;
            a.set(i, div );
        }
    }
}
