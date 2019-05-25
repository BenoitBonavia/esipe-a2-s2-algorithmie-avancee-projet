package project;

import java.util.Comparator;
import java.util.Objects;

public class Node {
    private int source;
    private int distance;

    public int getSource() {
        return source;
    }

    public int getDistance() {
        return distance;
    }

    public Node(int source, int distance) {
        this.source = source;
        this.distance = distance;
    }
    public static class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            if(o1.distance<o2.distance) {
                return -1;
            } else if(o1.distance>o2.distance) {
                return 1;
            }
            return 0;
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "source=" + source +
                ", distance=" + distance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return source == node.source &&
                distance == node.distance;
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, distance);
    }
}
