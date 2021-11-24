package com.backend.qcmplus.service;

import com.backend.qcmplus.model.Questionnaire;
import com.backend.qcmplus.repository.QuestionnaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class QuestionnaireService {

    @Autowired
    private QuestionnaireRepository questionRepository;

    public List<Questionnaire> listAllSurvey() {
        return questionRepository.findAll();
    }

    public Questionnaire saveSurvey(Questionnaire question) {
        return questionRepository.save(question);
    }

    public Optional<Questionnaire> getSurvey(Long id) {
        return questionRepository.findById(id);
    }

    public void deleteSurvey(Long id) {
        questionRepository.deleteById(id);
    }

    public Optional<Questionnaire> findById(Long id) {
        return questionRepository.findById(id);
    }
}
