package com.cinegraudos.domain.dto;

import java.util.List;

public record ReservaRequestDTO(Integer idFilme,
                                Integer idSessao,
                                List<Integer> idAssentos,
                                Integer idUsuario) {
}
