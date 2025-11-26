package com.cinegraudos.service;

import com.cinegraudos.domain.dto.LoginRequestDTO;
import com.cinegraudos.domain.entity.UsuariosEntity;
import com.cinegraudos.util.SenhaUtil;
import com.cinegraudos.util.JwtUtil;
import com.cinegraudos.repository.UsuariosRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {
    private final UsuariosRepository usuariosRepository;

    public LoginService(UsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }

    public String login(LoginRequestDTO dto) {
        UsuariosEntity usuario = usuariosRepository.findByEmail(dto.email())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (!SenhaUtil.verify(dto.senha(), usuario.getSenha())) {
            throw new IllegalArgumentException("Invalid Login Credentials");
        }

        String role = usuario.getFuncao().getNome();

        return JwtUtil.getToken(usuario.getEmail(), List.of(role));
    }
}
