package com.github.luaanslv.sysb.service;

import com.github.luaanslv.sysb.model.entitiy.Usuario;
import com.github.luaanslv.sysb.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserAtividadeService {

    private final UsuarioRepository repository;

    public Usuario buscarPorEmail(String email) {
        return  repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado com email" + email));
    }

    public void salvarDtCadastro(String email, LocalDateTime dtCadastro){
        Usuario usuario = repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Registro de cadastro não encontrado para o email: " + email));

        usuario.setDtCadastro(dtCadastro);
        repository.save(usuario);

    }

    public void salvarUltimoAcesso(String email){
        Usuario usuario = repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Registro de ultimo acesso não encontrado para o email: " + email));

        usuario.setDtUltimoAcesso(LocalDateTime.now());
        repository.save(usuario);
    }



}
