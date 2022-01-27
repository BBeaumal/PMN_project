USE qcmplus

CREATE TABLE IF NOT EXISTS Questionnaire (
   IdQuestionnaire INT auto_increment NOT NULL,
   NomQuestionnaire varchar(100) NULL,
   Auteur VARCHAR(6) CHARACTER SET utf8
);
INSERT INTO questionnaire VALUES
    (1212,'JAVA','Admin1');
