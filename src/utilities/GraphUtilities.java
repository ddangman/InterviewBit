/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package utilities;

import interviewbit.datastructures.UndirectedGraphNode;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Duy Dang
 */
public class GraphUtilities {
    public static UndirectedGraphNode genGraph(String s) {
        Scanner scanner = new Scanner(s);
        int vCount = scanner.nextInt();
        int count = 1;
        int key = scanner.nextInt();
        UndirectedGraphNode root = new UndirectedGraphNode(key);
        HashMap<Integer, UndirectedGraphNode> map = new HashMap<>();
        int[] keys = new int[vCount];
        keys[0] = key;
        map.put(key, root);
        while (count < vCount) {
            key = scanner.nextInt();
            UndirectedGraphNode node = new UndirectedGraphNode(key);
            map.put(key, node);
            keys[count] = key;
            count++;
        }
        
        boolean[][] hasEdge = new boolean[vCount][vCount];
        int r = 0;
        int c = 0;
        while (scanner.hasNextInt()) {
            int one = scanner.nextInt();
            if (one == 1) {
                hasEdge[r][c] = true;
            }   
            c++;
            if (c == vCount) {
                r++;
                c = 0;
            }            
        }
        
        for (int i = 0; i < vCount; i++) {
            UndirectedGraphNode node = map.get(keys[i]);
            for (int j = 0; j < vCount; j++) {
                if (hasEdge[i][j] || hasEdge[j][i]) {
                    node.neighbors.add(map.get(keys[j]));
                }                
            }
        }
        
        return root;
    }
}
