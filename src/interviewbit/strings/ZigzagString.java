/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.strings;

import java.util.Arrays;

/**
 *
 * @author Dang
 */
public class ZigzagString {

    public String convert(String a, int b) {
        if (b == 1) {
            return a;
        }  
        
        boolean up = true;
        int floor = b - 1;
        StringBuilder[] sb = new StringBuilder[b];
        Arrays.setAll(sb, i -> new StringBuilder());
        int n = 0;
        for (int i = 0; i < a.length(); i++) {
            sb[n].append(a.charAt(i));
            if (up) {
                n++;
                if (n == floor) {
                    up = false;
                }
            } else {
                n--;
                if (n == 0) {
                    up = true;
                }
            }
        }
        for (int i = 1; i < b; i++) {
            sb[0].append(sb[i].toString());
        }
        return sb[0].toString();
    }
}
