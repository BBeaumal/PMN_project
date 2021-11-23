package com.backend.qcmplus.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Scoretrack")
@Getter
@Setter
@NoArgsConstructor
public class Scoretrack {

    private int idutilisateur;
    private int idquestion;
    private int reponse;

    public Scoretrack(int idutilisateur, int idquestion, int reponse) {
        this.idutilisateur = idutilisateur;
        this.idquestion = idquestion;
        this.reponse = reponse;
    }

}
