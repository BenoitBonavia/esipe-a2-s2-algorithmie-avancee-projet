package project;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class NodeMap {

    Map<Integer, Coordinate> nodes = new HashMap<>();

    public void addNode(int id, Coordinate coordinate) {
        nodes.put(id, coordinate);
    }

    public int distance(int id1, int id2) {
        Coordinate coordP1 = nodes.get(id1);
        Coordinate coordP2 = nodes.get(id2);
        if (coordP1 == null) {
            throw new IllegalArgumentException("Id doesn't exist : " + id1);
        }
        if (coordP2 == null) {
            throw new IllegalArgumentException("Id doesn't exist : " + id2);
        }
        if (id1 == id2) {
            return 0;
        }
        return (int) Math.ceil(coordP1.distance(coordP2));
    }

    public int geographicalDistance(int id1, int id2) {
        Coordinate coordP1 = nodes.get(id1);
        Coordinate coordP2 = nodes.get(id2);
        if (coordP1 == null || coordP2 == null) {
            throw new IllegalArgumentException("Id doesn't exist");
        }
        return (int) Math.ceil(Coordinate.geocraphicalDistance(coordP1, coordP2));
    }

    public Optional<Coordinate> coordinateFromId(int id) {
        return Optional.ofNullable(nodes.get(id));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int key : nodes.keySet()) {
            sb.append(key);
            sb.append(" ");
            sb.append(nodes.get(key).toString());
            sb.append("\n");
        }
        return sb.toString();
    }
}