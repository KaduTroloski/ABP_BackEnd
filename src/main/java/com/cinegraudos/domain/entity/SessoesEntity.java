package com.cinegraudos.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.cinegraudos.domain.entity.SalasEntity;
import com.cinegraudos.domain.entity.FilmesEntity;
import java.time.Instant;


@Entity
@Table(name = "sessoes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SessoesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_sessoes;

    private Instant horario_inicio;

    private Instant horario_fim;

    private float preco_base;

    @ManyToOne()
    @JoinColumn(name = "id_sala")
    private SalasEntity sala;

    @ManyToOne()
    @JoinColumn(name = "id_filme")
    private FilmesEntity filme;

}
