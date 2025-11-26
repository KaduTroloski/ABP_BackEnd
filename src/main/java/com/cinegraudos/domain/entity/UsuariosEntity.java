package com.cinegraudos.domain.entity;
import com.cinegraudos.domain.entity.CinemasEntity;
import com.cinegraudos.domain.entity.FuncoesEntity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuariosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_usuarios;

    private String nome;

    private String sobrenome;

    private String senha;

    private String email;

    @ManyToOne
    @JoinColumn(name = "id_cinema")
    private CinemasEntity cinema;

    @ManyToOne
    @JoinColumn(name = "id_funcao")
    private FuncoesEntity funcao;

    private Integer acessos = 0;

}
