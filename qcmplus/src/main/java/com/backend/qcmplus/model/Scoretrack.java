package com.backend.qcmplus.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Scoretrack")
public class Scoretrack {

    private int idutilisateur;
    private int idquestion;
    private int reponse;


    public Scoretrack(int idutilisateur,
                      int idquestion, int reponse)
    {
        this.idutilisateur = idutilisateur;
        this.idquestion = idquestion;
        this.reponse = reponse;
    }

    public Scoretrack() {
    }
}
