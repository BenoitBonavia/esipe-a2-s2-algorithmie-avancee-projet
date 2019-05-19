package project;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Graph graph = Parser.parseToGraph(args[0]);

        NodeMap nodeMap = Parser.parseToCities(args[0]);
        System.out.println("End of parse");
        //System.out.println(nodeMap);
        Optional<ShortestPathFromOneVertex> astar = Graphs.astar(graph, 1, 7, Graphs.getH(graph.numberOfVertices(), 1, nodeMap));
        astar.ifPresent(shortestPathFromOneVertex -> shortestPathFromOneVertex.printShortestPathTo(7));
        System.out.println("==============");
        ShortestPathFromOneVertex dijkstra = Graphs.dijkstra(graph, 1);
        dijkstra.printShortestPathTo(7);

        /*Graph graph1 = Graph.makeRandomGraph(20, 18, 30, n -> new AdjGraph(20));
        Optional<ShortestPathFromOneVertex> astar2 = Graphs.astar(graph1, 1, 18, createH(graph1));
        ShortestPathFromOneVertex dijkstra = Graphs.dijkstra(graph1, 1);
        dijkstra.printShortestPathTo(3);
        astar2.ifPresent(shortestPathFromOneVertex -> shortestPathFromOneVertex.printShortestPathTo(3));*/
    }
    private static int[] createH(Graph graph) {
        int[] h = new int[graph.numberOfVertices()];
        for (int i = 0; i < h.length; i++) {
            h[i] = 0;
        }
        return h;
    }
}
