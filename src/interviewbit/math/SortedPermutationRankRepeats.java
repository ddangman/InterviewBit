/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.math;

/**
 *
 * @author Dang
 */
public class SortedPermutationRankRepeats {

    public int rankPerm(String perm) {
        long rank = 1;
        long suffixPermCount = 1;
        java.util.Map<Character, Integer> charCounts
                = new java.util.HashMap<Character, Integer>();
        for (int i = perm.length() - 1; i > -1; i--) {
            char x = perm.charAt(i); // current char
            // count of char from index to end
            int xCount = charCounts.containsKey(x) ? charCounts.get(x) + 1 : 1;
            charCounts.put(x, xCount);
            // go through character map
            for (java.util.Map.Entry<Character, Integer> e : charCounts.entrySet()) {
                if (e.getKey() < x) { // keyChar comes before currentChar
                    // add suffixPermCount for every char before current
                    // e.getValue is multiplier for repeated characters
                    // div by xCount if currentChar is repeated
                    //  otherwise 1 changes nothing
                    rank += suffixPermCount * e.getValue() / xCount;
                }
            }
            // multiply by length to increase permutation factorial
            suffixPermCount *= perm.length() - i;
            // divide by repeatedChar!
            suffixPermCount /= xCount;

        }
        return (int) rank;
    }

    public int rankPermPrint(String perm) {
        long rank = 1;
        long suffixPermCount = 1;
        java.util.Map<Character, Integer> charCounts
                = new java.util.HashMap<Character, Integer>();
        for (int i = perm.length() - 1; i > -1; i--) {
            char x = perm.charAt(i); // current char
            int l = perm.length() - i;
            System.out.print("Length: " + l + " char: ");
            System.out.println(x);
            // count of char from index to end
            int xCount = charCounts.containsKey(x) ? charCounts.get(x) + 1 : 1;
            charCounts.put(x, xCount);
            // go through character map
            for (java.util.Map.Entry<Character, Integer> e : charCounts.entrySet()) {
                if (e.getKey() < x) { // keyChar comes before currentChar
                    long fact = suffixPermCount * e.getValue() / xCount;
                    System.out.println(e.getKey() + " " + fact);
                    rank += fact;
                }
            }
            suffixPermCount *= perm.length() - i;
            suffixPermCount /= xCount;

        }
        return (int) rank;
    }

    public int rankPermModular(String perm) {
        long rank = 1;
        long suffixPermCount = 1;
        java.util.Map<Character, Integer> charCounts
                = new java.util.HashMap<Character, Integer>();
        for (int i = perm.length() - 1; i > -1; i--) {
            char x = perm.charAt(i); // current char
            // count of char from index to end
            int xCount = charCounts.containsKey(x) ? charCounts.get(x) + 1 : 1;
            charCounts.put(x, xCount);
            // go through character map
            for (java.util.Map.Entry<Character, Integer> e : charCounts.entrySet()) {
                if (e.getKey() < x) { 
                    rank += suffixPermCount * e.getValue() / xCount;
                }
            }
            
            suffixPermCount *= perm.length() - i;
            suffixPermCount /= xCount;

        }
        return (int) rank;
    }

}
