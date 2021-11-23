package com.backend.qcmplus.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Utilisateur")
@Getter
@Setter
@NoArgsConstructor
public class Utilisateur {
    @Id
    private int id;
    private String nom;
    private String prenom;
    private String mail;
    private String login;
    private String password;
    private String societe;
    private boolean isadmin;

    public Utilisateur(int id, String nom, String prenom, String mail, String login, String password, String societe,
            Boolean isadmin) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.login = login;
        this.password = password;
        this.societe = societe;
        this.isadmin = isadmin;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", nom='" + nom + '\'' + ", prenom='" + prenom + '\'' + ", mail='" + mail
                + '\'' + ", login='" + login + '\'' + ", password='" + password + '\'' + ", societe='" + societe + '\''
                + ", isadmin=" + isadmin + '}';
    }
}
