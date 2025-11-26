package com.cinegraudos.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import com.cinegraudos.domain.entity.SalasEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "assentos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssentosEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_assentos;

    private char fileira;

    private Integer numero;

    private String tipo;

    @ManyToOne
    @JoinColumn(name = "id_sala")
    private SalasEntity sala;
}
