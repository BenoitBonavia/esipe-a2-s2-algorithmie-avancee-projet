package project;

import java.util.*;
import java.util.stream.Collectors;

public class ShortestPathFromOneVertex {
    private final int source;
    private final int[] d;
    private final int[] pi;
    private final int step;

    ShortestPathFromOneVertex(int source, int[] d, int[] pi, int step) {
        this.source = source;
        this.d = d;
        this.pi = pi;
        this.step = step;
    }

    public int distanceTo(int destination) {
        return d[destination];
    }

    public void printShortestPathTo(int destination) {
        ArrayList<Integer> ar = new ArrayList();
        printShortestPathToRec(destination, ar);
        ar.add(source);
        Collections.reverse(ar);
        System.out.println("Go from " + source + " to " + destination);
        System.out.println(ar.stream().map(Object::toString).collect(Collectors.joining("->")));
    }

    private void printShortestPathToRec(int dest, ArrayList<Integer> ar) {
        if (dest == -1) {
            return;
        }
        if (ar.isEmpty()) {
            ar.add(dest);
        }
        if (pi[dest] == source) {
            return;
        }
        ar.add(pi[dest]);
        printShortestPathToRec(pi[dest], ar);
    }

    public List<Integer> shortestPathTo(int destination) {
        ArrayList path = new ArrayList();
        shortestPathToRec(destination, path);
        return path;
    }
    public void shortestPathToRec(int destination, ArrayList<Integer> vertices) {
        if(destination==-1) {
            return;
        }
        if(vertices.isEmpty()) {
            vertices.add(destination);
        }
        if(pi[destination] == source) {
            return;
        }
        vertices.add(pi[destination]);
    }

    public void printShortestPaths() {
        for (int i = 0; i < d.length; i++) {
            if (i == source) {
                continue;
            }
            printShortestPathTo(i);
        }
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(source);
        result = 31 * result + Arrays.hashCode(d);
        result = 31 * result + Arrays.hashCode(pi);
        return result;
    }

    @Override
    public String toString() {
        return source + " " + Arrays.toString(d) + " " + Arrays.toString(pi);
    }
}
