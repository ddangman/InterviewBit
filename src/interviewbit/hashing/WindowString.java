/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.hashing;

/**
 *
 * @author Dang
 */
public class WindowString {
    	public String minWindow(String text, String pattern) {
            int counter = pattern.length(); // check whether substing is valid
            int begin = 0, end = 0; // two pointers representing window
            int head = 0;
            int minLen = Integer.MAX_VALUE; // minimum length of substring
            
            // map characters from pattern
            int[] map = new int[128];
            // statistic for count of char in T
            for (int i = 0; i < counter; i++) {
                char c = pattern.charAt(i);
                map[c]++;
            }
            
            while (end < text.length()) {
                // if char in S exists in T, decrease counter
                if (map[text.charAt(end)] > 0) {
                    counter--;
                }  
                // decrease map value 
                // If char does not exist in pattern, map will be negative
                map[text.charAt(end)]--;
                
                // move end to find a valid window
                end++;
                
                // when valid window is found, move start to find a smaller window
                while (counter == 0) {
                    // use minLen to hold window size
                    if (end - begin < minLen) {
                        head = begin;
                        minLen = end - begin;
                    } 
                    map[text.charAt(begin)]++; // increment char in map
                    // if char did not exist in pattern, it should have a negative value
                    // when char exists in pattern, increase counter
                    if (map[text.charAt(begin)] > 0) {
                        counter++;
                    }  
                    begin++; // reduce window size
                }
            }
            
            return minLen == Integer.MAX_VALUE ? "" : text.substring(head, head + minLen);
	}
}
