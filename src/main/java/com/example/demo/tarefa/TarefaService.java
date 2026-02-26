package com.example.demo.tarefa;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    public List<DadosDetalhamentoTarefa> listarTarefas(){
        List<Tarefa> lista = tarefaRepository.findAll();
        var tarefas = lista.stream()
                .map(t -> new DadosDetalhamentoTarefa(t.getId(), t.getTitulo(), t.getDescricao(), t.getConcluida(), t.getDataCriacao()))
                .collect(Collectors.toList());
        return tarefas;
    }

    public DadosDetalhamentoTarefa listarTarefa(Long id){
        var tarefa = tarefaRepository.getReferenceById(id);
        return new DadosDetalhamentoTarefa(tarefa);
    }

    public Tarefa cadastrarTarefa(@Valid DadosCadastroTarefa dados){
        var tarefa = new Tarefa(dados);
        tarefaRepository.save(tarefa);
        return tarefa;
    }

    public Page<DadosDetalhamentoTarefa> listarTarefasPendentes(@PageableDefault Pageable paginacao){
        return tarefaRepository.findAllByConcluidaFalse(paginacao)
                .map(DadosDetalhamentoTarefa::new);

    }

    public Page<DadosDetalhamentoTarefa> listarTarefasConcluidas(@PageableDefault Pageable paginacao){
        return tarefaRepository.findAllByConcluidaTrue(paginacao)
                .map(DadosDetalhamentoTarefa::new);
    }

    public void finalizarTarefa(Long id){
        Tarefa tarefa = tarefaRepository.getReferenceById(id);
        tarefa.finalizar();
    }

    public void excluirTarefa(Long id){
        tarefaRepository.deleteById(id);
    }





}
