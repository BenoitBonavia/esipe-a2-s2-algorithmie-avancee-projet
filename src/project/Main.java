package project;

public class Main {
    public static void main(String[] args) {
        Graph graph = Parser.parseToGraph(args[0]);
//        System.out.println(graph.getWeight(381, 380));
        for (int i = 0; i < graph.numberOfVertices(); i++) {
            graph.forEachEdge(i, System.out::println);
        }
    }
}
