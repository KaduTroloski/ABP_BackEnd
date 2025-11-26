package com.cinegraudos.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "funcoes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FuncoesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_funcoes;

    private String nome;


}
