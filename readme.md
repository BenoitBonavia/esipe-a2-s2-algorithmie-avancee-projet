#Projet Algorithmie Avancée - Benoit Bonavia - Thierry Kuy


###Méthode employé pour le projet

1. Nous avons implémenté l'algorithme de Djikstra, et nous avons fait en sorte d'obtenir un résultat au même format que celui attendu pour astar afin de pouvoir comparer les dits résultas
2. Test avec plusieurs algorithmes simple
3. Test de plusieurs fichiers
4. Test avec de gros fichiers
5. Test avec plusieurs sources
6. Test avec plusieurs destinations
7. Pour tout les cas ci dessus nous avons comparé les résultats obtenu avec l'algorithme de Djikstra et celui d'Astar

###Arguments du programme

| Argument | Description |
|---|---|
|Nom du fichier|Le nom du fichier sans l'extension (.co & .gr)|
|Source|Donné sous la forme d'un int, la source est le sommet de départ de l'algorithme|
|Destination|Donné sous la forme d'un int, la destination est le sommet vers lequel on veut aller|

Le programme lancera ensuite les deux algorithmes (astar et djkstra).

###Difficulté Rencontrées

Nous avons tout d'abord eut quelques erreurs sur la base de ce qu'on a fait en TP. Le temps d'éxécution sur les gros graphs nous a également fait perdre beaucoup de temps.<br/>
Trouver le bon cas limite a également été un problème.

Nous avions utilisé une file de priorité de Java mais nous nous sommes rendu compte au dernier moment qu'elle ne permettait pas
d'avoir le bon nombre d'itérations.

###Etat actuel du projet

Tout marche mais l'algorithme astar ne fonctionne pas avec une file de priorité.

###Amélioration possibles

La résolution du problème de nombre d'itération sur l'algorithme d'astar. <br/>
Une optimisation de l'algorithme pour de meilleurs performances.<br/>

Utiliser une file de priorité de java nous permettrait d'améliorer les performances car la récupération du minimum serait meilleure.

Nous pourrions également utiliser une file de priorité personnalisée à la place de celle de java, cela nous permettrait de directement update au lieu d'avoir à remove puis add.