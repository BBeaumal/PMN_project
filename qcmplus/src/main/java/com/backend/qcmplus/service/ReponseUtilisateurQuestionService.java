package com.backend.qcmplus.service;

import com.backend.qcmplus.model.ReponseUtilisateurQuestion;
import com.backend.qcmplus.model.Utilisateur;
import com.backend.qcmplus.repository.QuestionnaireRepository;
import com.backend.qcmplus.repository.ReponseUtilisateurQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    @Autowired
    UtilisateurService utilisateurService;


    public List<ReponseUtilisateurQuestion> listAllQuestions(){
        return repository.findAll();
    }

    /**
     * retrieve all answered answers for a specific user
     * @param idUser is the Id of the User
     * @return a list of answered answers
     */
    public List<ReponseUtilisateurQuestion> listAllQuestionsByUtilisateur(Long idUser){
        return repository.findAllByLinkPk_Utilisateur_IdUtilisateurOrderByLinkPkNumeroTentative(idUser);
    }

    /**
     * retrieve all answered answers for a specific survey
     * @param idQuestionnaire is the id of survey
     * @return a list of answered answer for a survey
     */
    public List<ReponseUtilisateurQuestion> listAllQuestionsFromOneQuestionnaire(Long idQuestionnaire) {
        Utilisateur utilisateur = utilisateurService.getCurrentUtilisateur();
        List<ReponseUtilisateurQuestion> listeQuestionsRepondues =listAllQuestionsByUtilisateur(utilisateur.getIdUtilisateur());
        listeQuestionsRepondues.removeIf(questionRepondue -> !questionRepondue.getLinkPk().getQuestion().getQuestionnaire().getIdQuestionnaire().equals(idQuestionnaire));
    return listeQuestionsRepondues;
    }

    /**
     *
     * @param idQuestionnaire is id of a survey
     * @return all answered answer from the last attempt for a survey
     */
    public List<ReponseUtilisateurQuestion> listAllReponsesFromLastTentative(Long idQuestionnaire){
        List<ReponseUtilisateurQuestion> listeQuestions = listAllQuestionsFromOneQuestionnaire(idQuestionnaire);
        long numeroMax = listeQuestions
                .stream()
                .mapToLong(v -> v.getLinkPk().getNumeroTentative())
                .max().orElse(0);

        return listeQuestions
                .stream()
                .filter(reponseUtilisateurQuestion -> reponseUtilisateurQuestion.getLinkPk().getNumeroTentative()==numeroMax)
                .collect(Collectors.toList());
    }

}
