package com.backend.qcmplus.service;

import com.backend.qcmplus.model.Question;
import com.backend.qcmplus.model.Reponse;
import com.backend.qcmplus.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public List<Question> listAllQuestion() {
        return questionRepository.findAll();
    }

    public Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }

    public Optional<Question> getQuestion(Long id) {
        return questionRepository.findById(id);
    }

    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }

    public Optional<Question> findById(Long id) {
        return questionRepository.findById(id);
    }
    public List<Reponse> listAllResponses(Long idQuestion){
        Optional<Question> find = questionRepository.findById(idQuestion);
        if(find.isPresent()){
            return find.get().getReponses();
        }
        return new ArrayList<Reponse>();
    }
}
