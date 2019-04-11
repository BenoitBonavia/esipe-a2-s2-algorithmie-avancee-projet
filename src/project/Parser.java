package project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Parser {
    public static Graph parseToGraph(String filename) {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get("res/" + filename + ".gr"))) {
            String line;
            Graph graph;
            while ((line = reader.readLine()) != null) {
                String[] tab = line.split(" ");
                String type = tab[0];
                if (type.equals("p")) {
                    String sp = tab[1];
                    if (sp.equals("sp")) {
                        int numberVertices = Integer.parseInt(tab[2]);
                    }
                }
            }
        } catch (IOException e) {
            throw new IllegalStateException("Impossible to readFile");
        }
        return null;
    }
}
