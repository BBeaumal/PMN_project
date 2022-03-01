# PMN_project : Application de questionnaire pour LECLIENT

## Description

Ce projet a pour but de moderniser une application déjà existante appartenant à LECLIENT.

Cette application permet de gérer les questionnaires ainsi que d'afficher les notes obtenues sur les différents parcours en fonction de l'utilisateur.

Elle est composée d'un backend en Java et d'un frontend en Angular.

## Technologies utilisées

- Java 11
- Maven
- SpringBoot
- MySQL Database
- Angular

## Prérequis nécessaires

- [MySQL](https://dev.mysql.com/downloads/installer/)
- [Intellij Ultimate](https://www.jetbrains.com/fr-fr/idea/download/#section=windows)
- [Git](https://git-scm.com/downloads)
- [NodeJS](https://nodejs.org/en/download/)

## Installation


### 2. Backend

Lancez Intellij Ultimate
Cliquer sur "Get From VSC:
![image](https://user-images.githubusercontent.com/34241469/156140148-1abc5aee-10b6-46c5-863a-3d539bab6fb0.png)

Dans "URL" mettre : https://github.com/N0rvel/PMN_project.git puis cliquer sur "Clone"

Fermez le projet
![image](https://user-images.githubusercontent.com/34241469/156140722-2c62db6e-db50-40df-8e6a-096ffe060e55.png)

Cliquer sur "Open" et selectionnez le fichier pom.xml du projet cloné 
![image](https://user-images.githubusercontent.com/34241469/156140963-1527990c-c31e-4188-8463-2a7f365fe326.png)

Cliquez sur "Open as project"; Trust project

Faites clique droit sur le fichier Pom.xml, puis "Maven; Reload project"
![image](https://user-images.githubusercontent.com/34241469/156141348-4ac5b6eb-fe35-48a2-a3c8-93b47c30c600.png)

Dans le backend, modifier le fichier [application.properties](qcmplus/src/main/resources/application.properties) avec vos informations personnelles MySQL (username et password).

Passez à l'étape 3

### 3. Base de données

Exécutez le client MySQL Server situé à l'adresse suivante : (ou cherchez MySql Command Line Client)

`C:\Program Files\MySQL\MySQL Server 8.0\bin\mysql.exe`

Rentrez votre mot de passe et validez.

Lancez le backend, la base de données va automatiquement être créée:
Cliquez sur la fleche verte en haut ![image](https://user-images.githubusercontent.com/34241469/156142270-30b15db3-c268-46c4-84a3-d10387fac805.png)


Sélectionnez la base de données :

`use qcmplus`

Créer un compte administrateur avec le mot de passe suivant prédéfini :

`INSERT INTO qcmplus.utilisateur
(isadmin, login, mail, nom, password, prenom, societe, id_utilisateur)
VALUES(1, 'admin', 'utilisateur@tst.com', 'admin', '$2a$10$kh/kSTzNjeEIQ6F08YH/wO3T/17cJlDWOLZIucwE.phvOLw17XIGC', 'admin', 'LECLIENT', 0);
`
```Celà correspond aux identifiants suivants dans l'application:
Identifiant: admin
Mot de passe: admin```

### 4. Frontend

Pour rentrer dans le frontend, mettez vous à la racine du projet et tapez la commande suivante dans le terminal d'intellij:

```cd ../front```
![image](https://user-images.githubusercontent.com/34241469/156142634-37aaf28c-3f97-4474-bf97-4392857e0284.png)


Une fois dans le frontend, installez Angular avec la commande suivante :

```npm install -g @angular/cli```

Installez toutes les dépendances nécessaires au projet :

```npm install```

Lancez le serveur :

```ng serve```

## Contributeurs

- [Bastien Proudhom](https://github.com/N0rvel)
- [Baptiste Beaumal](https://github.com/BBeaumal)
- [Guillaume Bikoko](https://github.com/steevyor)

