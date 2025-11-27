package com.cinegraudos.domain.dto;

import java.time.Instant;

public record SessaoResponseDTO(Integer id,
                                Integer idFilme,
                                Integer idSala,
                                Instant horarioInicio,
                                Instant horarioFim,
                                float preco) {
}
