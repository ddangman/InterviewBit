/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.twopointers;

import java.util.ArrayList;

/**
 *
 * @author Dang
 */
public class DebugRemoveElements {
    	public int removeDuplicates(ArrayList<Integer> a) {
	    int i = 0; int k = i + 2;
	    
	    // this is O(n) best case, O(n^2) if k runs the entire length before restarting at i + 2
	    while(i < a.size() && k < a.size())
	    {
	        // unnecessary waste of memory. How would an integer exceed max value without a mathematical operation?
	        if ((long)a.get(i) == (long)a.get(k))
	        {
	            a.set(k, Integer.MAX_VALUE - 10);
	            k++;
	        }
	        else 
	        {
	            i++;
	            k = i + 2;
	        }
	    }
	    //System.out.println(a+"aa");
	    i = 0;
            
            // waste of computing. set a variable in your previous loop for i to jump to
            // this code will also fail if the ArrayList does contain Integer.MAX - 10
	    while(i < a.size() && a.get(i) != Integer.MAX_VALUE - 10)
	    {
	        i++;
	    }
	    int p = i;
	    int q = i;
            // 3rd while loop with 2 variables running length of list will only add to your time complexity
	    while(p < a.size() && q < a.size())
	    {
	        if (a.get(p) == Integer.MAX_VALUE - 10)
	        {
	            p++;
	        }
	        else
	        {
	            a.set(q, a.get(p));
	            a.set(p, Integer.MAX_VALUE - 10);
	            q++;
	            p++;
	        }
	    }
	    //System.out.println(a+"aa");
	    
            // good. Removing last element from ArrayList O(1)
            // otherwise it can be O(n)
            // https://stackoverflow.com/questions/24462513/time-complexity-for-java-arraylist-removeelement
	    for (int j = a.size() - 1; j > -1; j--)
	    {
	        if (a.get(j) == Integer.MAX_VALUE - 10)
	        {
	            a.remove(j);
	        }
	    }
	    return a.size();
	}
}
