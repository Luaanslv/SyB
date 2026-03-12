package com.github.luaanslv.sysb.controller;


import com.github.luaanslv.sysb.dto.CadastroDto;
import com.github.luaanslv.sysb.dto.LoginDto;
import com.github.luaanslv.sysb.service.CriarUsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final CriarUsuarioService criarUsuarioService;

    public UsuarioController(CriarUsuarioService criarUsuarioService) {
        this.criarUsuarioService = criarUsuarioService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrar(@Valid @RequestBody CadastroDto dto){
        try{
            criarUsuarioService.criarUsuario(dto);
            return ResponseEntity.ok("usuario cadastrado com sucesso");
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login (@RequestBody LoginDto dto){
        boolean senhaCorreta = criarUsuarioService.autenticar(dto.getEmail(), dto.getSenha());

        if (senhaCorreta){
            return ResponseEntity.ok("Login realizado com sucesso");
        }else{
            return ResponseEntity.badRequest().body("Senha ou email incorretos");
        }
    }
}
