package com.example.demo.usuario;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final PasswordEncoder encriptador;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder encriptador) {
        this.usuarioRepository = usuarioRepository;
        this.encriptador = encriptador;
    }

    public Usuario cadastrarUsuario(DadosCadastroUsuario dados){
        var senhaCriptografada = encriptador.encode(dados.senha());
        var usuario = new Usuario(dados, senhaCriptografada);
        usuarioRepository.save(usuario);
        return usuario;

    }

    public List<DadosDetalhamentoUsuario> listarUsuarios(){
        var lista = usuarioRepository.findAll();
        var usuarios = lista.stream()
                .map(DadosDetalhamentoUsuario::new)
                .collect(Collectors.toList());
        return usuarios;
    }

    public Usuario listarPorId(Long id){
        var usuario = usuarioRepository.getReferenceById(id);
        return usuario;
    }

    public Usuario atualizarUsuario(DadosAtualizacaoUsuario dados){
        var senhaEncriptada = encriptador.encode(dados.senha());
        var usuario = usuarioRepository.getReferenceById(dados.id());
        usuario.atualizar(dados, senhaEncriptada);
        return usuario;
    }

    public void excluirUsuario(Long id){
        usuarioRepository.deleteById(id);
    }
}
