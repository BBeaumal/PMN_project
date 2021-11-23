package com.backend.qcmplus.model;

import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Resultat")
@Getter
@Setter
@NoArgsConstructor
public class Resultat {
    private int id;
    private String libelle;
    private boolean iscorrect;
    private boolean isanswered;

    public Resultat(int id, String libelle, boolean iscorrect, boolean isanswered) {
        this.id = id;
        this.libelle = libelle;
        this.iscorrect = iscorrect;
        this.isanswered = isanswered;
    }

    @Override
    public String toString() {
        return "Resultat [id=" + id + ", isanswered=" + isanswered + ", iscorrect=" + iscorrect + ", libelle=" + libelle
                + "]";
    }

}
