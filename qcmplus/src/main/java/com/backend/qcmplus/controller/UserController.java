package com.backend.qcmplus.controller;


import com.backend.qcmplus.model.*;
import com.backend.qcmplus.bean.QuestionnaireBean;

import com.backend.qcmplus.service.QuestionService;
import com.backend.qcmplus.service.QuestionnaireService;
import com.backend.qcmplus.service.ReponseUtilisateurQuestionService;
import com.backend.qcmplus.service.UtilisateurService;
import com.backend.qcmplus.utils.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import org.springframework.http.HttpStatus;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/rest")
public class UserController {

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private QuestionnaireService surveyService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private ReponseUtilisateurQuestionService reponseUtilisateurQuestionService;

    @GetMapping("/login")
    Mono<String> hello() {
        return Mono.just("loged");
    }

    // Find All Survey
    @GetMapping("/surveys")
    Mono<List<Questionnaire>> findAllSurvey() {
        return Mono.just(surveyService.listAllSurvey());
    }

    // Find All Question
    @GetMapping("/question")
    Mono<List<Question>> findAllQuestion() {
        return Mono.just(questionService.listAllQuestion());
    }

    // Find Survey
    @GetMapping("/survey/{id}")
    Mono<Questionnaire> findOneSurvey(@PathVariable Long id) throws UserNotFoundException {
        Optional<Questionnaire> foundSurvey = surveyService.findById(id);
        if (foundSurvey.isEmpty())
            throw new UserNotFoundException(id);
        foundSurvey.get().getListeQuestion().forEach(question -> question.getReponses().forEach(reponse -> reponse.setIsCorrect(null)));
        return Mono.just(foundSurvey.get());
    }

    // Find Question
    @GetMapping("/question/{id}")
    Mono<Question> findOneQuestion(@PathVariable Long id) throws UserNotFoundException {
        Optional<Question> foundQuestion = questionService.findById(id);
        if (foundQuestion.isEmpty())
            throw new UserNotFoundException(id);
        return Mono.just(foundQuestion.get());
    }

    @GetMapping("/parcours")
    public List<ParcoursBean> noterTouslesQuestionnaires() throws Exception {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Questionnaire> liste = surveyService.listAllSurvey();
        List<ParcoursBean> notesQuestionnaireList= new ArrayList<>();
        for(Questionnaire questionnaire : liste){
            notesQuestionnaireList.add(noteOneQuestionnaire(questionnaire.getIdQuestionnaire()));
        }
        if(notesQuestionnaireList.isEmpty()){
            throw new Exception("pas de questionnaire");
        }
        return notesQuestionnaireList;
    }

    @GetMapping("/questionnaire/{idQuestionnaire}/parcours")
    public ParcoursBean noteOneQuestionnaire(@PathVariable Long idQuestionnaire) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<ReponseUtilisateurQuestion> listeQuestionsRepondues = reponseUtilisateurQuestionService.listAllReponsesFromLastTentative(idQuestionnaire);
        double note = 0;
        ParcoursBean parcoursBean = new ParcoursBean();
        Optional<Questionnaire> questionnaire = surveyService.getSurvey(idQuestionnaire);
        parcoursBean.setNomQuestionnaire(questionnaire.get().getNomQuestionnaire());

        if (principal instanceof UserDetails) {
            for (ReponseUtilisateurQuestion reponseUtilisateurQuestion : listeQuestionsRepondues) {
                if (reponseUtilisateurQuestion.getReponse() == 1) {
                    note++;
                }
            }
            note=(note / listeQuestionsRepondues.size()) * 20;
            parcoursBean.setNote(note);
            return parcoursBean;
        }
        return new ParcoursBean();
    }

/*    Mono<List<ReponseUtilisateurQuestion>> findAllReponses(@PathVariable Long id) throws UserNotFoundException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();
            System.out.println("Utilisateur : "+username);
            Utilisateur tempUser = utilisateurService.getUtilisateurByLogin(username);
            if ( tempUser == null )
                System.out.println("User not found with id survey");
            else{
                if (tempUser.isIsadmin())
                    System.out.println("User must not be Admin");
                else{
                    System.out.println("Utilisateur : "+tempUser.getIdUtilisateur()+ "  : "+tempUser.getPrenom());
                    return Mono.just(reponseUtilisateurQuestionService.listAllQuestionsByUtilisateur(tempUser.getIdUtilisateur()));
                }
            }
        } else {
          throw new UserNotFoundException(id);
        }
        return Mono.just(reponseUtilisateurQuestionService.listAllQuestionsByUtilisateur(id));
    }*/


    @Transactional
    @PostMapping("/questionnaire/repondre")
    void saveNewUser(@RequestBody QuestionnaireBean questionnaire) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "";
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        Utilisateur utilisateur = utilisateurService.getUtilisateurByLogin(username);
        surveyService.addAnswer(questionnaire, utilisateur);
    }
}

