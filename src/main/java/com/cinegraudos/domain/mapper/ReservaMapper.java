package com.cinegraudos.domain.mapper;

import com.cinegraudos.domain.dto.ReservaResponseDTO;
import com.cinegraudos.domain.entity.AssentoReservaEntity;
import com.cinegraudos.domain.entity.ReservasEntity;

import java.util.List;

public class ReservaMapper {
    public static ReservaResponseDTO toDTO(ReservasEntity reserva, List<AssentoReservaEntity> assentos) {
        List<Integer> idsAssentos = assentos.stream()
                .map(as -> as.getAssento().getId_assentos())
                .toList();

        return new ReservaResponseDTO(
                reserva.getId_reservas(),
                reserva.getSessao().getIdSessoes(),
                reserva.getUsuario().getId_usuarios(),
                reserva.getStatus(),
                idsAssentos
        );
    }
}
