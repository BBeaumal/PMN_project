package com.backend.qcmplus.controller;

import com.backend.qcmplus.model.*;
import com.backend.qcmplus.service.QuestionService;
import com.backend.qcmplus.service.QuestionnaireService;
import com.backend.qcmplus.service.ReponseUtilisateurQuestionService;
import com.backend.qcmplus.service.UtilisateurService;
import com.backend.qcmplus.utils.NotFoundException;
import com.backend.qcmplus.utils.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.*;


@RestController
@RequestMapping("/rest")
public class UserController {

    @Autowired
    private QuestionnaireService surveyService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UtilisateurService utilisateurService;

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

    @GetMapping("/survey/{id}/reponses")
    Mono<List<ReponseUtilisateurQuestion>> findAllReponse(@PathVariable Long id) throws NotFoundException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            System.out.println("Utilisateur : " + username);
            Utilisateur tempUser = utilisateurService.getUtilisateurByLogin(username);
            if (tempUser == null)
                throw new NotFoundException("User not found with id survey", 0L);
            else {
                if (tempUser.isIsadmin())
                    throw new NotFoundException("User must not be Admin", 0L);
                else {
                    System.out.println("Utilisateur : " + tempUser.getIdUtilisateur() + "  : " + tempUser.getPrenom());
                    return Mono.just(reponseUtilisateurQuestionService.listAllQuestionByIdUtilisateur(tempUser.getIdUtilisateur()));
                }
            }
        } else {
            throw new NotFoundException("User not found logged in", 0L);
        }
    }

    //in progress
    @GetMapping("/questionnaire/{idQuestionnaire}/parcours")
    public List<double> noteOneQuestionnaire(@PathVariable Long idQuestionnaire) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username = ((UserDetails) principal).getUsername();
        List<ReponseUtilisateurQuestion> listeQuestionsRepondues = reponseUtilisateurQuestionService.listAllReponsesFromLastTentative(idQuestionnaire);

        List<ReponseUtilisateurQuestion> listAll = reponseUtilisateurQuestionService.listAllQuestionByIdUtilisateur(utilisateurService.getUtilisateurByLogin(username).getIdUtilisateur());
        Optional<Questionnaire> questionnaire = surveyService.findById(idQuestionnaire);


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
            note = (note / listeQuestionsRepondues.size()) * 20;
            parcoursBean.setNote(note);
            return parcoursBean;
        }
        return new ParcoursBean();
    }

    @GetMapping("/survey/{idQuestionnaire}/mark")
    public Object noteOneQuestionnaire(@PathVariable Long idQuestionnaire) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List listeDeNote = new ArrayList();

        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            Map<Integer,ReponseUtilisateurQuestionPk> values = new HashMap<Integer, ReponseUtilisateurQuestionPk>();

            List<ReponseUtilisateurQuestion> listeQuestionsRepondues = reponseUtilisateurQuestionService.listAllQuestionByIdUtilisateur(utilisateurService.getUtilisateurByLogin(username).getIdUtilisateur());
            for (ReponseUtilisateurQuestion rep : listeQuestionsRepondues)
            {
                values.put(rep.getLinkPk().getNumeroTentative(), rep.getLinkPk());
            }

            values.

            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            System.out.println();
            List<Questionnaire> listeQuestionsRepondues = surveyService.listAllSurvey();
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
                note = (note / listeQuestionsRepondues.size()) * 20;
                parcoursBean.setNote(note);
                return parcoursBean;
            }
            return new ParcoursBean();
        }
    }

    double calculnote(List <ReponseUtilisateurQuestion> reponses, Long idQuestionnaire){
        int sizeQuestion = surveyService.listAllQuestion(idQuestionnaire).size();
        int rightValue = 0;
        for (ReponseUtilisateurQuestion reponseUtilisateurQuestion : reponses) {
            if (reponseUtilisateurQuestion.getReponse() == 1) {
                rightValue ++;
            }
        }

        return (rightValue/ sizeQuestion) * 20;
    }
}