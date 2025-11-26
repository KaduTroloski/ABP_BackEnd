package com.cinegraudos.domain.dto;

public record UsuarioRequestDTO(String nome,
                                String sobrenome,
                                String email,
                                String senha,
                                Integer idFuncao,
                                Integer idCinema) {}
