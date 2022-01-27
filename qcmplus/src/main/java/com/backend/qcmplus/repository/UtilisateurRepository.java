package com.backend.qcmplus.repository;

import com.backend.qcmplus.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    void deleteByidUtilisateur(Long id);

    Optional<Utilisateur> findByidUtilisateur(Long id);

    Utilisateur findUtilisateurByLogin(String login);

    List<Utilisateur> findAllByIsadminFalse();
}
