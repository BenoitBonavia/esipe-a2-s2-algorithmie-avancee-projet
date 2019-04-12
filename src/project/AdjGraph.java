package project;

import java.util.*;
import java.util.function.Consumer;

public class AdjGraph implements Graph {
    private final ArrayList<LinkedList<Edge>> adj;
    private final int n;
    private int numberOfEdges;

    public AdjGraph(int n) {
        this.n = n;
        this.numberOfEdges = 0;
        this.adj = new ArrayList<>();
        for(int i = 0; i<n; i++) {
            this.adj.add(new LinkedList<>());
        }
    }

    @Override
    public int numberOfEdges() {
        return numberOfEdges;
    }

    @Override
    public int numberOfVertices() {
        return n;
    }

    @Override
    public void addEdge(int i, int j, int value) {
        Optional<Edge> optEdge = adj.get(i).stream().filter(edge -> edge.getStart() == i && edge.getEnd() == j).findFirst();
        if(optEdge.isPresent()) {
            throw new IllegalArgumentException(i + " " + j + " " + value);
        } else {
            adj.get(i).add(new Edge(i, j, value));
        }
        numberOfEdges++;
    }

    @Override
    public boolean isEdge(int i, int j) {
        return adj.get(i).stream().anyMatch(edge -> edge.getStart() == i && edge.getEnd() == j);
    }

    @Override
    public int getWeight(int i, int j) {
        Optional<Edge> opt = adj.get(i).stream().filter(edge -> edge.getStart() == i && edge.getEnd() == j).findFirst();
        if(opt.isEmpty()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return opt.get().getValue();

    }

    @Override
    public Iterator<Edge> edgeIterator(int i) {
        return new ArrayList<>(adj.get(i)).iterator();
    }

    @Override
    public void forEachEdge(int i, Consumer<Edge> consumer) {
        adj.get(i).forEach(consumer);
    }

    @Override
    public Graph newGraphOfSameSize() {
        return new AdjGraph(n);
    }
}
