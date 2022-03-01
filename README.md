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
- un IDE (Eclipse, VS Code, etc...)
- [Git](https://git-scm.com/downloads)
- [NodeJS](https://nodejs.org/en/download/)

## Installation

### 1.  Cloner le repository avec la commande Git Bash suivante :

`git clone https://github.com/N0rvel/PMN_project.git`

### 2. Backend

Rentrer dans le backend en ouvrant un terminal à la racine du projet, puis tapez :

`cd qcmplus`

Dans le backend, modifier le fichier [application.properties](qcmplus/src/main/resources/application.properties) avec vos informations personnelles MySQL (username et password).

Faire clique droit sur le répertoire qcmplus; maven install

![image](https://user-images.githubusercontent.com/34241469/156135005-d08fb58b-f876-4908-9d7b-688f73d7e586.png)

Passez à l'étape 3

### 3. Base de données

Exécutez le client MySQL Server situé à l'adresse suivante : (ou cherchez MySql Command Line Client)

`C:\Program Files\MySQL\MySQL Server 8.0\bin\mysql.exe`

Rentrez votre mot de passe et validez.

Lancez le backend, en vous plaçant dans la classe suivante [QcmplusApplication](qcmplus/src/main/java/com/backend/qcmplus/QcmplusApplication.java), la base de données va automatiquement être créée.

Sélectionnez la base de données :

`use qcmplus`

Créer un compte administrateur avec le mot de passe suivant prédéfini :

`INSERT INTO qcmplus.utilisateur
(isadmin, login, mail, nom, password, prenom, societe, id_utilisateur)
VALUES(1, 'admin', 'utilisateur@tst.com', 'admin', '$10$kh/kSTzNjeEIQ6F08YH/wO3T/17cJlDWOLZIucwE.phvOLw17XIGC', 'admin', 'LECLIENT', 0);
`

### 4. Frontend

Pour rentrer dans le frontend, mettez vous à la racine du projet et tapez la commande suivante dans un terminal :

```cd front```

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

