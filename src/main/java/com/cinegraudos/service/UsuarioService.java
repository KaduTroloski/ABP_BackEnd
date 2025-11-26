package com.cinegraudos.service;

import com.cinegraudos.domain.dto.UsuarioRequestDTO;
import com.cinegraudos.domain.dto.UsuarioResponseDTO;
import com.cinegraudos.domain.mapper.UsuarioMapper;
import com.cinegraudos.util.SenhaUtil;
import com.cinegraudos.domain.entity.UsuariosEntity;
import com.cinegraudos.repository.UsuariosRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuariosRepository usuarioRepository;

    public UsuarioService(UsuariosRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuariosEntity create(UsuariosEntity usuario) {
        String senhaHash = SenhaUtil.createHash(usuario.getSenha());
        usuario.setSenha(senhaHash);
        return usuarioRepository.save(usuario);
    }

    public List<UsuariosEntity> listAll() {
        return usuarioRepository.findAll();
    }

    public UsuariosEntity findById(Integer id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public UsuarioResponseDTO update(Integer id, UsuarioRequestDTO dto) {
        UsuariosEntity usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setSobrenome(dto.sobrenome());
        usuario.setCinema(usuario.getCinema());
        usuario.setFuncao(usuario.getFuncao());

        return UsuarioMapper.toDTO(usuarioRepository.save(usuario));
    }

    public void delete(Integer id) {
        if (!usuarioRepository.existsById(id)) {
            throw new IllegalArgumentException("User not found");
        }
        usuarioRepository.deleteById(id);
    }
}
