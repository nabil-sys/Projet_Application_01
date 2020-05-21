# Projet_Application_01
## Présentation :
Projet sur une application mobile sous android, permettant d'afficher une liste d'élements et son détail lorsque l'on clique sur l'élement.
Cette application affiche les images de differents exercices selon l'identifiant donnée. Il utilise L'api du site Workout Manager : "https://wger.de".
Ainsi cette application permet d'aider une personne à exercer le mouvement d'un exercice.

Note : dans ce projet j'ai d'abord utiliser l'API d'un pokedex (PokeApi) afin de m'initier sur la pratique du logiciel, ensuite j'ai utilisé une autre API sur le workout(ExoApi) pour continuer mon application.

## Prérequis :
- Installation d'Android Studio
- Utilisation d'une API :
"https://wger.de/api/v2/exerciseimage/"

## Consigne respectées :
- Appel WebService à une API REST
- Affichage d'une liste dans un recyclerView
- Affichage du détail d'un item de la liste (string)
- Affichage du détail d'un item de la liste (image à partir d'une URL)
- 2 écrans : un pour la liste d'item, un autre pour l'affichage du détail
- Stockage de données en cache
- Archtecture en MVC, respectant principes SOLID. Avec Singleton et Design Patterns.
- Utilisation de Gitflow (notamment lors du changement d'API)

## Fonctionnalités :
### Premier écran
- Liste de RecyclerView.

![ ](img_readme/1er%20ecran.PNG)


### deuxième écran
- Détail de l'élement (image + url)

![ ](img_readme/2e%20ecran.PNG)    ![ ](img_readme/2e%20ecran%20bis.PNG)  
