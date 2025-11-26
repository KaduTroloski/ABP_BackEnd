package com.cinegraudos.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "filmes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilmesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_filmes;

    private String nome;

    private String descricao;

    private Integer duracao;
}
