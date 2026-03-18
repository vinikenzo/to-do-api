package com.example.demo.usuario;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario cadastrarUsuario(DadosCadastroUsuario dados){
        var usuario = new Usuario(dados);
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
}
