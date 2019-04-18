package project;

public class Node {
    private int id;
    private int x;
    private int y;

    public Node(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "\n" + id + " " + x + " " + y;
    }
}
