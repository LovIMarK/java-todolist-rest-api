**Objectifs** 
L'objectif principal de ce laboratoire est d'apprendre à construire une infrastructure Web complète. Cela signifie que nous allons créer une infrastructure serveur qui sert un site Web statique et une API HTTP dynamique. Le diagramme ci-dessous montre l'architecture de l'infrastructure que nous allons construire.
En plus de l'exigence de base de service de contenu statique et dynamique, l'infrastructure aura les caractéristiques suivantes :
 
- **Scalabilité**  : le serveur statique et le serveur dynamique seront déployés en tant que cluster de plusieurs instances. Le proxy inverse sera configuré pour répartir la charge entre les instances.
 
- **Sécurité**  : la connexion entre le navigateur et le proxy inverse sera cryptée via HTTPS.
 
- **Gestion**  : une application Web sera déployée pour gérer l'infrastructure. Cette application permettra de démarrer/arrêter les instances des serveurs et de surveiller l'état de l'infrastructure.
**Instructions générales** 
Il s'agit d'un laboratoire **complexe**  et vous aurez besoin de beaucoup de temps pour le terminer.
Vous travaillerez en groupe de 2 étudiants et utiliserez un flux de travail Git pour collaborer.
Pour certaines étapes, vous devrez effectuer des recherches dans la documentation par vous-même (nous sommes là pour aider, mais nous ne vous fournirons pas des instructions étape par étape !) ou vous devrez faire preuve de créativité (ne vous attendez pas à des directives complètes).
Lisez attentivement tous les critères d'acceptation de chaque étape. Ils vous indiqueront ce que vous devez faire pour terminer l'étape.
Après le laboratoire, chaque groupe effectuera une démonstration courte de son infrastructure.
Vous devez rédiger un rapport avec une brève description de chaque étape. Faites-le directement dans le dépôt, dans un ou plusieurs fichiers markdown. Commencez dans le fichier README.md à la racine de votre répertoire.
Le rapport doit contenir la procédure que vous avez suivie pour prouver que votre configuration est correcte (ce que vous avez fait pour faire fonctionner l'étape et ce que vous feriez si vous deviez faire une démonstration).**Étape 0 : Dépôt GitHub** 
Créez un dépôt GitHub pour votre projet. Vous utiliserez ce dépôt pour collaborer avec votre coéquipier et pour soumettre votre travail.**Critères d'acceptation** 
- Vous avez créé un dépôt GitHub pour votre projet.

- Le dépôt contient un fichier Readme que vous utiliserez pour documenter votre projet.
**Étape 1 : Site Web statique** 
L'objectif de cette étape est de construire une image Docker qui contient un serveur HTTP statique Nginx. Le serveur servira un site Web statique. Le site Web statique sera une seule page avec un joli modèle. Vous pouvez utiliser un modèle gratuit, par exemple depuis Free-CSS ou Start Bootstrap.**Critères d'acceptation** 
- Vous avez créé un dossier séparé dans votre dépôt pour votre serveur Web statique.

- Vous avez un fichier Dockerfile basé sur l'image Nginx. Le Dockerfile copie le contenu du site statique dans l'image.

- Vous avez configuré le fichier nginx.conf pour servir le contenu statique sur un port (normalement 80).

- Vous êtes capable d'expliquer le contenu du fichier nginx.conf.

- Vous pouvez exécuter l'image et accéder au contenu statique depuis un navigateur.

- Vous avez documenté votre configuration dans votre rapport.
**Étape 2 : Docker Compose** 
L'objectif de cette étape est d'utiliser Docker Compose pour déployer une première version de l'infrastructure avec un seul service : le serveur Web statique.**Critères d'acceptation** 
- Vous avez ajouté un fichier de configuration Docker Compose à votre dépôt GitHub.

- Vous pouvez démarrer et arrêter une infrastructure avec un seul serveur Web statique en utilisant Docker Compose.

- Vous pouvez accéder au serveur Web sur votre machine locale sur le port respectif.
 
- Vous pouvez reconstruire l'image Docker avec `docker compose build`.

- Vous avez documenté votre configuration dans votre rapport.
**Étape 3 : Serveur API HTTP** 
L'objectif est de construire une API HTTP avec Javalin. Vous pouvez implémenter n'importe quelle API de votre choix (par exemple, gestion des citations du jour, gestion des éléments à faire, gestion des personnes). L'API doit supporter toutes les opérations CRUD : Créer, Lire, Mettre à jour, Supprimer.**Critères d'acceptation** 
- Votre API supporte toutes les opérations CRUD.

- Vous êtes capable d'expliquer votre implémentation et de nous expliquer le code.

- Vous pouvez démarrer et arrêter le serveur API avec Docker Compose.

- Vous pouvez accéder à la fois à l'API et au serveur statique depuis votre navigateur.

- Vous pouvez reconstruire l'image Docker avec Docker Compose.

- Vous pouvez effectuer une démonstration en utilisant un outil de test d'API pour montrer que toutes les opérations CRUD fonctionnent.

- Vous avez documenté votre implémentation dans votre rapport.
**Étape 4 : Proxy inverse avec Traefik** 
L'objectif est de placer un proxy inverse devant les serveurs Web statiques et dynamiques, de manière à ce que le proxy inverse reçoive toutes les connexions et les relaie aux serveurs respectifs.**Critères d'acceptation** 
- Vous pouvez effectuer une démonstration où vous démarrez une infrastructure avec 3 conteneurs : serveur statique, serveur dynamique et proxy inverse.

- Vous pouvez prouver que le routage est effectué correctement via le proxy inverse.

- Vous êtes capable d'expliquer comment vous avez implémenté la solution et de nous expliquer la configuration et le code.

- Vous pouvez expliquer l'utilité d'un proxy inverse pour améliorer la sécurité de l'infrastructure.

- Vous pouvez expliquer comment accéder au tableau de bord de Traefik et comment il fonctionne.

- Vous avez documenté votre configuration dans votre rapport.
**Étape 5 : Scalabilité et équilibrage de charge** 
L'objectif est de permettre à Traefik de détecter dynamiquement plusieurs instances des serveurs Web (statique/dynamique).**Critères d'acceptation** 
- Vous pouvez démarrer l'infrastructure avec plusieurs instances de chaque serveur (statique et dynamique).

- Vous pouvez ajouter et supprimer dynamiquement des instances de chaque serveur.

- Vous pouvez effectuer une démonstration montrant que Traefik effectue un équilibrage de charge entre les instances.

- Vous avez documenté votre configuration dans votre rapport.
**Étape 6 : Équilibrage de charge avec round-robin et sessions persistantes** 
L'objectif est de modifier la configuration pour que :
- Traefik utilise des sessions persistantes pour les instances de serveur dynamique (API).

- Traefik continue d'utiliser round-robin pour les serveurs statiques (aucun changement nécessaire).
**Critères d'acceptation** 
- Vous effectuez une démonstration montrant que le répartiteur de charge distribue les requêtes HTTP selon un modèle round-robin vers les nœuds du serveur statique.

- Vous prouve que le répartiteur de charge gère les sessions persistantes pour les requêtes HTTP vers les nœuds du serveur dynamique.

- Vous avez documenté votre configuration dans votre rapport.
**Étape 7 : Sécurisation de Traefik avec HTTPS** 
L'objectif est de configurer Traefik pour utiliser HTTPS avec les clients. Vous devrez générer un certificat auto-signé et configurer Traefik en conséquence.**Critères d'acceptation** 
- Vous pouvez effectuer une démonstration où vous montrez que les serveurs statiques et dynamiques sont accessibles via HTTPS.

- Vous avez documenté votre configuration dans votre rapport.
**Étapes optionnelles** 
Si vous réussissez toutes les étapes ci-dessus, vous pouvez atteindre une note de 5.0. Pour obtenir une note plus élevée, vous pouvez compléter une ou plusieurs des étapes optionnelles suivantes :**Étape optionnelle 1 : Interface de gestion** 
Déployez ou développez une application Web qui permet de surveiller et de mettre à jour votre infrastructure Web dynamiquement.**Critères d'acceptation** 
- Vous pouvez effectuer une démonstration montrant l'interface de gestion et la gestion des conteneurs de votre infrastructure.

- Vous avez documenté la manière d'utiliser votre solution.
**Étape optionnelle 2 : API d'intégration - site Web statique** 
Modifiez votre page Web statique pour qu'elle effectue des appels périodiques à votre serveur API et affiche les résultats sur la page.**Critères d'acceptation** 
- Vous avez ajouté du code JavaScript à votre page Web statique pour effectuer au moins une requête GET vers le serveur API.

- Vous avez effectué une démonstration montrant que l'API est appelée et que le résultat est affiché sur la page.