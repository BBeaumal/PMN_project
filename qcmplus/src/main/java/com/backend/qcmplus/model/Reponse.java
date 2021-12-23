package com.backend.qcmplus.model;

import java.io.Serializable;

import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Reponse")
@Getter
@Setter
@NoArgsConstructor
public class Reponse implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idReponse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_question")
    private Question question;

    private String libelle;

    private Boolean isCorrect;

    @Override
    public String toString() {
        return "Reponse [idReponse=" + idReponse + ", iscorrect=" + isCorrect + ", libelle=" + libelle + "]";
    }

}
