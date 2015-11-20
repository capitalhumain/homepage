package graph;

import java.util.ArrayList;
import java.util.List;

/**
 * 沒有檢查例外狀況的有向圖
 * 
 * @author tzuyichao
 */
public class DirectedGraph {
    /**
     * |vertices|
     */
    private int V;
    /**
     * adjacency list of graph
     */
    private List<Integer>[] adj;
    
    public DirectedGraph(int v) {
        this.V = v;
        adj = new ArrayList[V];
        for(int i=0; i<v; i++) {
            adj[i] = new ArrayList<>();
        }
    }
    
    /**
     * add edge
     * @param from
     * @param to 
     */
    public void addEdge(int from, int to) {
        // 重複路線不加
        if(!adj[from].contains(to)) {
            adj[from].add(to);
        }
    }
    
    /**
     * |vertices|
     * @return 
     */
    public int getVerticesCount() {
        return V;
    }
    
    /**
     * get vertex's edge vertices from adj
     * @param vertex
     * @return 
     */
    public List<Integer> getEdges(int vertex) {
        return adj[vertex];
    }
}
