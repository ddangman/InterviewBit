package interviewbit;

import interviewbit.graphs.ValidPath;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Main class for running InterviewBit solutions.
 *
 * @author Dang
 */
public class InterviewBit {

    public static void main(String[] args) {
//        generateTextNames(); 
    }

    /**
     * this is the description of the method
     * @return nothing
     */
    private static void generateTextNames() {
        ValidPath vp = new ValidPath();
        System.out.println(vp.solve(2, 3, 1, 1,
                new ArrayList<Integer>(Arrays.asList(1, 2)),
                new ArrayList<Integer>(Arrays.asList(1, 3)))); // no
    }

}
