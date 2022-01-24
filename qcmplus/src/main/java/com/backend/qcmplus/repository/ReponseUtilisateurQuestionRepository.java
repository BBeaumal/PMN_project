package com.backend.qcmplus.repository;

import com.backend.qcmplus.model.ReponseUtilisateurQuestion;
import com.backend.qcmplus.model.ReponseUtilisateurQuestionPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReponseUtilisateurQuestionRepository extends JpaRepository<ReponseUtilisateurQuestion, Long> {

    //@Query("SELECT * FROM reponse_utilisateur_question where id_utilisateur =?1")
    //@Query(value = "SELECT reponse_utilisateur_question.* FROM reponse_utilisateur_question WHERE id_utilisateur =:id")
    //List<ReponseUtilisateurQuestion> findAllBy(Long id);
    List<ReponseUtilisateurQuestion> findAllByLinkPk_Utilisateur_IdUtilisateurOrderByLinkPkNumeroTentative(Long idUtilisateur);

}