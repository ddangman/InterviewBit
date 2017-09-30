package interviewbit;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Main class for running InterviewBit solutions.
 *
 * @author Dang
 */
public class InterviewBit {

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> edges = utilities.MatrixUtilities.StringToIntegerMatrix(3, "1 2 1 2 3 4 1 4 3 4 3 2 1 3 10");
        System.out.println(solve(4, edges));
    }
    
    // calculate minimum spanning tree using Kruskal's union
    public static int solve(int vec, ArrayList<ArrayList<Integer>> edgeList) {
        Graph g = new Graph(vec, edgeList);
        return g.findMinWeight();
    }
    
    static class Graph {
        class Edge implements Comparable<Edge>{
            int source, destination, weight;
            Edge(int src, int dest, int wt) {
                this.source = src;
                this.destination = dest;
                this.weight = wt;
            }

            @Override
            public int compareTo(Edge compareEdge) {
                return this.weight - compareEdge.weight; 
            }
        }
        
        class Vector {
            int set, rank;
            Vector(int set) {
                this.set = set;
                this.rank = 0;
            }
        }
        
        private Vector[] vectors;
        private Edge[] edges;
        
        Graph (int vec, ArrayList<ArrayList<Integer>> edgeList) {
            this.edges = new Edge[edgeList.size()];
            for (int i = 0; i < edgeList.size(); i++) {
                ArrayList<Integer> e = edgeList.get(i);
                edges[i] = new Edge(e.get(0) - 1, e.get(1) - 1, e.get(2));
            }
            Arrays.sort(edges);
            
            vectors = new Vector[vec];
            for (int i = 0; i < vec; i++) {
                vectors[i] = new Vector(i);
            }
        }
        
        int find (int v) {
            if (v != vectors[v].set) {
                vectors[v].set = find(vectors[v].set);
            }
            
            return vectors[v].set;
        }
        
        void union(Vector a, Vector b) {
            if (a.rank > b.rank) {
                b.set = a.set;
            } else if (b.rank > a.rank) {
                a.set = b.set;
            } else {
                a.set = b.set;
                b.rank++;
            }          
        }
        
        int findMinWeight(){
            int size = 0; // exit when v-1 edges are weighted
            int weight = 0;
            int e = 0; // iterates edges
            while (size < vectors.length - 1) {
                int ps = find(edges[e].source);
                int pd = find(edges[e].destination);
                if (ps != pd) {
                    union(vectors[ps], vectors[pd]);
                    weight += edges[e].weight;
                    size++;
                }                
                e++;
            }
            
            return weight;
        }
    }


}
