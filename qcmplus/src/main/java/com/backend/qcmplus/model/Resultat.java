package com.backend.qcmplus.model;

import javax.persistence.*;

@Entity
@Table(name = "Resultat")
public class Resultat
{
    private int id;
    private String libelle;
    private boolean iscorrect;
    private boolean isanswered;

    public Resultat() {
    }

    public Resultat(int id, String libelle,
                    boolean iscorrect, boolean isanswered) {
        this.id = id;
        this.libelle = libelle;
        this.iscorrect = iscorrect;
        this.isanswered = isanswered;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public boolean isIscorrect() {
        return iscorrect;
    }

    public void setIscorrect(boolean iscorrect) {
        this.iscorrect = iscorrect;
    }

    public boolean isIsanswered() {
        return isanswered;
    }

    public void setIsanswered(boolean isanswered) {
        this.isanswered = isanswered;
    }
}
