package com.example.demo.controller;

import com.example.demo.tarefa.DadosCadastroTarefa;
import com.example.demo.tarefa.DadosDetalhamentoTarefa;
import com.example.demo.tarefa.TarefaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefaController {

    private TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @GetMapping
    public ResponseEntity listarTarefas(){
        var tarefas = tarefaService.listarTarefas();

        return ResponseEntity.ok(tarefas);
    }

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarTarefa(@RequestBody @Valid DadosCadastroTarefa dados, UriComponentsBuilder uriComponentsBuilder){
        var tarefa = tarefaService.cadastrarTarefa(dados);
        var uri = uriComponentsBuilder.path("/tarefas/{id}").buildAndExpand(tarefa.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoTarefa(tarefa));

    }


}
