/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.graphs;

import java.util.ArrayList;

/**
 * There are a total of N courses you have to take, labeled from 1 to N. 
 * Some courses may have prerequisites, for example to take course 2 you have to 
 * first take course 1, which is expressed as a pair: [1,2].
 * Example: If N = 3 and the prerequisite pairs are [1,2] and [2,3], then you 
 * can finish courses in the following order: 1, 2 and 3. But if N = 2 and the 
 * prerequisite pairs are [1,2] and [2,1], then it is not possible for you to 
 * finish all the courses.
 * Given the total number of courses and a list of prerequisite pairs, is it 
 * possible for you to finish all courses. return 1/0 if it is possible/not possible.
 * The list of prerequisite pair are given in two integer arrays B and C where 
 * B[i] is a prerequisite for C[i].
 * 
 * Logic: Course completion is not possible if there exists a loop in prerequisites
 * so that a distant prerequisite for a class is itself.
 * 
 * Solution: Set class as parent and prerequisite as child.
 * If any child belongs to the same set as its parent, there exists a loop.
 * @author Duy Dang
 */
public class CourseCompletionGivenPrerequisites {

    private int[] set; // set representative
    public int solve(int a, ArrayList<Integer> b, ArrayList<Integer> c) {
        set = new int[a + 1];
        for (int i = 0; i < a; i++) {
            set[i] = i;
        }
        
        for (int i = 0; i < b.size(); i++) {
            int pre = getSet(b.get(i));
            int post = getSet(c.get(i));
            
            if (pre == post) { // loop found
                return 0;
            }            
            set[pre] = post; // union
        }
        return 1;
    }
    
    private int getSet(int r) {
        if (set[r] != r) {
            set[r] = getSet(set[r]);
        }        
        
        return set[r];
    }
}
