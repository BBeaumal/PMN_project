package com.backend.qcmplus.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "Questionnaire")
public class Questionnaire
{

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

    public Questionnaire(int id, String nom, String auteur,
                         Date date, List<Question> listequestion, int score)
    {
        this.id = id;
        this.nom = nom;
        this.auteur = auteur;
        this.date = date;
        this.listequestion = listequestion;
        this.score = score;
    }

    public Questionnaire ()
    {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Question> getListequestion() {
        return listequestion;
    }

    public void setListequestion(List<Question> listequestion) {
        this.listequestion = listequestion;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}