package com.example.demo.tarefa;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    public Tarefa cadastrarTarefa(@Valid DadosCadastroTarefa dados){
        var tarefa = new Tarefa(dados);
        tarefaRepository.save(tarefa);
        return tarefa;

    }

}
