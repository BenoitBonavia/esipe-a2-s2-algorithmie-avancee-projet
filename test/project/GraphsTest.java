package project;

import org.junit.jupiter.api.Test;
import project.*;

import java.util.Iterator;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class GraphsTest {
    private static Graph graph;
    private static Graph loadedGraph;
    private static int[] gResultGraph = {0, 5, 8, 7, 6};
    private static int[] piResultGraph = {0, 0, 3, 1, 0};

    static {
        graph = new AdjGraph(5);
        graph.addEdge(0, 1, 5);
        graph.addEdge(1, 2, 6);
        graph.addEdge(1, 3, 2);
        graph.addEdge(3, 2, 1);
        graph.addEdge(0, 4, 6);
        loadedGraph = Parser.parseToGraph("test");
    }

    private int[] createH(Graph graph) {
        int[] h = new int[graph.numberOfVertices()];
        for (int i = 0; i < h.length; i++) {
            h[i] = 0;
        }
        return h;
    }

    @Test
    void astarOnSimpleGraph() {
        assertEquals(new ShortestPathFromOneVertex(0, gResultGraph, piResultGraph), Graphs.astar(graph, 0, 2, createH(graph)));
    }

    @Test
    void astarWithUnreachableTarget() {
        assertNull(Graphs.astar(graph, 2, 0, createH(graph)));
    }

    @Test
    void astarWithNotExistingNode() {
        Graph graph = new AdjGraph(5);
        assertThrows(IllegalArgumentException.class, () -> Graphs.astar(graph, 0, 8, createH(graph)));
        assertThrows(IllegalArgumentException.class, () -> Graphs.astar(graph, 8, 8, createH(graph)));
        assertThrows(IllegalArgumentException.class, () -> Graphs.astar(graph, 8, 0, createH(graph)));
    }

    @Test
    void parser() {
        Graph graph = Parser.parseToGraph("test");
        assertEquals(graph.getWeight(1, 4), 87);
        assertEquals(graph.getWeight(7, 4), 101);
        NodeMap nodeMap = Parser.parseToCities("test");
        Optional<Coordinate> coordinate = nodeMap.coordinateFromId(1);
        coordinate.ifPresent(c -> {
            assertEquals(86, c.getX());
            assertEquals(40, c.getY());
        });
        assertEquals(8, graph.numberOfVertices());
    }

    @Test
    void astarOnGraphFromFile() {
        System.out.println(loadedGraph);
        NodeMap nodeMap = Parser.parseToCities("test");
//        assertEquals(234, (int) Math.ceil(Graphs.astar(loadedGraph, 1, 7, createH(loadedGraph)) * 1.6));
//        assertEquals(234, (int) Math.ceil(Graphs.astar(loadedGraph, 1, 7, Graphs.getH(loadedGraph.numberOfVertices(), 1, nodeMap)) * 1.6));
    }

    @Test
    void graphviz() {
        Graph graph = Parser.parseToGraph("test");
        System.out.println(graph.toGraphviz());
    }

    @Test
    void distanceBetween2Points() {
        Coordinate c1 = new Coordinate(15, 16);
        Coordinate c2 = new Coordinate(53, 140);
        assertEquals(130, Math.ceil(Coordinate.distance(c1, c2)));
        Coordinate c3 = new Coordinate(-15, -16);
        Coordinate c4 = new Coordinate(53, -140);
        assertEquals(142, Math.ceil(Coordinate.distance(c3, c4)));
    }

    @Test
    void dijkstra() {
        ShortestPathFromOneVertex dijkstra = Graphs.dijkstra(loadedGraph, 0);
        dijkstra.printShortestPaths();
    }

    @Test
    void simpleDijkstra() {
        //TODO : Complete the test and compare with astar
        Graph graph = new AdjGraph(5);
        graph.addEdge(0, 1, 5);
        graph.addEdge(1, 2, 6);
        graph.addEdge(1, 3, 2);
        graph.addEdge(3, 2, 1);
        graph.addEdge(0, 4, 6);
        ShortestPathFromOneVertex dijkstra = Graphs.dijkstra(graph, 0);
        Optional<ShortestPathFromOneVertex> astar = Graphs.astar(graph, 0, 4, createH(graph));
        dijkstra.printShortestPathTo(3);
        astar.get().printShortestPathTo(3);
        /*
        assertEquals(dijkstra, astar);*/
    }

    @Test
    void astarSameAsDijkstra() {

    }
    @Test
    void sameResultWithDijkstraAndAstar() {
        //TODO : TEST
    }

    @Test
    public void randomGraph() {
        Graph graph = Graph.makeRandomGraph(5, 4, 9, n -> new AdjGraph(5));
        assertEquals(graph.numberOfEdges(), 4);
        assertEquals(graph.numberOfVertices(), 5);
        for (int i = 0; i < 5; i++) {
            Iterator<Edge> edgeIterator = graph.edgeIterator(i);
            while (edgeIterator.hasNext()) {
                Edge next = edgeIterator.next();
                assertTrue(next.getValue() < 9);
            }
        }
        System.out.println(graph);
    }

}
