package com.cinegraudos.repository;

import com.cinegraudos.domain.entity.SalasEntity;
import com.cinegraudos.domain.entity.SessoesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

public interface SessoesRepository extends JpaRepository<SessoesEntity, Integer> {
    List<SessoesEntity> findBySalaAndHorarioInicioBetween(SalasEntity sala, Instant inicio, Instant fim);
}
