package project;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Graph graph = Parser.parseToGraph(args[0]);
        NodeMap nodeMap = Parser.parseToCities(args[0]);
        System.out.println("End of parse");
        //System.out.println(nodeMap);
        int source = Integer.parseInt(args[1]);
        int dest = Integer.parseInt(args[2]);
        Optional<ShortestPathFromOneVertex> astar = Graphs.astar(graph, source, dest, Graphs.getH(graph.numberOfVertices(), source, nodeMap));
        astar.ifPresent(shortestPathFromOneVertex -> shortestPathFromOneVertex.printShortestPathTo(46104));
        System.out.println("==============");
        ShortestPathFromOneVertex dijkstra = Graphs.dijkstra(graph, source);
        dijkstra.printShortestPathTo(dest);
    }
}
