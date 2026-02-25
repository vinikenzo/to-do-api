package com.example.demo.tarefa;

import java.time.LocalDateTime;

public record DadosDetalhamentoTarefa(
        Long id,
        String titulo,
        String descricao,
        Boolean concluida,
        LocalDateTime dataCriacao
) {
    public DadosDetalhamentoTarefa(Tarefa tarefa) {
        this(tarefa.getId(), tarefa.getTitulo(), tarefa.getDescricao(), tarefa.getConcluida(), tarefa.getDataCriacao());
    }
}
