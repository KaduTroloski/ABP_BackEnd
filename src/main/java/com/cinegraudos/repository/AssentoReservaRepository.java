package com.cinegraudos.repository;

import com.cinegraudos.domain.entity.AssentoReservaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AssentoReservaRepository extends JpaRepository<AssentoReservaEntity, Integer> {
    @Query("SELECT ar.assento.id_assentos " +
            "FROM AssentoReservaEntity ar " +
            "WHERE ar.reserva.sessao.idSessoes = :idSessao")
    List<Integer> findAssentosReservadosPorSessao(@Param("idSessao") Integer idSessao);
}
