package com.backend.qcmplus.repository;

import com.backend.qcmplus.model.Question;
import com.backend.qcmplus.model.Reponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReponseRepository extends JpaRepository<Reponse, Long> {


}
