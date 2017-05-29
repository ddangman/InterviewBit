/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.strings;

import java.util.ArrayList;

/**
 *
 * @author Dang
 */
public class PrintBullseye {

    public ArrayList<ArrayList<Integer>> prettyPrint(int a) {
        ArrayList<ArrayList<Integer>> pp = new ArrayList<>();
        for (int i = 0; i < a; i++) {
            ArrayList<Integer> row = new ArrayList<Integer>();
            int floor = a - i;
            for (int j = 0; j < a; j++) {
                if (a - j < floor) {
                    row.add(floor);
                } else {
                    row.add(a - j);
                }               
            }
            
            // mirror horzontally
            for (int j = row.size() - 2; j >= 0; j--) {
                row.add(row.get(j));
            }
            pp.add(row);
        }
        
        // mirror vertically
        for (int i = pp.size() - 2; i >= 0; i--) {
            pp.add(pp.get(i));
        }
        return pp;
    }
}
