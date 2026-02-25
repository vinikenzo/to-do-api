package com.example.demo.tarefa;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosCadastroTarefa(

        @NotBlank
        String titulo,

        String descricao

) {
}
