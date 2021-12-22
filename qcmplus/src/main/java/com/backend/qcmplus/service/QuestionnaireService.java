package com.backend.qcmplus.service;

import com.backend.qcmplus.model.Question;
import com.backend.qcmplus.model.Questionnaire;
import com.backend.qcmplus.repository.QuestionnaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class QuestionnaireService {

    @Autowired
    private QuestionnaireRepository questionnaireRepository;

    public List<Questionnaire> listAllSurvey() {
        return questionnaireRepository.findAll();
    }

    public Questionnaire saveSurvey(Questionnaire question) {
        return questionnaireRepository.save(question);
    }

    public Optional<Questionnaire> getSurvey(Long id) {
        return questionnaireRepository.findById(id);
    }

    public void deleteSurvey(Long id) {
        questionnaireRepository.deleteById(id);
    }

    public Optional<Questionnaire> findById(Long id) {
        return questionnaireRepository.findById(id);
    }

    public List<Question> listAllQuestion(Long id) {
        Optional<Questionnaire> find = questionnaireRepository.findById(id);
        if (!find.isEmpty()) {
            return find.get().getListeQuestion();
        }
        return new ArrayList<Question>();
    }
}
