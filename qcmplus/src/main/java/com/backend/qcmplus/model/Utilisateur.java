package com.backend.qcmplus.model;

import java.io.Serializable;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Utilisateur")
@Getter
@Setter
@NoArgsConstructor
public class Utilisateur implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idUtilisateur;

    private String nom;

    private String prenom;

    private String mail;

    private String login;

    private String password;

    private String societe;

    private boolean isadmin;

    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + idUtilisateur + ", nom='" + nom + '\'' + ", prenom='" + prenom + '\''
                + ", mail='" + mail + '\'' + ", login='" + login + '\'' + ", password='" + password + '\''
                + ", societe='" + societe + '\'' + ", isadmin=" + isadmin + '}';
    }

}
