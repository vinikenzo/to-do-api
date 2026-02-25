package com.example.demo.tarefa;

import java.time.LocalDateTime;

public record DadosDetalhamentoTarefa(
        String titulo,
        String descricao,
        Boolean concluida,
        LocalDateTime dataCriacao
) {
}
