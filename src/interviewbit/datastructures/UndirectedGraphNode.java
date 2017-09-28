/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.datastructures;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Duy Dang
 */
public class UndirectedGraphNode {

    public int label;
    public List<UndirectedGraphNode> neighbors;

    public UndirectedGraphNode(int x) {
        label = x;
        neighbors = new ArrayList<UndirectedGraphNode>();
    }

}
