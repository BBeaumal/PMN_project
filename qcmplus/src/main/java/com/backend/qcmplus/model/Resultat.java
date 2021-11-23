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
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idQuestion")
    private Question question;
    private String libelle;
    private boolean isCorrect;

    public Resultat(int id, String libelle, boolean isCorrect) {
        this.id = id;
        this.libelle = libelle;
        this.isCorrect = isCorrect;
    }

    @Override
    public String toString() {
        return "Resultat [id=" + id + ", iscorrect=" + isCorrect + ", libelle=" + libelle + "]";
    }

}
