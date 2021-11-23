package com.backend.qcmplus.model;

import java.io.Serializable;

import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Resultat")
@Getter
@Setter
@NoArgsConstructor
public class Resultat implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idResultat;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_question")
    private Question question;
    private String libelle;
    private boolean isCorrect;

    public Resultat(int idResultat, String libelle, boolean isCorrect) {
        this.idResultat = idResultat;
        this.libelle = libelle;
        this.isCorrect = isCorrect;
    }

    @Override
    public String toString() {
        return "Resultat [idResultat=" + idResultat + ", iscorrect=" + isCorrect + ", libelle=" + libelle + "]";
    }

}
