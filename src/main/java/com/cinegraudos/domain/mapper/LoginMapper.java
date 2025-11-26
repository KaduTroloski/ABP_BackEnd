package com.cinegraudos.domain.mapper;

import com.cinegraudos.domain.dto.LoginRequestDTO;
import com.cinegraudos.domain.dto.LoginResponseDTO;
import com.cinegraudos.domain.entity.UsuariosEntity;

public class LoginMapper {
    public static UsuariosEntity toEntity(LoginRequestDTO dto) {
        UsuariosEntity usuario = new UsuariosEntity();
        usuario.setEmail(dto.email());
        usuario.setSenha(dto.senha());
        return usuario;
    }

    public static LoginResponseDTO toDTO(String jwt) {
        return new LoginResponseDTO(jwt);
    }
}
