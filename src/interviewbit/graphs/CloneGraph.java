/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.graphs;

import interviewbit.datastructures.UndirectedGraphNode;
import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author Duy Dang
 */
public class CloneGraph {

    public UndirectedGraphNode cloneGraphBFS(UndirectedGraphNode root) {
        HashMap<Integer, UndirectedGraphNode> map = new HashMap<>(); // points to cloned nodes
        LinkedList<UndirectedGraphNode> q = new LinkedList<>(); // queue unexplored nodes
        q.add(root);
        while (!q.isEmpty()) { // if more nodes needs to be visited
            UndirectedGraphNode pop = q.pop();
            UndirectedGraphNode clone = map.get(pop.label); // get cloned node
            if (clone == null) { // cloned node does not exist, make clone
                clone = new UndirectedGraphNode(pop.label);
                map.put(pop.label, clone); // maintain pointer
            }              
            
            for (UndirectedGraphNode nb : pop.neighbors) {
                UndirectedGraphNode cnb = map.get(nb.label); // copy's neighbor
                if (cnb == null) {
                    cnb = new UndirectedGraphNode(nb.label);
                    map.put(nb.label, cnb);
                    q.add(nb); // add unexplored neighbor to queue
                } 
                clone.neighbors.add(cnb); // add neighbor to copy
            }
        }
        return map.get(root.label); // return root's clone
    }

    private HashMap<Integer, UndirectedGraphNode> map = new HashMap<>();

    public UndirectedGraphNode cloneGraphDFS(UndirectedGraphNode root) {
        return dfsRec(root);
    }

    private UndirectedGraphNode dfsRec(UndirectedGraphNode node) {
        if (node == null) {
            return null;
        }

        if (map.containsKey(node.label)) {
            return map.get(node.label);
        }
        UndirectedGraphNode clone = new UndirectedGraphNode(node.label);
        map.put(clone.label, clone); // add to map before recursion to prevent heap overflow
        for (UndirectedGraphNode neighbor : node.neighbors) {
            clone.neighbors.add(dfsRec(neighbor));
        }
        return clone;
    }
}
