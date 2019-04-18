package project;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.LongAdder;

public class Graphs {
    public static List<Integer> DFS(Graph g) {
        boolean[] visited = new boolean[g.numberOfVertices()];
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                PPrec(g, i, visited, list);
            }
        }
        return list;
    }

    public static void PPrec(Graph g, int sommet, boolean[] visited, List<Integer> list) {
        visited[sommet] = true;
        list.add(sommet);
        Iterator<Edge> edgeIterator = g.edgeIterator(sommet);
        while (edgeIterator.hasNext()) {
            Edge next = edgeIterator.next();
            int end = next.getEnd();
            if (!visited[end]) {
                PPrec(g, end, visited, list);
            }
        }
    }

    public static int astar(Graph graph, int s, int t) {
        int[] f = new int[graph.numberOfVertices()];
        int[] g = new int[graph.numberOfVertices()];
        int[] h = new int[graph.numberOfVertices()];
        for (int i = 0; i < f.length; i++) {
            f[i] = 0;
            g[i] = 0;
            h[i] = 0;
        }
        ArrayList<Integer> border = new ArrayList<>();
        ArrayList<Integer> computed = new ArrayList();
        border.add(s);
        computed.add(s);
        while (!border.isEmpty()) {
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
                return f[x];
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
                            if (!border.contains(y)) {
                                border.add(y);
                            }
                        }
                    } else {
                        int value = edge.getValue();
                        g[y] = g[fx] + value;
                        f[y] = g[y] + h[y];
                        border.add(y);
                        computed.add(y);
                    }
                });
            }
        }
        return -1;
    }

}
