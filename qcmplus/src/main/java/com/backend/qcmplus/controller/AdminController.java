package com.backend.qcmplus.controller;

import com.backend.qcmplus.model.Question;
import com.backend.qcmplus.model.Questionnaire;
import com.backend.qcmplus.model.Reponse;
import com.backend.qcmplus.model.Utilisateur;
import com.backend.qcmplus.repository.ReponseRepository;
import com.backend.qcmplus.service.QuestionService;
import com.backend.qcmplus.service.QuestionnaireService;
import com.backend.qcmplus.service.UtilisateurService;
import com.backend.qcmplus.utils.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/rest")
public class AdminController {

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private QuestionnaireService surveyService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private ReponseRepository reponseRepository;

    // Save
    @Transactional
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/users")
    Mono<Utilisateur> saveNewUser(@RequestBody Utilisateur newUser) {
        String pwd = newUser.getPassword();
        String encryptPwd = passwordEncoder.encode(pwd);
        newUser.setPassword(encryptPwd);
        return Mono.just(utilisateurService.saveUser(newUser));
    }

    @PostMapping("/user")
    HttpStatus newUser(@RequestBody Utilisateur newUser) throws Exception {
        if ("".equals(newUser.getPassword()) || newUser.getPassword() == null) {
            newUser.setPassword(utilisateurService.getUtilisateur(newUser.getIdUtilisateur()).get().getPassword());
        } else {
            String pwd = newUser.getPassword();
            String encryptPwd = passwordEncoder.encode(pwd);
            newUser.setPassword(encryptPwd);
        }
        if (newUser.getIdUtilisateur() == null) {
            Utilisateur utilisateur = utilisateurService.getUtilisateurByLogin(newUser.getLogin());
            if (utilisateur != null) {
                throw new Exception("Ce login existe déjà");
            }
        }
        utilisateurService.saveUser(newUser);
        return HttpStatus.CREATED;

    }

    // Find
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/users")
    Mono<List<Utilisateur>> findAll() {
        return Mono.just(utilisateurService.listAllUser());
    }

    // Find
    @GetMapping("/users/{id}")
    Mono<Utilisateur> findOne(@PathVariable Long id) throws UserNotFoundException {
        Optional<Utilisateur> foundUser = utilisateurService.getUtilisateur(id);
        if (foundUser.isEmpty())
            throw new UserNotFoundException(id);
        return Mono.just(foundUser.get());
    }

    // Save or update
    @PutMapping("/users/{id}")
    Mono<Utilisateur> saveOrUpdate(@RequestBody Utilisateur newUser, @PathVariable Long id) {
        Optional<Utilisateur> foundUser = utilisateurService.getUtilisateur(id);

        return foundUser.map(x -> {
            x.setLogin(newUser.getLogin());
            x.setMail(newUser.getMail());
            x.setNom(newUser.getNom());
            x.setPassword(newUser.getPassword());
            x.setPrenom(newUser.getPrenom());
            x.setSociete(newUser.getSociete());

            return Mono.just(utilisateurService.saveUser(x));
        }).orElseGet(() -> {
            newUser.setIdUtilisateur(id);
            return Mono.just(utilisateurService.saveUser(newUser));
        });
    }

    @GetMapping("/user/{id}")
    void deleteUtilisateur(@PathVariable Long id) {
        utilisateurService.deleteUtilisateur(id);
    }

    // Save Survey
    // return 201 instead of 200
    // @ResponseStatus(HttpStatus.CREATED)
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/survey")
    Mono<Questionnaire> newSurvey(@RequestBody Questionnaire newSurvey) {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");// dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        newSurvey.setDateCreation(strDate);
        return Mono.just(surveyService.saveSurvey(newSurvey));
    }

    // Get all surveys
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/surveys")
    Mono<List<Questionnaire>> findAllSurveys() {
        return Mono.just((surveyService.listAllSurvey()));
    }

    // Save Question
    // return 201 instead of 200
    // @ResponseStatus(HttpStatus.CREATED)
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/question")
    @Transactional
    Mono<Question> newQuestion(@RequestBody Question newQuestion) {


        Question question = questionService.saveQuestion(newQuestion);
        for (Reponse reponse : question.getReponses()){
            reponse.setQuestion(question);
        }
        return Mono.just(questionService.saveQuestion(question));
    }

    // Save or update Question
    @PutMapping("/question/{id}")
    Mono<Question> saveOrUpdateQuestion(@RequestBody Question newQuestion, @PathVariable Long id) {
        Optional<Question> foundQuestion = questionService.findById(id);

        return foundQuestion.map(x -> {
            x.setIntitule(newQuestion.getIntitule());
            x.setQuestionnaire(newQuestion.getQuestionnaire());
            x.setReponses(newQuestion.getReponses());

            return Mono.just(questionService.saveQuestion(x));
        }).orElseGet(() -> {
            newQuestion.setIdQuestion(id);
            return Mono.just(questionService.saveQuestion(newQuestion));
        });
    }

    // Get Survey Question
    // return 201 instead of 200
    @GetMapping("/survey/{id}/question")
    Mono<List<Question>> getAllQuestion(@PathVariable long id) {
        return Mono.just(surveyService.listAllQuestion(id));
    }

    // Save or update Survey
    @PutMapping("/survey/{id}")
    Mono<Questionnaire> saveOrUpdateSurvey(@RequestBody Questionnaire newSurvey, @PathVariable Long id) {
        Optional<Questionnaire> foundSurvey = surveyService.findById(id);

        return foundSurvey.map(x -> {
            x.setDescription(newSurvey.getDescription());
            x.setDateCreation(newSurvey.getDateCreation());
            x.setNomQuestionnaire(newSurvey.getNomQuestionnaire());
            x.setListeQuestion(newSurvey.getListeQuestion());

            return Mono.just(surveyService.saveSurvey(x));
        }).orElseGet(() -> {
            newSurvey.setIdQuestionnaire(id);
            return Mono.just(surveyService.saveSurvey(newSurvey));
        });
    }

    @GetMapping("/survey/{id}")
    void deleteSurvey(@PathVariable Long id) {
        surveyService.deleteSurvey(id);
    }

    @GetMapping("/question/{id}")
    void deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
    }
}
