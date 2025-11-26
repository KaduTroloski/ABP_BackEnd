package com.cinegraudos.repository;

import com.cinegraudos.domain.entity.CinemasEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CinemasRepository extends JpaRepository<CinemasEntity, Integer> {

}
