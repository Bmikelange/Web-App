# La Demo

## Instructions d'utilisation

Le fichier "pom.xml" se trouve à la racine du répertoire "Code".   
Pour build le projet sous forme d'un fichier "value.war" : mvn clean package.  
Placer ledit fichier dans le répertoire "webapps" de Tomcat, ou alors : mvn deploy.  
Après avoir lancé un serveur SonarQube : mvn sonar:sonar pour générer l'analyse SonarQube.  
Pour générer la javadoc : mvn javadoc;javadoc

## La VM

Disponible à l'adresse : **etudiant@192.168.74.196**  
mot de passe: etudiant.

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


```sql

INSERT INTO SECRETARY (SECRET_ID,PASSWORD,NAME,FIRST_NAME)
VALUES ('512', 'Secret','Gent','Josephine');
```