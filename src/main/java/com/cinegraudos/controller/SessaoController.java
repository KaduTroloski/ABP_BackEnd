package com.cinegraudos.controller;

import com.cinegraudos.domain.dto.SessaoRequestDTO;
import com.cinegraudos.domain.dto.SessaoResponseDTO;
import com.cinegraudos.domain.dto.SessaoUpdateDTO;
import com.cinegraudos.service.SessaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sessoes")
public class SessaoController {
    private final SessaoService sessaoService;

    public SessaoController(SessaoService sessaoService) {
        this.sessaoService = sessaoService;
    }

    @PostMapping
    public ResponseEntity<List<SessaoResponseDTO>> gerarSessoes(@RequestBody SessaoRequestDTO dto) {
        List<SessaoResponseDTO> sessoes = sessaoService.generateSessions(dto);
        return ResponseEntity.ok(sessoes);
    }

    @GetMapping
    public ResponseEntity<List<SessaoResponseDTO>> findAll() {
        return ResponseEntity.ok(sessaoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SessaoResponseDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(sessaoService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Integer id, @RequestBody SessaoUpdateDTO dto) {
        sessaoService.update(id, dto);
        return ResponseEntity.ok("Session updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        sessaoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
