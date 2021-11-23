package com.backend.qcmplus.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class ReponseUtilisateurQuestionPk implements Serializable {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUtilisateur")
    private Utilisateur utilisateur;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idQuestion")
    private Question question;
}
