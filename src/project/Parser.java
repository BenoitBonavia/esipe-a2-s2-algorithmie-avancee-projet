package project;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Parser {

    private static Logger logger = Logger.getLogger(Parser.class.getName());


    public static Graph parseToGraph(String filename) {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get("res/" + filename + ".gr"))) {
            String line;
            Graph graph = null;
            while ((line = reader.readLine()) != null) {
                String[] tab = line.split(" ");
                String type = tab[0];
                if (type.equals("c")) {
                    StringBuilder sb = new StringBuilder();
                    for (String s: tab) {
                        sb.append(s);
                    }
                    logger.info(sb.toString());
                }
                else if (type.equals("p")) {
                    String sp = tab[1];
                    if (sp.equals("sp")) {
                        int numberVertices = Integer.parseInt(tab[2]) + 1;
                        graph = new AdjGraph(numberVertices);
                    }
                }
                else if (type.equals("a")) {
                    if (graph != null) {
                        int i = Integer.parseInt(tab[1]);
                        int j = Integer.parseInt(tab[2]);
                        int value = Integer.parseInt(tab[3]);
                        try {
                            graph.addEdge(i, j, value);
                        } catch(IllegalArgumentException e) {
                            logger.info("There is a double in the edges file");
                        }
                    }
                }
            }
            return graph;
        } catch (IOException e) {
            throw new IllegalStateException("Impossible to readFile .gr");
        }
    }

    public static NodeMap parseToCities(String filename) {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get("res/" + filename + ".co"))) {
            String line;
            NodeMap nodeMap = new NodeMap();
            while ((line = reader.readLine()) != null) {
                String[] tab = line.split(" ");
                String type = tab[0];
                if (type.equals("c")) {
                    StringBuilder sb = new StringBuilder();
                    for (String s: tab) {
                        sb.append(s);
                    }
                    logger.info(sb.toString());
                }
                else if (type.equals("v")) {
                    nodeMap.addNode(Integer.parseInt(tab[1]), new Coordonate(Integer.parseInt(tab[2]), Integer.parseInt(tab[3])));
                }
            }
            return nodeMap;
        } catch (IOException e) {
            throw new IllegalStateException("Impossible to readFile .co");
        }
    }
}
