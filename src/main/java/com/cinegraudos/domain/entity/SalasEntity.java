package com.cinegraudos.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import com.cinegraudos.domain.entity.CinemasEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "salas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalasEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_salas;

    private Integer numero;

    @ManyToOne()
    @JoinColumn(name = "id_cinema")
    private CinemasEntity cinema;

    private String layout;

    private Integer capacidade;

}
