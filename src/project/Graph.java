package project;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.IntFunction;

public interface Graph {
    /**
     * Le nombre d'arêtes du graphe.
     *
     * @return le nombre d'arêtes du graphe
     */
    int numberOfEdges();

    /**
     * Le nombre de sommets du graphe.
     *
     * @return le nombre de sommets du graphe
     */
    int numberOfVertices();

    /**
     * Permet d'ajouter une arête orientée et pondérée au graphe.
     *
     * @param i     la première extrémité de l'arête
     * @param j     la seconde extrémité de l'arête
     * @param value le poids de l'arête
     * @throws IndexOutOfBoundsException si i ou j n'est pas un sommet du graphe
     * @throws IllegalArgumentException  si value vaut 0 ou si il existe déjà une arête entre i et j
     */
    void addEdge(int i, int j, int value);

    /**
     * Teste l'existence d'une arête donnée
     *
     * @param i la première extrémité de l'arête
     * @param j la seconde extrémité de l'arête
     * @return true s'il existe une arête entre i et j; false sinon
     * @throws IndexOutOfBoundsException si i ou j n'est pas un sommet du graphe
     */
    boolean isEdge(int i, int j);

    /**
     * Renvoie le poids d'une arête donnée.
     *
     * @param i la première extrémité de l'arête
     * @param j la seconde extrémité de l'arête
     * @return le poids de l'arête entre i et j
     * @throws IndexOutOfBoundsException si i ou j n'est pas un sommet du graphe
     */
    int getWeight(int i, int j);

    /**
     * Un itérateur sur tous les voisins d'un sommet donné.
     *
     * @param i le sommet à partir duquel partent les arêtes fournies par l'itérateur
     * @return un itérateur sur tous les voisins du sommet i
     * @throws IndexOutOfBoundsException si i n'est pas un sommet du graphe
     */
    Iterator<Edge> edgeIterator(int i);

    /**
     * Effectue une action sur tous les voisins (les arêtes) d'un sommet donné.
     *
     * @param i        le sommet à partir duquel partent les arêtes traitées
     * @param consumer l'acction effectuée sur toutes les arêtes voisines de i
     * @throws IndexOutOfBoundsException si i n'est pas un sommet du graphe
     */
    void forEachEdge(int i, Consumer<Edge> consumer);

    /**
     * Fournit une réprésentation du graphe au format .dot
     *
     * @return une réprésentation du graphe au format .dot
     */
    default String toGraphviz() {
        StringBuilder stb = new StringBuilder("digraph G {\n");
        for (int i = 0; i < numberOfVertices(); i++) {
            stb.append("	").append(i).append(";\n");
            int finalI = i;
            forEachEdge(i, edge -> stb.append("	").append(finalI).append(" -> ").append(edge.getEnd()).append(" [ label=\"").append(edge.getValue()).append("\" ] ;\n"));
        }
        stb.append("}");
        return stb.toString();
    }

    /**
     * Création d'un graphe aléatoire avec un nombre de sommets et d'arêtes fixé
     *
     * @param n       nombre de sommets
     * @param nbEdges nombre d'arêtes
     * @param wmax    poids maximal (en valeur absolue) sur les arêtes
     * @param factory une méthode qui étant donné un nombre de sommets n, fabrique et renvoie yun graphe vide à n sommets
     * @return un graphe aléatoire construit à l'aide de factory yant exactement n sommets et nbEdges arêtes
     * @throws IllegalArgumentException si le nombre d'arêtes est trop élevé par rapport au nombre de sommets
     */
    public static Graph makeRandomGraph(int n, int nbEdges, int wmax, IntFunction<Graph> factory) {
        Graph g = factory.apply(n);
        if (nbEdges > n * n) {
            throw new IllegalArgumentException("Trop d'arêtes pour " + n + " sommets!");
        }
        if (wmax == 0) {
            throw new IllegalArgumentException("Le poids max doit être non nul");
        }
        Random r = new Random();
        int v1, v2;
        for (int k = 0; k < nbEdges; k++) {
            do {
                v1 = r.nextInt(n);
                v2 = r.nextInt(n);

            } while (g.isEdge(v1, v2));
            if (r.nextBoolean()) {
                g.addEdge(v1, v2, 1 + r.nextInt(wmax));
            } else {
                g.addEdge(v1, v2, -(1 + r.nextInt(wmax)));
            }
        }
        return g;
    }

    public Graph newGraphOfSameSize();
}