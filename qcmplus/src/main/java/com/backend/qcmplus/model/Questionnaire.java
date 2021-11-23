package com.backend.qcmplus.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "questionnaire")
@Setter
@Getter
@NoArgsConstructor
public class Questionnaire implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idQuestionnaire;

    private String nomQuestionnaire;

    private String auteur;

    private Date dateCreation;

    @OneToMany(mappedBy = "idQuestion", cascade = CascadeType.ALL)
    private List<Question> listeQuestion;

}