package com.example.demo.tarefa;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.Date;

@Entity()
public class Tarefa {
    @Id
    private Long id;
    private String titulo;
    private String descricao;
    private Boolean concluida;
    private LocalDateTime dataCriacao = LocalDateTime.now();



}
