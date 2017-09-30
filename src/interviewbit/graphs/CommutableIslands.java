/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.graphs;

import interviewbit.datastructures.KruskalGraph;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Duy Dang
 */
public class CommutableIslands {

    // calculate minimum spanning tree using Kruskal's union
    public int solve(int vec, ArrayList<ArrayList<Integer>> edges) {
        // build graph
        KruskalGraph graph = new KruskalGraph(vec, edges, 1);
        return graph.findMSTweight();
    }

}
