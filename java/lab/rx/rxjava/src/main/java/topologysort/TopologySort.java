package topologysort;

import java.util.Scanner;

public class TopologySort {
    final int MAX_V = 100;
    int N = 0;
    int place = 0;
    Scanner inputStream = null;
    boolean[] visited = new boolean[MAX_V+1];

    int[]  top_order = new int[MAX_V+1];
    Node[] adjlist = new Node[MAX_V+1];



    public static void main(String[] args) {

    }
}
