package com.cinegraudos.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "localizacoes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocalizacoesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_localizacoes;

    private char cep;

    private String cidade;

    private String bairro;

    private String rua;

    private Integer numero;

    private char uf;
}
