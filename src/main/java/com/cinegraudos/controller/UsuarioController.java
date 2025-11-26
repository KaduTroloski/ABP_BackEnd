package com.cinegraudos.controller;

import com.cinegraudos.domain.dto.UsuarioResponseDTO;
import com.cinegraudos.service.UsuarioService;
import com.cinegraudos.domain.dto.UsuarioRequestDTO;
import com.cinegraudos.domain.entity.UsuariosEntity;
import com.cinegraudos.domain.mapper.UsuarioMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody UsuarioRequestDTO dto) {
        UsuariosEntity usuario = UsuarioMapper.toEntity(dto);
        UsuariosEntity salvo = usuarioService.create(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body("User created with success");
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> list() {
        List<UsuariosEntity> usuarios = usuarioService.listAll();
        List<UsuarioResponseDTO> dtos = usuarios.stream()
                .map(UsuarioMapper::toDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> findById(@PathVariable Integer id) {
        UsuariosEntity usuario = usuarioService.findById(id);
        return ResponseEntity.ok(UsuarioMapper.toDTO(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Integer id, @RequestBody UsuarioRequestDTO dto) {
        usuarioService.update(id, dto);
        return ResponseEntity.ok("User updated with success");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        usuarioService.delete(id);
        return ResponseEntity.ok("User deleted with success");    }
}