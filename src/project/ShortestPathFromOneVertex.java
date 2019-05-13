package project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

public class ShortestPathFromOneVertex {
    private final int source;
    private final int[] d;
    private final int[] pi;

    ShortestPathFromOneVertex(int source, int[] d, int[] pi) {
        this.source = source;
        this.d = d;
        this.pi = pi;
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

    public void printShortestPaths() {
        for (int i = 0; i < d.length; i++) {
            if (i == source) {
                continue;
            }
            printShortestPathTo(i);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShortestPathFromOneVertex that = (ShortestPathFromOneVertex) o;
        return source == that.source &&
                Arrays.equals(d, that.d) &&
                Arrays.equals(pi, that.pi);
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
