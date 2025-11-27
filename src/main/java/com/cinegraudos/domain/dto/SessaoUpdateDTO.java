package com.cinegraudos.domain.dto;

import java.time.Instant;

public record SessaoUpdateDTO(Float preco,
                              Instant horarioInicio,
                              Instant horarioFim) {
    public boolean hasPreco() { return preco != null; }
    public boolean hasHorarioInicio() { return horarioInicio != null; }
    public boolean hasHorarioFim() { return horarioFim != null; }
}
