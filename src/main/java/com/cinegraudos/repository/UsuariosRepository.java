package com.cinegraudos.repository;

import com.cinegraudos.domain.entity.UsuariosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuariosRepository extends JpaRepository<UsuariosEntity, Integer> {
    Optional<UsuariosEntity> findByEmail(String email);
}
