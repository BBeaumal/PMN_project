package com.backend.qcmplus.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "Questionnaire")
@Setter
@Getter
@NoArgsConstructor
public class Questionnaire {

    private int id;
    private String nom;
    private String auteur;
    private Date date;
    private List<Question> listequestion;
    private int score;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Questionnaire(int id, String nom, String auteur, Date date, List<Question> listequestion, int score) {
        this.id = id;
        this.nom = nom;
        this.auteur = auteur;
        this.date = date;
        this.listequestion = listequestion;
        this.score = score;
    }

}