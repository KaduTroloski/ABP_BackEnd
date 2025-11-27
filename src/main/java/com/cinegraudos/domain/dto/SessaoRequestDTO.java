package com.cinegraudos.domain.dto;

import java.time.LocalDate;

public record SessaoRequestDTO(Integer idSala,
                               Integer idFilme,
                               Integer intervalo,
                               LocalDate data,
                               float preco) {
}
