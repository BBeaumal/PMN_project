package com.backend.qcmplus.service;

import com.backend.qcmplus.model.ReponseUtilisateurQuestion;
import com.backend.qcmplus.repository.QuestionnaireRepository;
import com.backend.qcmplus.repository.ReponseUtilisateurQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ReponseUtilisateurQuestionService {

    @Autowired
    ReponseUtilisateurQuestionRepository repository;
    @Autowired
    QuestionnaireRepository questionnaireRepository;

    public List<ReponseUtilisateurQuestion> listAllQuestions(){
        return repository.findAll();
    }

    public List<ReponseUtilisateurQuestion> listAllQuestionsByUtilisateur(Long idUser){
        return repository.findAllByLinkPk_Utilisateur_IdUtilisateurOrderByLinkPkNumeroTentative(idUser);
    }

    public List<ReponseUtilisateurQuestion> listAllQuestionsFromOneQuestionnaire(Long idQuestionnaire) {
        List<ReponseUtilisateurQuestion> listeQuestionsRepondues = listAllQuestions();
        listeQuestionsRepondues.removeIf(questionRepondue -> !questionRepondue.getLinkPk().getQuestion().getQuestionnaire().getIdQuestionnaire().equals(idQuestionnaire));
    return listeQuestionsRepondues;
    }

    public List<ReponseUtilisateurQuestion> listAllReponsesFromLastTentative(Long idQuestionnaire){
        List<ReponseUtilisateurQuestion> listeQuestions = listAllQuestionsFromOneQuestionnaire(idQuestionnaire);
        long numeroMax = listeQuestions
                .stream()
                .mapToLong(v -> v.getLinkPk().getNumeroTentative())
                .max().orElseThrow(NoSuchElementException::new);

        return listeQuestions
                .stream()
                .filter(reponseUtilisateurQuestion -> reponseUtilisateurQuestion.getLinkPk().getNumeroTentative()==numeroMax)
                .collect(Collectors.toList());
    }

}