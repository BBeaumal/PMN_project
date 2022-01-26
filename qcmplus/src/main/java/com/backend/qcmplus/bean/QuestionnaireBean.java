package com.backend.qcmplus.bean;

import com.backend.qcmplus.model.Question;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuestionnaireBean {
    private String dateFin;

    private String dateFormulaire;

    private Long idQuestionnaire;

    private String nomQuestionnaire;

    private String description;

    private String dateCreation;

    private List<Question> listeQuestion;

    private boolean possedeParcours;
}
