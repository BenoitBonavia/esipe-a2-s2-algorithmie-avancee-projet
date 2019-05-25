package project;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Graph graph = Parser.parseToGraph(args[0]);

        NodeMap nodeMap = Parser.parseToCities(args[0]);
        System.out.println("End of parse");
        //System.out.println(nodeMap);
        Optional<ShortestPathFromOneVertex> astar = Graphs.astar(graph, 1, 46104, Graphs.getH(graph.numberOfVertices(), 1, nodeMap));
        astar.ifPresent(shortestPathFromOneVertex -> shortestPathFromOneVertex.printShortestPathTo(46104));
        System.out.println("==============");
        ShortestPathFromOneVertex dijkstra = Graphs.dijkstra(graph, 1);
        dijkstra.printShortestPathTo(46104);
    }
}
