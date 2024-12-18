# Rapport
## Etape 1 Site Web statique

Création d'un dossier static-web-server contant:
- Le site static, modèle tiré de https://www.free-css.com/free-css-templates/page296/finexo.
- Un Dockerfile.
- Un fichier de configuration nginx.

Pour lancer le server dans docker il faut utiliser un terminal dans le dossier pour le serveur statique.
- Construire l'image Docker avec : "docker build -t static-nginx-server ."
- Lancer le conteneur sur le port 80 avec : docker run -d -p 8080:80 --name static-site static-nginx-server

Accès au serveur Web ce fait sur http://localhost:8080.

## Etape 2 Docker Compose

Ajout de Docker Compose dans le dossier *static-web-server" avec la configuration.

Construire l'image:
"docker compose build"

Démarrer l'infrastructure :
"docker compose up -d" //Cela lance le conteneur automatiquement et -d permet de lancer le conteneur en arrière-plan, sans afficher les logs directement dans le terminal.

Arrêter l'infrastructure :
"docker compose down"

## Etape 3 Serveur API HTTP

Objectif :
L’objectif de cette étape est de développer une API HTTP en utilisant Javalin afin de gérer une liste de tâches. L'API doit supporter les opérations CRUD :

- Création (POST),
- Récupération (GET),
- Mise à jour (PUT),
- Suppression (DELETE).

Structure du projet :

Main.java : Point d’entrée principal où le serveur Javalin est configuré pour exposer les routes API.
TaskController.java : Gère les opérations CRUD.
Task.java : Modèle de données pour les tâches.
pom.xml : Configuration Maven


Création de l'image dockerfile pour l'api
![Dockerfile pour API](images/dockerfile-step3.png)

Mis à jour du docker compose en ajoutant l'api au services
![Docker Compose API](images/compose-step3.png)

Insomnia est l'outil utilisé pour tester la bonne fonctionnalité de l'api et du support des opérations CRUD. 

5 scénarios de test ont été imaginés pour couvrir les fonctionnalités suivantes :

1. POST /tasks : Création d'une nouvelle tâche.\
   Statut attendu : 201 Created


2. GET /tasks : Récupération de la liste complète des tâches.\
   Statut attendu : 200 OK
3. GET /tasks/{id} : Récupération d'une tâche spécifique par son ID.\ 
   Statut attendu : 200 OK (tâche trouvée) ou 404 Not Found (si l'ID est inexistant).
4. PUT /tasks/{id} : Mise à jour d'une tâche existante.\
   Statut attendu : 200 OK
5. DELETE /tasks/{id} : Suppression d'une tâche existante.\
   Statut attendu : 204 No Content

Résultats des tests :
1. **POST /tasks** :  
   ![POST API](images/CREATE-API-step3.png)
2. **GET /tasks** :  
   ![GET API](images/GET-API.png)
3. **GET /tasks/{id}** :  
   ![GET API par ID](images/GETId-API.png)
4. **PUT /tasks/{id}** :  
   ![PUT API](images/PUT-API.png)
5. **DELETE /tasks/{id}** :  
   ![DELETE API](images/DELETE-API.png)

La tâche l'id 2 a été supprimé de la base de données

## Etape 4 Proxy inverse avec Traefik

Objectif:
L'objectif de cette étape est de déployer un proxy inverse configuré avec Traefik sur les différents services de l'application en utilisant Docker Compose.

Docker Compose:
Modification du docker compose pour integrer Traefik.
Définition trois services principaux :

1. Serveur statique (static-web)
Accès :
Traefik redirige les requêtes HTTP vers ce service via la règle définit dans le labels.
2. Serveur API (todolist-api)
Accès :
Traefik redirige les requêtes HTTP vers ce service via la règle définit dans le labels.
3. Traefik (Proxy inverse)
Traefik est utilisé pour rediriger les requêtes HTTP vers les bons services en fonction de l'URL demandée.

Ports configurés :
- 80:80 : Trafic HTTP redirigé web-static.
- 7000:7000 : Trafic HTTP redirigé todolist-api
- 8080:8080 : Accès au tableau de bord Traefik.


### Docker Compose pour Traefik
![Docker Compose Traefik](images/compose-step4.png)

Vérification:
La vérification du bon fonctionnement de l'API a été effectuée en utilisant Insomnia. En changeant simplement le nom de l'URL pour refléter la configuration Traefik (http://todolist-api.localhost)


Accès au site :
- Static : http://static-web.localhost/
- Api tasks : http://todolist-api.localhost/tasks
- Traefik dashboard : http://localhost:8080/