package model;

import java.util.HashSet;
import java.util.Set;

public class MatrixTest {

	public static void main(String[] args) {
		Set<String> vertices = new HashSet<String>();
		vertices.add("this");
		vertices.add("is");
		vertices.add("just");
		vertices.add("test");
		
		AdjacencyMatrix matrix = new AdjacencyMatrix(vertices);
		matrix.initMatrix();
		matrix.inspect();
		
		matrix.addEdge("this", "just");
		matrix.inspect();
	}

}
