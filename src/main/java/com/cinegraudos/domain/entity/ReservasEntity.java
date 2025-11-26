package com.cinegraudos.domain.entity;

import jakarta.persistence.*;
import com.cinegraudos.domain.entity.UsuariosEntity;
import com.cinegraudos.domain.entity.SessoesEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sessoes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservasEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_reservas;

    private String status;

    @ManyToOne()
    @JoinColumn(name = "id_usuario")
    private UsuariosEntity usuario;

    @ManyToOne()
    @JoinColumn(name = "id_sessao")
    private  SessoesEntity sessao;
}
