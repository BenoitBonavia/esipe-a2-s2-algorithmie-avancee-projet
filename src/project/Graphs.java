package project;

import java.util.*;
import java.util.concurrent.atomic.LongAdder;
import java.util.logging.Logger;

public class Graphs {
    private static Logger logger = Logger.getLogger(Graphs.class.getName());

    public static Optional<ShortestPathFromOneVertex> astar(Graph graph, int s, int t, int[] h) {
        LongAdder longAdder = new LongAdder();
        if (s < 0 || s >= graph.numberOfVertices() || t < 0 || t >= graph.numberOfVertices()) {
            throw new IllegalArgumentException("The source vertice or target vertice doesn't exist");
        }
        PriorityQueue<Node> border = new PriorityQueue<>(new Node.NodeComparator());
        int[] f = new int[graph.numberOfVertices()];
        int[] g = new int[graph.numberOfVertices()];
        int[] pi = new int[graph.numberOfVertices()];
        for (int i = 0; i < f.length; i++) {
            f[i] = Integer.MAX_VALUE;
            g[i] = Integer.MAX_VALUE;
            pi[i] = -1;
        }
        f[s] = 0;
        g[s] = 0;
        h[s] = 0;
        pi[s] = s;
        ArrayList<Integer> computed = new ArrayList<>();
        border.add(new Node(s, Integer.MAX_VALUE));
        computed.add(s);
        while (!border.isEmpty()) { // tant qu'il y a des points a retirer
            longAdder.increment();
            Node node = border.remove();
            System.out.println(node);
            int x = node.getSource();
            if (x == t) {
                return Optional.of(new ShortestPathFromOneVertex(s, g, pi, longAdder.intValue()));
            }
            if (x != -1) {
                //Enleve x de border
                final int fx = x;
                graph.forEachEdge(x, edge -> {
                    int y = edge.getEnd();
                    if (computed.contains(y)) {
                        int value = edge.getValue();
                        if (g[y] > g[fx] + value) {
                            g[y] = g[fx] + value;
                            f[y] = g[y] + h[y];
                            pi[y] = fx;
                            if (!border.contains(y)) {
                                border.add(new Node(y, f[y]));
                            }
                        }
                    } else {
                        int value = edge.getValue();
                        g[y] = g[fx] + value;
                        f[y] = g[y] + h[y];
                        pi[y] = fx;
                        border.add(new Node(y, f[y]));
                        computed.add(y);
                    }
                });
            }
        }
        return Optional.empty();
    }

    public static int[] getH(int numberOfVertices, int source, NodeMap nodeMap) {
        int[] h = new int[numberOfVertices];
        for (int i = 1; i < numberOfVertices; i++) {
            try {
                h[i] = (int) Math.ceil(nodeMap.distance(source, i) * 1.6);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        h[0]=-1;
        return h;
    }

    public static ShortestPathFromOneVertex dijkstra(Graph g, int source) {
        LongAdder longAdder = new LongAdder();
        ArrayList<Integer> vertices = new ArrayList<>();
        int[] d = new int[g.numberOfVertices()];
        int[] pi = new int[g.numberOfVertices()];
        for (int i = 0; i < g.numberOfVertices(); i++) {
            d[i] = Integer.MAX_VALUE;
            pi[i] = -1;
        }
        d[source] = 0;
        pi[source] = source;
        vertices.add(source);
        while (vertices.size() != 0) {
            int min = Integer.MAX_VALUE;
            int vertice = -1;
            for (int i : vertices) {
                if (d[i] < min) {
                    vertice = i;
                    min = d[i];
                }
            }
            if (vertice != -1) {
                int y = vertice;
                vertices.remove(vertices.indexOf(vertice)); // obligatoire d'utiliser indexOf, vertice étant un int, sans préciser indexOf, remove appelerait avec vertice comme une position
                g.forEachEdge(y, edge -> {
                    int end = edge.getEnd();
                    if (d[y] + edge.getValue() < d[end]) {
                        d[end] = d[y] + edge.getValue();
                        pi[end] = y;
                        if (!vertices.contains(end))
                            vertices.add(end);
                    }
                });
            }
            longAdder.increment();
        }
        return new ShortestPathFromOneVertex(source, d, pi, longAdder.intValue());
    }

}
