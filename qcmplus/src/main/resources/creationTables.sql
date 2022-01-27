# Creation of the user table
CREATE TABLE Utilisateur (
IdUtilisateur INT auto_increment NOT NULL,
Nom varchar(100) NULL,
Prenom varchar(100) NULL,
Mail varchar(100) NULL,
Login varchar(100) NULL,
Password varchar(100) NULL,
Societe varchar(100) NULL,
isAdmin boolean NOT NULL,
PRIMARY KEY (IdUtilisateur)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;



# Creation of the questionnaire table
CREATE TABLE Questionnaire (
IdQuestionnaire INT auto_increment NOT NULL,
NomQuestionnaire varchar(100) NULL,
DateCreation DATE NULL,
PRIMARY KEY (IdQuestionnaire)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;



# Creation of the question table
CREATE TABLE Question (
IdQuestion INT auto_increment NOT NULL,
Intitule varchar(100) NULL,
IdQuestionnaire INT NOT NULL,
PRIMARY KEY (IdQuestion),
CONSTRAINT Questionnaire_fk FOREIGN KEY (IdQuestionnaire) REFERENCES Questionnaire(IdQuestionnaire)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;



# Creation of the result table
CREATE TABLE Resultat (
IdResultat INT auto_increment NOT NULL,
Libelle varchar(100) NULL,
isCorrect boolean NULL,
IdQuestion INT NOT NULL,
PRIMARY KEY (IdResultat),
CONSTRAINT FK_ResultatQuestion FOREIGN KEY (IdQuestion) REFERENCES Question(IdQuestion)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;



# Creation of the ReponseUtilisateurQuestion table
CREATE TABLE ReponseUtilisateurQuestion (
IdUtilisateur INT NOT NULL,
IdQuestion INT NOT NULL,
reponse INT NULL,
PRIMARY KEY (IdUtilisateur, IdQuestion),
CONSTRAINT FK_ReponseUtilisateurQuestion_Q FOREIGN KEY (IdQuestion) REFERENCES Question(IdQuestion),
CONSTRAINT FK_ReponseUtilisateurQuestion_U FOREIGN KEY (IdUtilisateur) REFERENCES Utilisateur(IdUtilisateur)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;