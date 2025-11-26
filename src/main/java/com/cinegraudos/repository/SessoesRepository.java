package com.cinegraudos.repository;

import com.cinegraudos.domain.entity.SessoesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessoesRepository extends JpaRepository<SessoesEntity, Integer> {
}
