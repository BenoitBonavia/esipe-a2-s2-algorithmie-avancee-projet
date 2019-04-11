package project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class MatGraph implements Graph {

    private final int[][] mat;
    private final int n;

    public MatGraph(int n) {
        this.n = n;
        this.mat = new int[n][n];
    }

    @Override
    public int numberOfEdges() {
        int edges = 0;
        for (int[] line: mat) {
            for (int elem: line) {
                edges += elem;
            }
        }
        return edges;
    }

    @Override
    public int numberOfVertices() {
        return n;
    }

    @Override
    public void addEdge(int i, int j, int value) {
        if (i > n-1 || j > n-1 || i < 0 || j < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (value == 0 || mat[i][j] != 0) {
            throw new IllegalArgumentException();
        }
        this.mat[i][j] = value;
    }

    @Override
    public boolean isEdge(int i, int j) {
        if (i > n-1 || j < n-1 || i < 0 || j < 0) {
            throw new IndexOutOfBoundsException();
        }
        return mat[i][j] != 0;
    }

    @Override
    public int getWeight(int i, int j) {
        if (i > n-1 || j > n-1 || i < 0 || j < 0) {
            throw new IndexOutOfBoundsException();
        }
        return mat[i][j];
    }

    @Override
    public Iterator<Edge> edgeIterator(int i) {
        if (i > n-1 || i < 0) {
            throw new IndexOutOfBoundsException();
        }
        List<Edge> list = new ArrayList<>();
        for (int j = 0; j < n; j++) {
            if (mat[i][j] == 0) {
                continue;
            }
            list.add(new Edge(i, j, mat[i][j]));
        }
        return list.stream().iterator();
    }

    @Override
    public void forEachEdge(int i, Consumer<Edge> consumer) {
        if (i < 0 || i > n-1) {
            throw new IllegalArgumentException();
        }
        for (int j = 0; j < n; j++) {
            if (mat[i][j] == 0) {
                continue;
            }
            consumer.accept(new Edge(i, j, mat[i][j]));
        }
    }

    @Override
    public String toGraphviz() {
        return null;
    }
}
