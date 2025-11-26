package com.cinegraudos.controller;

import com.cinegraudos.domain.dto.LoginRequestDTO;
import com.cinegraudos.domain.dto.LoginResponseDTO;
import com.cinegraudos.domain.mapper.LoginMapper;
import com.cinegraudos.service.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController{
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO dto) {
        String token = loginService.login(dto);
        return ResponseEntity.ok(LoginMapper.toDTO(token));
    }
}
