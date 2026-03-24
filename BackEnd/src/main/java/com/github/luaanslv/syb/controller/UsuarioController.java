package com.github.luaanslv.syb.controller;


import com.github.luaanslv.syb.dto.CadastroDto;
import com.github.luaanslv.syb.dto.LoginDto;
import com.github.luaanslv.syb.service.CriarUsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class UsuarioController {

    private final CriarUsuarioService criarUsuarioService;

    public UsuarioController(CriarUsuarioService criarUsuarioService) {
        this.criarUsuarioService = criarUsuarioService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@Valid @RequestBody CadastroDto dto){
        try{
            criarUsuarioService.criarUsuario(dto);
            return ResponseEntity.ok(Collections.singletonMap("mensagem", "Usuario cadastrado com sucesso"));
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(Collections.singletonMap("erro", e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login (@Valid @RequestBody LoginDto dto){
        try {
            boolean senhaCorreta = criarUsuarioService.autenticar(dto.getEmail(), dto.getSenha());

            if (senhaCorreta){
                return ResponseEntity.ok(Collections.singletonMap("mensagem", "Login realizado com sucesso"));
            } else {
                return ResponseEntity.status(401).body(Collections.singletonMap("erro", "Email ou senha incorreta"));
            }
        } catch (RuntimeException e) {
            // Captura o "Usuario não encontrado" e envia uma mensagem amigável
            return ResponseEntity.status(401).body(Collections.singletonMap("erro", "Email ou senha incorreta"));
        }
    }
}