package com.cinegraudos.domain.mapper;

import com.cinegraudos.domain.dto.UsuarioRequestDTO;
import com.cinegraudos.domain.dto.UsuarioResponseDTO;
import com.cinegraudos.domain.entity.FuncoesEntity;
import com.cinegraudos.domain.entity.UsuariosEntity;
import com.cinegraudos.domain.entity.CinemasEntity;

public class UsuarioMapper {
    public static UsuariosEntity toEntity(UsuarioRequestDTO dto) {
        UsuariosEntity usuario = new UsuariosEntity();
        usuario.setNome(dto.nome());
        usuario.setSobrenome(dto.sobrenome());
        usuario.setEmail(dto.email());
        usuario.setSenha(dto.senha());
        FuncoesEntity funcao = new FuncoesEntity();
        funcao.setId_funcoes(dto.idFuncao());
        usuario.setFuncao(funcao);
        CinemasEntity cinema = new CinemasEntity();
        cinema.setId_cinemas(dto.idCinema());
        usuario.setCinema(cinema);

        return usuario;
    }

    public static UsuarioResponseDTO toDTO(UsuariosEntity usuario) {
        return new UsuarioResponseDTO(
                usuario.getId_usuarios(),
                usuario.getNome(),
                usuario.getSobrenome(),
                usuario.getEmail(),
                usuario.getCinema().getId_cinemas(),
                usuario.getAcessos(),
                usuario.getFuncao().getId_funcoes()
        );
    }
}
