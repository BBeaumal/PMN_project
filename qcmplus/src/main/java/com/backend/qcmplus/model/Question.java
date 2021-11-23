package com.backend.qcmplus.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Question")
@Setter
@Getter
@NoArgsConstructor
public class Question implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idQuestion;
    private String intitule;
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Resultat> resultat;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idQuestionnaire")
    private Questionnaire questionnaire;

    public Question(String intitule, List<Resultat> resultat, Questionnaire questionnaire) {
        this.intitule = intitule;
        this.resultat = resultat;
        this.questionnaire = questionnaire;
    }
}
