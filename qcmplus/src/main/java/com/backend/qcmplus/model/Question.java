package com.backend.qcmplus.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Question")
public class Question {

    private int id;
    private String intitule;
    private List<Resultat> resultat;


    public Question(int id, String intitule,
                    List<Resultat> resultat) {
        this.id = id;
        this.intitule = intitule;
        this.resultat = resultat;
    }

    public Question() {
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public List<Resultat> getResultat() {
        return resultat;
    }

    public void setResultat(List<Resultat> resultat) {
        this.resultat = resultat;
    }
}
