package model;

import java.util.HashSet;
import java.util.Set;

public class AdjacencyMatrix {
	// LinkedHashSet是insertion order的set
	private Set<String> cachedVertices;
    private String[] vertices;
    private int[][] matrix;
    
    public boolean hasVertex(String v) {
    	if(null == v) {
    		throw new IllegalArgumentException("input argument can not be null");
    	}
    	for(int i=0; i<vertices.length; i++) {
    		if(vertices[i].equals(v)) {
    			return true;
    		}
    	}
    	return false;
    }
    
    public int indexOfVertex(String v) {
    	if(null == v) {
    		throw new IllegalArgumentException("input argument can not be null");
    	}
    	for(int i=0; i<vertices.length; i++) {
    		if(vertices[i].equals(v)) {
    			return i;
    		}
    	}
    	return -1;
    }
    
    public AdjacencyMatrix(Set<String> vertices) {
    	this.vertices = new String[vertices.size()];
    	int i = 0;
    	for(String vertex : vertices) {
    		this.vertices[i] = vertex;
    		i++;
    	}
    	initMatrix();
    }
    
    public void initMatrix() {
    	if(null == vertices || vertices.length == 0) {
    		throw new IllegalStateException("vertices is null or empty");
    	}
    	int size = vertices.length;
    	matrix = new int[size][size];
    }
    
    public void inspect() {
    	int size = vertices.length;
    	int idx = 0;
    	for(String vertex : this.vertices) {
    		System.out.println(String.format("%d: %s", idx, vertex));
    		idx++;
    	}
    	for(int i=0; i<size; i++) {
    		for(int j=0; j<size; j++) {
    			System.out.printf(String.format("%4d", matrix[i][j]));
    		}
    		System.out.println();
    	}
    }
    
    public void addEdge(String from, String to) {
    	if(null == from || null == to) {
    		throw new IllegalArgumentException("input argument can not be null");
    	}
    	// check matrix
    	if(null == matrix) {
    		throw new IllegalStateException("matrix does not init");
    	}
    	// check vertices
    	if(!hasVertex(from) || !hasVertex(to)) {
    		throw new IllegalArgumentException("verteices not found");
    	}
    	int f = indexOfVertex(from);
    	int t = indexOfVertex(to);
    	if(-1 == f || -1 == t) {
    		throw new IllegalArgumentException("verteices not found");
    	}
    	
    	matrix[f][t] = 1;
    }
    
    public Set<String> getVertices() {
    	if(null == cachedVertices) {
    		Set<String> result = new HashSet<String>();
        	for(String v: vertices) {
        		result.add(v);
        	}	
    	}
    	return cachedVertices;
    }
}
