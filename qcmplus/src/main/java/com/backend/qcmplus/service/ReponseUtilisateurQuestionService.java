package com.backend.qcmplus.service;

import com.backend.qcmplus.model.ReponseUtilisateurQuestion;
import com.backend.qcmplus.repository.ReponseUtilisateurQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReponseUtilisateurQuestionService {

    @Autowired
    private ReponseUtilisateurQuestionRepository reponseUtilisateurQuestionRepository;

    public List<ReponseUtilisateurQuestion> listAllQuestion() {
        return reponseUtilisateurQuestionRepository.findAll();
    }

    public List<ReponseUtilisateurQuestion> listAllQuestionByIdUtilisateur(Long id) {
        return reponseUtilisateurQuestionRepository.findAllByLinkPk_Utilisateur_IdUtilisateurOrderByLinkPkNumeroTentative(id);
    }
}
