/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.strings;

import java.util.ArrayList;

/**
 *
 * @author Dang
 */
public class ValidIpAddresses {

    ArrayList<String> al;

    public ArrayList<String> restoreIpAddresses(String a) {
        al = new ArrayList<>();
        int[] array = new int[3];
        findIp(a, 0, 0, array);
        return al;
    }

    public boolean findIp(String a, int i, int dot, int[] array) {
        // valid dot placements
        if (dot == 3) {
            if (valid(a, i, a.length())) {
                saveIp(a, array);
                return true;
            } else {
                return false;
            }

        }

        int j = i;
        for (; j - i <= 3; j++) {
            if (valid(a, i, j + 1)) {
                array[dot] = j + 1;
                findIp(a, j + 1, dot + 1, array);
            }
        }

        return false;
    }

    public boolean valid(String a, int i, int j) {
        if (i >= j) {
            return false;
        }
        if (j - i > 3) {
            return false;
        }
        if (j > a.length()) {
            return false;
        }        
        
        if (a.charAt(i) == '0') {
            if (j - i == 1) {
                return true;
            }            
            return false;
        }  

        int n = Integer.parseInt(a.substring(i, j));

        return (n <= 255);
    }

    public void saveIp(String a, int[] array) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (int j = 0; j < 3; j++) {
            sb.append(a.substring(i, array[j]));
            sb.append('.');
            i = array[j];
        }
        sb.append(a.substring(i, a.length()));
        al.add(sb.toString());
    }
}
