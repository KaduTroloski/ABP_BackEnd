package com.cinegraudos.domain.dto;

public record UsuarioResponseDTO(Integer id,
                                 String nome,
                                 String sobrenome,
                                 String email,
                                 Integer idCinema,
                                 Integer idFuncao,
                                 Integer acessos) {}
