package com.backend.qcmplus.repository;

import com.backend.qcmplus.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    void deleteById(Long id);

    Optional<Utilisateur> findById(Long id);
}
