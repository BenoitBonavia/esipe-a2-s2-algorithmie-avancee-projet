package project.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import project.AdjGraph;
import project.Graph;
import project.Graphs;

import static org.junit.jupiter.api.Assertions.*;

class GraphsTest {
    private static Graph graph;
    static{
        graph = new AdjGraph(5);
        graph.addEdge(0, 1, 5);
        graph.addEdge(1, 2, 6);
        graph.addEdge(1, 3, 2);
        graph.addEdge(3, 2, 1);
        graph.addEdge(0, 4, 6);
    }
    @Test
    void astar() {
        assertEquals(8, Graphs.astar(graph, 0, 2));
    }
    @Test
    void astarWithUnreachableTarget() {
        assertEquals(-1, Graphs.astar(graph, 2, 0));
    }
    @Test
    void astarWithNotExistingNode() {
        Graph graph = new AdjGraph(5);
        assertThrows(IllegalArgumentException.class, () -> Graphs.astar(graph, 0, 8));
        assertThrows(IllegalArgumentException.class, () -> Graphs.astar(graph, 8, 8));
        assertThrows(IllegalArgumentException.class, () -> Graphs.astar(graph, 8, 0));
    }

}
