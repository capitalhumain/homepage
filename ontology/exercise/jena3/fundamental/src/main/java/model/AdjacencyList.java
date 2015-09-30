package model;

import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;

public class AdjacencyList {
	// 不想給連到相同vertex多條路線的機會
	private Map<String, Set<String> > graph;
	
	public boolean hasVertex(String v) {
		if(null == v) {
			new IllegalArgumentException("input argument v is null");
		}
		if(null == graph) {
			throw new IllegalStateException("graph does not init");
		}
		return graph.containsKey(v);
	}
	
	public AdjacencyList(Set<String> vertices) {
		graph = new HashMap<String, Set<String> >();
		for(String v : vertices) {
			graph.put(v, new HashSet<String>());
		}
	}
	
	public void addEdge(String from, String to) {
    	if(null == from || null == to) {
    		throw new IllegalArgumentException("input argument can not be null");
    	}
    	// check matrix
    	if(null == graph) {
    		throw new IllegalStateException("graph does not init");
    	}
    	// check vertices
    	if(!hasVertex(from) || !hasVertex(to)) {
    		throw new IllegalArgumentException("verteices not found");
    	}
    	// exception handling for list
    	if(null == graph.get(from)) {
    		graph.put(from, new HashSet<String>());
    	}
    	graph.get(from).add(to);
    }
	
	public void inspect() {
		for(String k : graph.keySet()) {
			System.out.printf( "%s: ", k );
			for(String t : graph.get(k)) {
				System.out.printf( "%s, ", t );
			}
			System.out.println();
		}
	}
}
