package com.cinegraudos.domain.mapper;

import com.cinegraudos.domain.dto.SessaoRequestDTO;
import com.cinegraudos.domain.dto.SessaoResponseDTO;
import com.cinegraudos.domain.entity.FilmesEntity;
import com.cinegraudos.domain.entity.SalasEntity;
import com.cinegraudos.domain.entity.SessoesEntity;

public class SessaoMapper {
    public static SessoesEntity toEntity(SessaoRequestDTO dto, SalasEntity sala, FilmesEntity filme) {
        SessoesEntity sessao = new SessoesEntity();
        sessao.setSala(sala);
        sessao.setFilme(filme);
        sessao.setPreco_base(dto.preco());

        return sessao;
    }

    public static SessaoResponseDTO toDTO(SessoesEntity sessao) {
        return new SessaoResponseDTO(
                sessao.getIdSessoes(),
                sessao.getFilme().getId_filmes(),
                sessao.getSala().getId_salas(),
                sessao.getHorarioInicio(),
                sessao.getHorarioFim(),
                sessao.getPreco_base()
        );
    }
}
