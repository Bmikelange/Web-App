# M1IF10 Projet Transversal 2019-2020

### Groupe 3

    • ABEBE EYOSYAS
    • BARROS MIKEL-ANGE
    • BEDDALIA ZACHARIA
    • BURDIN KEVIN
    • INAYA VICTOR
    • SANDRE VICTOR
    • SCOUFLAIRE THOMAS

## Contenu du projet

Conception puis réalisation d'une application permettant la vérification de la présence des alternants aux différents cours, 
afin de faciliter le travail des étudiants, des professeurs, ainsi que des secrétaires.

## Organisation du dépôt

    • Le répertoire "Code" comporte l'ensemble de l'application du projet.
    • Le répertoire "Wiki" est consacré à l'aspect conception et organisation du projet. Ce dernier contient :
            • un sous-répertoire "CU", contenant un document UserStories.pdf, qui va regrouper l'ensemble des user stories et des cas d'utilisations de l'application,
            • un sous-répertoire "Compte-Rendus", listant les diférentes réunions ayant eu lieu,
            • un sous-répertoire "Diagrammes", incluant les différents diagramme réalisés lors de la conception de l'application,
            • un sous-répertoire "Jalons", contenant les rendus intermédiaires des deux premiers jalons.
            • un sous -répertoire "Démo", dédié à la démostration de Jeudi 28/11.
    • un Wiki plus complet est disponible dans la section "Wiki" du dépôt.  
                
## L'application

### Les commandes utilisées

Le fichier "pom.xml" se trouve à la racine du répertoire "Code". <br/>
Pour build le projet sous forme d'un fichier "value.war" : mvn clean package.<br/>
Placer ledit fichier dans le répertoire "webapps" de Tomcat, ou alors : mvn deploy.<br/>
Après avoir lancé un serveur SonarQube : mvn sonar:sonar pour générer l'analyse SonarQube.


### Les dépendances

Les différences dépendances présentes dans pom.xml :

    • Junit 4.11, pour l'execution des différents tests de l'application
    • Jacoco 0.8.5, pour la couverture des tests d'intégration, avec SonarQube (section "Coverage" de l'analyse de SonarQube)
    • l'API JavaEE 8.0.1 / JSTL 1.2 / API Java Servlet 3.1.0, pour l'intégration de différentes composantes liées à Java dans l'application (servlets, jsp, etc ...)
    • PostgreSQL 42.2.8, pour la liaison de l'application à la base de données psql
    • Hibernate-core 5.4.7.Final / hibernate-validate 6.0.17.Final / hibernate-validate-cdi 6.0.17.Final , pour inclure Hibernate, ORM utilisé pour les interactions entre la base de données et l'application
    • JWT 3.8.3 Pour l'authentification
    • Jackson 2.9.8, pour parser les objets en JSON
    • Itextpdf 5.5.13, pour exporter les données en format PDF
    • SL4J 1.5.12 / SL4J-log4j12 1.5.12, pour les log

### Utilisateurs entrés en base pour accéder a l application

#### Etudiant
<ul>
<li>password ; 'mikado', id: `11508726`, nom : 'Barros', prénom : 'Mikel-ange', lesson : 'M1-info' </li>
<li>password ; 'que20', id: `11509726`, nom : 'Nom', prénom : 'Kevin', lesson : 'M1-info' </li>
<li>password ; 'zac', id; `11508556`, nom : 'Nom', prénom : 'Zacharia', lesson : 'M1-info' </li>
<li>password ; 'thomas', id: `11508966`, nom : 'Scouflair', prénom : 'Thomas', lesson : 'M1-info' </li>
<li>password ; 'eyosyas', id: `11508566`, nom : 'Abebe', prénom : 'Eyosyas', lesson : 'M1-info' </li>
<li>password ; 'vico', id: `11508416`, nom : 'Nom', prénom : 'Victor', lesson : 'M2-TIW' </li>
<li>password ; 'victor', id: `11508746`, nom : 'Sandre', prénom : 'Victor', lesson : 'M2-SRIV' </li>
</ul>

#### Professeurs
<ul>
<li>id: '5050', password: 'please',nom: 'Medini',prenom: 'Lionel'</li>
<li>id: '5051', password: 'Bonjour',nom: 'Coquery',prenom: 'Emmanuel'</li>
</ul>

#### Secrétaire

<ul>
<li>id: '512', password: 'Secret',nom: 'Gent',prenom: 'Josephine'</li>
</ul>

    
