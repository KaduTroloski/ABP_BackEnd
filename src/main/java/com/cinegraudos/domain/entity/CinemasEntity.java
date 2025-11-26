package com.cinegraudos.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cinemas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CinemasEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_cinemas;

    private String nome;

    @ManyToOne()
    @JoinColumn(name = "id_localizacao")
    private LocalizacoesEntity localizacao;
}
