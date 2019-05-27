#Projet Algorithmie Avancée - Benoit Bonavia - Thierry Kuy


###Méthode employé pour le projet

1. Nous avons implémenté l'algorithme de Djikstra, et nous avons fait en sorte d'obtenir un résultat au même format que celui attendu pour astar afin de pouvoir comparer les dits résultas
2. Test avec plusieurs algorithmes simple
3. Test de plusieurs fichiers
4. Test avec de gros fichiers
5. Test avec plusieurs sources
6. Test avec plusieurs destinations
7. Pour tout les cas ci dessus nous avons comparé les résultats obtenu avec l'algorithme de Djikstra et celui D'Astar

###Arguments du programme

| Argument | Description |
|---|---|
|Nom du fichier|Le nom du fichier sans l'extension (.co & .gr)|
|Source|Donné sous la forme d'un int, la source est le sommet de départ de l'algorithme|
|Destination|Donné sous la forme d'un int, la destination est le sommet vers lequel on veut aller|

Le programme lancera ensuite les deux algorithmes (astar et djkstra).

###Difficulté Rencontrées

Nous avons tout d'abord eut quelques erreurs sur la base de ce qu'on a fait en TP. Le temps d'éxécution sur les gros graphs nous a également fait perdre beaucoup de temps.<br/>
Trouver le bon cas limite a également été un problème, et nous avons également eut un problème pour le nombre d'étapes de astar.

###Etat actuel du projet

Tout marche, mais l'algorithme astar semble encore effectuer un nombre d'itération trop élevé. Peut-être dut à une récupération des approximation éronées.

###Amélioration possibles

La résolution du problème de nombre d'itération sur l'algorithme d'astar. <br/>
Une optimisation de l'algorithme pour de meilleurs performances.<br/>
Nous pourrions également utiliser une file de priorité personnalisée, cela nous permettrait de directement update au d'avoir à remove puis add.