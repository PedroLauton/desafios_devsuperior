package com.devsuperior.desafio.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_participante")
public class Participante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;

    @Column(unique = true)
    private String email;

    @ManyToMany(mappedBy = "participantes")
    private List<Atividade> atividades;

}
