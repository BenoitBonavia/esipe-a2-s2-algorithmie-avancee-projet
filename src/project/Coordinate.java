package project;

public class Coordinate {
    private int x;
    private int y;

    public Coordinate(int x, int y) {
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

    public double distance(Coordinate coordinate) {
        return distance(this, coordinate);
    }

    public static double distance(Coordinate coordP1, Coordinate coordP2) {
        long diffX2 = (coordP2.getX() - coordP1.getX()) * (coordP2.getX() - coordP1.getX());
        long diffY2 = (coordP2.getY() - coordP1.getY()) * (coordP2.getY() - coordP1.getY());
        return Math.sqrt(diffX2 + diffY2);
    }

    private static double degreesToRadian(long value) {
        return value * Math.PI / 180;
    }

    public static double geocraphicalDistance(Coordinate coordP1, Coordinate coordP2) {
        int earthRadiusKm = 6371;

        double dLat = degreesToRadian(coordP2.x - coordP1.x);
        double dLon = degreesToRadian(coordP2.y - coordP1.y);

        double lat1 = degreesToRadian(coordP1.x);
        double lat2 = degreesToRadian(coordP2.y);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.sin(dLon / 2) * Math.sin(dLon / 2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return earthRadiusKm * c;
    }
}
