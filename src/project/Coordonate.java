package project;

public class Coordonate {
    private int x;
    private int y;

    public Coordonate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String toString() {
        return x + " " + y;
    }
}
