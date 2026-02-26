package com.example.demo.tarefa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    Page<Tarefa> findAllByConcluidaFalse(Pageable paginacao);


    Page<Tarefa> findAllByConcluidaTrue(Pageable paginacao);
}
