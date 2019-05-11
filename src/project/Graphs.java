package project;

import java.util.*;
import java.util.logging.Logger;

public class Graphs {
    private static Logger logger = Logger.getLogger(Graphs.class.getName());

    public static ShortestPathFromOneVertex astar(Graph graph, int s, int t, int[] h) {
        if (s < 0 || s >= graph.numberOfVertices() || t < 0 || t >= graph.numberOfVertices()) {
            throw new IllegalArgumentException("The source vertice or target vertice doesn't exist");
        }
        int[] f = new int[graph.numberOfVertices()];
        int[] g = new int[graph.numberOfVertices()];
        int[] pi = new int[graph.numberOfVertices()];
        for (int i = 0; i < f.length; i++) {
            f[i] = Integer.MAX_VALUE;
            g[i] = Integer.MAX_VALUE;
        }
        f[s] = 0;
        g[s] = 0;
        ArrayList<Integer> border = new ArrayList<>();
        ArrayList<Integer> computed = new ArrayList<>();
        border.add(s);
        computed.add(s);
        while (!border.isEmpty()) { // tant qu'il y a des points a
            int min = Integer.MAX_VALUE;
            int x = -1;
            //Extrait le min de border
            for (int i : border) {
                if (f[i] < min) {
                    x = i;
                    min = f[i];
                }
            }
            if (x == t) {
                return new ShortestPathFromOneVertex(s, g, pi);
            }
            if (x != -1) {
                //Enleve x de border
                border.remove(border.indexOf(x));
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
                                border.add(y);
                            }
                        }
                    } else {
                        int value = edge.getValue();
                        g[y] = g[fx] + value;
                        f[y] = g[y] + h[y];
                        pi[y] = fx;
                        border.add(y);
                        computed.add(y);
                    }
                });
            }
        }
        return null;
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
                    if (d[y] + edge.getValue() < d[edge.getEnd()]) {
                        d[edge.getEnd()] = d[y] + edge.getValue();
                        pi[edge.getEnd()] = y;
                        if (!vertices.contains(edge.getEnd()))
                            vertices.add(edge.getEnd());
                    }
                });
            }
        }
        return new ShortestPathFromOneVertex(source, d, pi);
    }

}
