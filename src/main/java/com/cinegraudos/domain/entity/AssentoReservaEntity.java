package com.cinegraudos.domain.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import com.cinegraudos.domain.entity.ReservasEntity;
import com.cinegraudos.domain.entity.AssentosEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "assento_reserva")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssentoReservaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_assento_reserva;

    private float preco_final;

    @ManyToOne()
    @JoinColumn(name = "id_reserva")
    private  ReservasEntity reserva;

    @ManyToOne()
    @JoinColumn(name = "id_assento")
    private AssentosEntity assento;
}
