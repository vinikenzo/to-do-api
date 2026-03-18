package com.example.demo.controller;

import com.example.demo.tarefa.DadosCadastroTarefa;
import com.example.demo.tarefa.DadosDetalhamentoTarefa;
import com.example.demo.tarefa.TarefaService;
import com.example.demo.usuario.Usuario;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @GetMapping("/{id}")
    public ResponseEntity listarTarefa(@PathVariable Long id){
        var dadosTarefa = tarefaService.listarTarefa(id);
        return ResponseEntity.ok(dadosTarefa);
    }

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarTarefa(@RequestBody @Valid DadosCadastroTarefa dados, UriComponentsBuilder uriComponentsBuilder,@AuthenticationPrincipal Usuario usuario){
        var tarefa = tarefaService.cadastrarTarefa(dados, usuario);
        var uri = uriComponentsBuilder.path("/tarefas/{id}").buildAndExpand(tarefa.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoTarefa(tarefa));

    }

    @GetMapping("/pendentes")
    public ResponseEntity<Page<DadosDetalhamentoTarefa>> listarTarefasPendentes(@PageableDefault Pageable paginacao){
        var tarefas = tarefaService.listarTarefasPendentes(paginacao);
        return ResponseEntity.ok(tarefas);

    }

    @GetMapping("/concluidas")
    public ResponseEntity<Page<DadosDetalhamentoTarefa>> listarTarefasConcluidas(@PageableDefault Pageable paginacao){
        var tarefas = tarefaService.listarTarefasConcluidas(paginacao);
        return ResponseEntity.ok(tarefas);
    }

    @PatchMapping("/{id}/concluir")
    @Transactional
    public ResponseEntity concluirTarefa(@PathVariable Long id){
        tarefaService.finalizarTarefa(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirTarefa(@PathVariable Long id){
        tarefaService.excluirTarefa(id);
        return ResponseEntity.noContent().build();
    }






}
