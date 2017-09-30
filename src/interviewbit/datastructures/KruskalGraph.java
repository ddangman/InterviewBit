/*
 * This is my project containing my solutions to InterviewBit problems.
 */
package interviewbit.datastructures;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Duy Dang
 */
public class KruskalGraph {

    // A class to represent a graph edge
    class Edge implements Comparable<Edge> {

        int src, dest, weight;
        Edge(int s, int d, int w) {
            this.src = s;
            this.dest = d;
            this.weight = w;
        }

        // Comparator function used for sorting edges based on
        // their weight
        @Override
        public int compareTo(Edge compareEdge) {
            return this.weight - compareEdge.weight;
        }
    };

    // A class to represent a vector subset for union-find
    class Vector {

        int parent, rank;
    };

    int V, E, Z;    // V-> no. of vertices & E->no.of edges
    Edge edge[]; // collection of all edges

    // Creates a graph with V vertices and E edges
    public KruskalGraph(int v, ArrayList<ArrayList<Integer>> edges, int z) {
        Z = z; // zeroth index
        V = v;
        E = edges.size();
        edge = new Edge[E];
        for (int i = 0; i < E; ++i) {
            ArrayList<Integer> e = edges.get(i);
            edge[i] = new Edge(e.get(0) - Z, e.get(1) - Z, e.get(2));
        }
    }
    public KruskalGraph(int v, ArrayList<ArrayList<Integer>> edges) {
        Z = 0; // no adjustment needed
        V = v;
        E = edges.size();
        edge = new Edge[E];
        for (int i = 0; i < E; ++i) {
            ArrayList<Integer> e = edges.get(i);
            edge[i] = new Edge(e.get(0), e.get(1), e.get(2));
        }
    }

    // A utility function to find set of an element i
    // (uses path compression technique)
    int find(Vector subsets[], int i) {
        // find root and make root as parent of i (path compression)
        if (subsets[i].parent != i) {
            subsets[i].parent = find(subsets, subsets[i].parent);
        }

        return subsets[i].parent;
    }

    // A function that does union of two sets of x and y
    // (uses union by rank)
    void Union(Vector subsets[], int x, int y) {

        // Attach smaller rank tree under root of high rank tree
        // (Union by Rank)
        if (subsets[x].rank < subsets[y].rank) {
            subsets[x].parent = y;
        } else if (subsets[x].rank > subsets[y].rank) {
            subsets[y].parent = x;
        } // If ranks are same, then make one as root and increment
        // its rank by one
        else {
            subsets[y].parent = x;
            subsets[x].rank++;
        }
    }

    // The main function to construct MST using Kruskal's algorithm
    public void KruskalMST() {
        Edge result[] = new Edge[V];  // Tnis will store the resultant MST
        int e = 0;  // An index variable, used for result[]
        int i = 0;  // An index variable, used for sorted edges
        for (i = 0; i < V; ++i) {
            result[i] = new Edge(0, 0, 0);
        }

        // Step 1:  Sort all the edges in non-decreasing order of their
        // weight.  If we are not allowed to change the given graph, we
        // can create a copy of array of edges
        Arrays.sort(edge);

        // Allocate memory for creating V subsets
        Vector subsets[] = new Vector[V];
        for (i = 0; i < V; ++i) {
            subsets[i] = new Vector();
        }

        // Create V subsets with single elements
        for (int v = 0; v < V; ++v) {
            subsets[v].parent = v;
            subsets[v].rank = 0;
        }

        i = 0;  // Index used to pick next edge

        // Number of edges to be taken is equal to V-1
        while (e < V - 1) {
            // Step 2: Pick the smallest edge. And increment the index
            // for next iteration
            Edge next_edge = edge[i++];

            int x = find(subsets, next_edge.src);
            int y = find(subsets, next_edge.dest);

            // If including this edge does't cause cycle, include it
            // in result and increment the index of result for next edge
            if (x != y) {
                result[e++] = next_edge;
                Union(subsets, x, y);
            }
            // Else discard the next_edge
        }

        // print the contents of result[] to display the built MST
        System.out.println("Following are the edges in the constructed MST");
        for (i = 0; i < e; ++i) {
            System.out.println((result[i].src + Z)+ " -- " + (result[i].dest + Z)+ " == "
                    + result[i].weight);
        }
    }

    // The main function to get MST weight using Kruskal's algorithm
    public int findMSTweight() {
        int sum = 0;
        int e = 0;  // An index variable, used for result[]
        int i = 0;  // An index variable, used for sorted edges

        // Step 1:  Sort all the edges in non-decreasing order of their
        // weight.  If we are not allowed to change the given graph, we
        // can create a copy of array of edges
        Arrays.sort(edge);

        // Allocate memory for creating V subsets
        Vector subsets[] = new Vector[V];
        for (i = 0; i < V; ++i) {
            subsets[i] = new Vector();
        }

        // Create V subsets with single elements
        for (int v = 0; v < V; ++v) {
            subsets[v].parent = v;
            subsets[v].rank = 0;
        }

        i = 0;  // Index used to pick next edge

        // Number of edges to be taken is equal to V-1
        while (e < V - 1) {
            // Step 2: Pick the smallest edge. And increment the index
            // for next iteration
            Edge next_edge = edge[i++];

            int x = find(subsets, next_edge.src);
            int y = find(subsets, next_edge.dest);

            // If including this edge does't cause cycle, include it
            // in result and increment the index of result for next edge
            if (x != y) {
                sum += next_edge.weight;
                Union(subsets, x, y);
                e++;
            }
            // Else discard the next_edge
        }

        return sum;
    }
}
