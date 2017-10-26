/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.strings;

import java.util.ArrayList;

/**
 *
 * @author Dang
 */
public class JustifiedText {
    
    public ArrayList<String> fullJustify(ArrayList<String> a, int b) {
        ArrayList<String> justified = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (j < a.size()) {
            int count = a.get(i).length();
            while (j + 1 < a.size() && count + a.get(j + 1).length() + 1 <= b) {
                j++;
                count += a.get(j).length() + 1;
            }
            if (j + 1 == a.size() || j == i) {
                justified.add(bottomLine(a, i, j, b - count));
            } else {
                justified.add(returnLine(a, b, i, j, b - count + (j - i)));
            }
            j++;
            i = j;
        }
        return justified;
    }
    
    private String returnLine(ArrayList<String> a, int b, int i, int j, int space) {
        int diff = j - i;
        int div = space / diff;
        int mod = space % diff;
        int m = 0;
        StringBuilder sb = new StringBuilder();
        while (i <= j) {
            sb.append(a.get(i));
            if (i != j) {
                for (int k = 0; k < div; k++) {
                    sb.append(' ');
                }                
            }            
            
            if (m < mod) {
                sb.append(' ');
            }            
            i++;
            m++;
        }
        return sb.toString();
    }
    
    private String bottomLine(ArrayList<String> a, int i, int j, int padding) {
        StringBuilder sb = new StringBuilder();
        while (i <= j) {
            sb.append(a.get(i));
            if (i != j) {
                sb.append(' ');
            }            
            i++;
        }
        for (int k = 0; k < padding; k++) {
            sb.append(' ');
        }
        return sb.toString();
    }
}
