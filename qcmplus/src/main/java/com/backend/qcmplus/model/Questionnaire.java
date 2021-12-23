package com.backend.qcmplus.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
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

    private String description;

    private String dateCreation;

    @OneToMany(mappedBy = "idQuestion", cascade = CascadeType.ALL)
    private List<Question> listeQuestion;

}