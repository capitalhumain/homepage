package centrality;

import graph.DirectedGraph;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * Betweenness centrality
 * reference: http://algo.uni-konstanz.de/publications/b-vspbc-08.pdf
 * 
 * @author tzuyichao
 */
public final class Betweenness {
    private DirectedGraph graph;
    /**
     * queue Q, stack S (both initially empty) and for all v ∈ V :
     */
    private Queue<Integer> q;
    /**
     * queue Q, stack S (both initially empty) and for all v ∈ V :
     */
    private Stack<Integer> s;
    /**
     * distance from source
     */
    private int[] dist;
    /**
     * list of predecessors on shortest paths from source
     */
    private List<Integer>[] pred;
    /**
     * number of shortest paths from source to v ∈ V
     */
    private int[] sigma;
    /**
     * dependency of source on v ∈ V
     */
    private double[] delta;
    /**
     *  betweenness cB[v] for all v ∈ V (initialized to 0)
     */
    private int[] centrality;
    
    
    public Betweenness(DirectedGraph g) {
        this.graph = g;
        centrality = new int[graph.getVerticesCount()];
        q = new ArrayDeque<>();
        s = new Stack<>();
        dist = new int[graph.getVerticesCount()];
        pred = new ArrayList[g.getVerticesCount()];
        sigma = new int[graph.getVerticesCount()];
        delta = new double[graph.getVerticesCount()];
        
        for(int i=0; i<g.getVerticesCount(); i++) {
            pred[i] = new ArrayList<>();
        }
    }
    
    /**
     * 
     * @param g
     * @return 
     */
    public synchronized int[] compute() {
        initCentrality();
        // for s ∈ V do
        for(int source = 0; source < graph.getVerticesCount(); source++) {
            // single-source shortest-paths problem
            singleSourceShortestPath(source);
            //accumulation
            acculmulation(source);
        }
        return centrality;
    }
    
    private void initCentrality() {
        for(int i=0; i<graph.getVerticesCount(); i++) {
            centrality[i] = 0;
        }
    }
    
    private void init(int source) {
        for(int i=0; i<graph.getVerticesCount(); i++) {
            pred[i].clear();
            dist[i] = -1;
            sigma[i] = 0;
        }
        dist[source] = 0;
        sigma[source] = 1;
        q.add(source);
    }
    
    private void singleSourceShortestPath(int source) {
        // initialization
        init(source);
        
        while(!q.isEmpty()) {
            int v = q.poll();
            s.push(v);
            for(Integer w : graph.getEdges(v)) {
                // path discovery
                if(dist[w] == -1) {
                    dist[w] = dist[v] + 1;
                    q.add(w);
                }
                // path counting
                if(dist[w] == (dist[v] + 1)) {
                    sigma[w] = sigma[w] + sigma[v];
                    pred[w].add(v);
                }
            }
        }
    }
    
    private void acculmulation(int source) {
        // for v ∈ V do δ[v] ← 0
        for(int i=0; i<graph.getVerticesCount(); i++) {
            delta[i] = 0.0;
        }
        while(!s.isEmpty()) {
            int w = s.pop();
            for(int v : pred[w]) {
                delta[v] += (sigma[v]/sigma[w])*(1+delta[w]);
            }
            if(w != source) {
                centrality[w] += delta[w];
            }
        }
    }
}
