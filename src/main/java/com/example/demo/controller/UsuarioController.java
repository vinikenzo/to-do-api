package com.example.demo.controller;

import com.example.demo.usuario.*;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody DadosCadastroUsuario dados, UriComponentsBuilder uriComponentsBuilder){
        var usuario = usuarioService.cadastrarUsuario(dados);
        var uri = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoUsuario(usuario));
    }

    @GetMapping
    public ResponseEntity listarTodos(){
        var usuarios = usuarioService.listarUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity listarPorId(@PathVariable Long id){
        var usuario = usuarioService.listarPorId(id);
        var dadosUsuario = new DadosDetalhamentoUsuario(usuario);
        return ResponseEntity.ok(dadosUsuario);
    }

    @PutMapping()
    @Transactional
    public ResponseEntity atualizar(@RequestBody DadosAtualizacaoUsuario dados){
        var usuario = usuarioService.atualizarUsuario(dados);
        return ResponseEntity.ok(new DadosDetalhamentoUsuario(usuario));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        usuarioService.excluirUsuario(id);
        return ResponseEntity.noContent().build();
    }

}
