package com.example.demo.tarefa;

import com.example.demo.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@AllArgsConstructor

@Table(name = "tarefas")
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descricao;
    private Boolean concluida;
    private LocalDateTime dataCriacao;
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;


    public Tarefa(DadosCadastroTarefa dados, Usuario usuario) {
        this.titulo = dados.titulo();
        this.descricao = dados.descricao();
        this.concluida = false;
        this.dataCriacao = LocalDateTime.now();
        this.usuario = usuario;
    }

    public Tarefa() {
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Boolean getConcluida() {
        return concluida;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void finalizar() {
        this.concluida = true;
    }

}
