/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 *
 * @author Duy Dang
 */
public class WordLadderList {

    private Map<String, List<String>> map;
    private ArrayList<ArrayList<String>> results;

    public ArrayList<ArrayList<String>> findLadders(String start, String end, ArrayList<String> input) {
        Set<String> dict = new HashSet<>();
        for (String str : input) {
            dict.add(str);
        }
        results = new ArrayList<>();
        if (dict.size() == 0) {
            return results;
        }

        int min = Integer.MAX_VALUE;

        Queue<String> queue = new ArrayDeque<String>();
        queue.add(start);

        map = new HashMap<String, List<String>>();

        Map<String, Integer> ladder = new HashMap<String, Integer>(); // Key is StringVector, Value is distance from 'start'
        for (String string : dict) {
            ladder.put(string, Integer.MAX_VALUE); // initial distance from start is int_MAX
        }
        ladder.put(start, 0); // distance from start to self is 0

        dict.add(end);
        //BFS: Dijisktra search
        while (!queue.isEmpty()) {

            String word = queue.poll();

            int step = ladder.get(word) + 1; //'step' indicates how many steps are needed to travel from 'start' to 'new_word'. 

            if (step > min) {
                break;
            }

            // Use Character iteration to find all possible paths.
            // Do NOT compare one word to all the other words and check if they only differ by one character
            for (int i = 0; i < word.length(); i++) {
                StringBuilder builder = new StringBuilder(word);
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    builder.setCharAt(i, ch);
                    String new_word = builder.toString();
                    if (ladder.containsKey(new_word)) {

                        //Check if it is the shortest path from 'start' to 'new_word'.
                        if (step > ladder.get(new_word)) { 
                            continue; // shorter path already exists
                        } else if (step < ladder.get(new_word)) { // shorter path discovered
                            queue.add(new_word); // explore neighbors of 'new_word'
                            ladder.put(new_word, step); // update ladder's String with shorter 'step'
                        }

                        //Build adjacent Graph
                        if (map.containsKey(new_word)) { 
                            map.get(new_word).add(word);
                        } else {
                            List<String> list = new LinkedList<String>();
                            list.add(word);
                            map.put(new_word, list);
                            //It is possible to write three lines in one:
                            //map.put(new_word,new LinkedList<String>(Arrays.asList(new String[]{word})));
                            //Which one is better?
                        }

                        if (new_word.equals(end)) {
                            min = step;
                        }

                    }//End if dict/ladder contains new_word
                }//End:Iteration from 'a' to 'z'
            }//End:Iteration from the first to the last char of 'word'
        }//End While

        //BackTracking
        LinkedList<String> list = new LinkedList<String>();
        backTrace(end, start, list);

        return results;
    }

    // DFS to construct the path from end to start
    private void backTrace(String word, String start, List<String> list) {
        if (word.equals(start)) {
            list.add(0, start);
            results.add(new ArrayList<String>(list));
            list.remove(0);
            return;
        }
        list.add(0, word);
        if (map.get(word) != null) {
            for (String s : map.get(word)) {
                backTrace(s, start, list);
            }
        }
        list.remove(0);
    }
}
