package com.backend.qcmplus.repository;

import com.backend.qcmplus.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    void deleteById(Long id);

    Optional<Utilisateur> findById(Long id);
}
