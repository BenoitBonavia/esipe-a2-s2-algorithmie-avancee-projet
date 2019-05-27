package project;

import java.util.*;
import java.util.concurrent.atomic.LongAdder;
import java.util.logging.Logger;

public class Graphs {
    private static Logger logger = Logger.getLogger(Graphs.class.getName());

    public static Optional<ShortestPathFromOneVertex> astar(Graph graph, int s, int t, int[] h, NodeMap nodeMap) {
        LongAdder longAdder = new LongAdder();
        if (s < 0 || s >= graph.numberOfVertices() || t < 0 || t >= graph.numberOfVertices()) {
            throw new IllegalArgumentException("The source vertice or target vertice doesn't exist");
        }
        //PriorityQueue<Node> border = new PriorityQueue<>(new Node.NodeComparator());
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
        computed.add(s);
        PriorityQueue<Node> nodes = new PriorityQueue<>(new Node.NodeComparator());
        nodes.add(new Node(s, Integer.MAX_VALUE));
        while (!nodes.isEmpty()) { // tant qu'il y a des points a retirer
            longAdder.increment();
            int x = -1;
            //Extrait le min de border
            x=nodes.remove().getSource();
            if (x == t) {
                return Optional.of(new ShortestPathFromOneVertex(s, g, pi, longAdder.intValue()));
            }
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
                        //nodes.removeIf(node -> node.getSource() == y);
                        Node node = new Node(y, f[y]);
                        if(nodes.contains(node)) {
                            nodes.remove(node);
                        }
                        nodes.add(node);
                    }
                } else {
                    if(nodeMap!=null) {
                        h[y] = nodeMap.distance(y, t);
                    }
                    int value = edge.getValue();
                    g[y] = g[fx] + value;
                    f[y] = g[y] + h[y];
                    pi[y] = fx;
                    computed.add(y);
                    //nodes.removeIf(node -> node.getSource() == y);
                    nodes.add(new Node(y, f[y]));
                }
            });
        }

        return Optional.empty();
    }

    public static int[] getH(int numberOfVertices, int source, NodeMap nodeMap) {
        int[] h = new int[numberOfVertices];
        for (int i = 1; i < numberOfVertices; i++) {
            try {
                h[i - 1] = (int) Math.ceil(nodeMap.distance(source, i) * 1.6);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
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
