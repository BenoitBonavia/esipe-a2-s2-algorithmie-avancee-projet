package project;

import java.util.HashMap;
import java.util.Map;

public class NodeMap {

    Map<Integer, Coordonate> nodes = new HashMap<>();

    public void addNode(int id, Coordonate coordonate) {
        nodes.put(id, coordonate);
    }

    public double distance(int point1, int point2) {
        Coordonate coordP1 = nodes.get(point1);
        Coordonate coordP2 = nodes.get(point2);
        return Math.sqrt((coordP2.getX() - coordP1.getX())*(coordP2.getX() - coordP1.getX()) + (coordP2.getY() - coordP1.getY())*(coordP2.getY() - coordP1.getY()));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int key: nodes.keySet()) {
            sb.append(key);
            sb.append(" ");
            sb.append(nodes.get(key).toString());
            sb.append("\n");
        }
        return sb.toString();
    }
}