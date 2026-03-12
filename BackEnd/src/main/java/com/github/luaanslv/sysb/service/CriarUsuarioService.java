package com.github.luaanslv.sysb.service;


import com.github.luaanslv.sysb.dto.CadastroDto;
import com.github.luaanslv.sysb.model.entitiy.Permissao;
import com.github.luaanslv.sysb.model.entitiy.Usuario;
import com.github.luaanslv.sysb.repository.PermissaoRepositorio;
import com.github.luaanslv.sysb.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class CriarUsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PermissaoRepositorio permissaoRepositorio;

    private PasswordEncoder passwordEncoder;




    public Usuario criarUsuario(CadastroDto dto) {
        if (usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Ja existe um usuário com este email");
        }

        if (!dto.getSenha().equals(dto.getConfirmacaoSenha())) {
            throw new RuntimeException("As senhas não coincidem");
        }

        Permissao permissao = permissaoRepositorio.findByNomePerfil("ADMIN")
                .orElseThrow(() -> new RuntimeException("Erro interno: Permissão padrão não encontrada"));

        Usuario novoUsuario = new Usuario();
        novoUsuario.setEmail(dto.getEmail());
        novoUsuario.setDtCadastro(LocalDateTime.now());
        novoUsuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        novoUsuario.setCpf(dto.getCpf());
        novoUsuario.setNome(dto.getNome());
        novoUsuario.setAtiva(true);
        novoUsuario.setTentativasLogin(0);
        novoUsuario.setBloqueadoAte(3);
        novoUsuario.setPermissao(permissao);

        return usuarioRepository.save(novoUsuario);
    }

    public boolean autenticar (String email, String senhaDigitada){
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow( () -> new RuntimeException("Usuario não encontrado") );

        return passwordEncoder.matches(senhaDigitada, usuario.getSenha());
    }


}
