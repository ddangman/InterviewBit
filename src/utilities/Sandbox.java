/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.util.ArrayList;

/**
 *
 * @author Dang
 */
public class Sandbox {

    private String input;
    private ArrayList<String> dict;
    private int len;
    private ArrayList<String> res; // result

    public ArrayList<String> wordBreak(String input, ArrayList<String> dict) {
        this.input = input;
        this.dict = dict;
        this.len = input.length();
        this.res = new ArrayList<>();
        
        wordBreakUtil(0, new ArrayList<>());
        
        return res;
    }
    
    private void wordBreakUtil(int start, ArrayList<String> build) {
        if (start >= len) {
            stringFound(build);
            return;
        }        
        
        for (int i = start; i < len; i++) {
            String sub = input.substring(start, i + 1);
            if (dict.contains(sub)) {
                build.add(sub);
                wordBreakUtil(i + 1, build);
                build.remove(build.size() - 1);
            }            
        }
    }
    
    // add valid string to result
    private void stringFound(ArrayList<String> build) {
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < build.size() - 1; i++) {
            sb.append(build.get(i));
            sb.append(' ');
        }
        sb.append(build.get(build.size() - 1));
        res.add(sb.toString());
    }
}
