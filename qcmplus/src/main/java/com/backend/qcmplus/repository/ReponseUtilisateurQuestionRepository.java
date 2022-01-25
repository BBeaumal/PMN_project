package com.backend.qcmplus.repository;

import com.backend.qcmplus.model.ReponseUtilisateurQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReponseUtilisateurQuestionRepository extends JpaRepository<ReponseUtilisateurQuestion, Long> {

    List<ReponseUtilisateurQuestion> findAllByLinkPk_Utilisateur_IdUtilisateurOrderByLinkPkNumeroTentative(Long idUtilisateur);

}
