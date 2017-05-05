/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.strings;

/**
 *
 * @author Dang
 */
public class RomanToInteger {

    public int romanToInt(String a) {
        int count = 0;
        for (int i = 0; i < a.length(); i++) {
            switch (a.charAt(i)) {
                case 'I':
                    if (i + 1 < a.length()
                            && (a.charAt(i + 1) == 'V' || a.charAt(i + 1) == 'X')) {
                        count--;
                    } else {
                        count++;
                    }
                    break;
                case 'V':
                    count += 5;
                    break;
                case 'X':
                    if (i + 1 < a.length()
                            && (a.charAt(i + 1) == 'L' || a.charAt(i + 1) == 'C')) {
                        count -= 10;
                    } else {
                        count += 10;
                    }
                    break;
                case 'L':
                    count += 50;
                    break;
                case 'C':
                    if (i + 1 < a.length()
                            && (a.charAt(i + 1) == 'D' || a.charAt(i + 1) == 'M')) {
                        count -= 100;
                    } else {
                        count += 100;
                    }
                    break;
                case 'D':
                    count += 500;
                    break;
                case 'M':
                    count += 1000;
                    break;
            }
        }
        return count;
    }
}
