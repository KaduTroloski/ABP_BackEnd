package com.cinegraudos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cinegraudos.domain.entity.FilmesEntity;

public interface FilmesRepository extends JpaRepository<FilmesEntity, Integer> {
}
