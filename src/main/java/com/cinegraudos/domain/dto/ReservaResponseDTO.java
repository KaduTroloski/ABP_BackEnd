package com.cinegraudos.domain.dto;

import java.util.List;

public record ReservaResponseDTO(Integer idReserva,
                                 Integer idSessao,
                                 Integer idUsuario,
                                 String status,
                                 List<Integer> assentos) {
}
