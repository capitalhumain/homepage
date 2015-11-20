package centrality;

import graph.DirectedGraph;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author tzuyichao
 */
public class BetweennessTest {
    @Test
    public void testKanKan() {
        DirectedGraph g = new DirectedGraph(5);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(1, 4);
        
        Betweenness centrality = new Betweenness(g);
        int[] result = centrality.compute();
        for(int i=0; i<result.length; i++) {
            System.out.println(String.format("%d: %d", i, result[i]));
        }
        assertEquals(0, result[0]);
        assertEquals(3, result[1]);
        assertEquals(2, result[2]);
        assertEquals(0, result[3]);
        assertEquals(0, result[4]);
    }
}
