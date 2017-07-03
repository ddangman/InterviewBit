package interviewbit.math;

import java.util.ArrayList;

/*
    Arr[i] -> Arr[Arr[i]]

    (Value to keep * array size) + value to drop = combined values
    If values are already combined, modulus array size will reduce to value to drop
    Combined values % array size = value to drop
    Combined values / array size = value to keep
    Since value to drop is always less than array size, floor division will drop it

    Input: arr[]  = {3, 2, 0, 1}
    Output: arr[] = {1, 0, 3, 2}
    Output[0] = Input[3]
    Combined values = (1 * 4) + 3 = 7
    7 % 4 = 3 value to drop 
    7 / 4 = 1 value to keep 
*/
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
            int floored = a.get(i) / n;
            a.set(i, floored );
        }
    }
}
