package com.github.luaanslv.sysb.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class CadastroDto {

    @NotBlank(message = "Email é obrigatorio")
    @Email(message = "Email invalido")
    private String email;

    @NotBlank(message = "Nome é obrigatorio")
    private String nome;

    @NotBlank(message = "cpf é obrigatorio")
    @Pattern(regexp = "\\d{11}", message = "O CPF deve conter apenas os 11 dígitos numéricos")
    private String cpf;

    /*  ^ indica o início da verificação;
        (?=.*[a-z]) Garante que tenha pelo menos uma letra minuscula;
        (?=.*[A-Z]) Garante que tenha pelo menos uma letra maiuscula;
        (?=.*\\d) Garante que tenho pelo menos um numero;
        (?=.*[\W_]) Obriga pelo menos um caractere especial;
        (?=\S+$) Não deixa ter espaço;
        .{8,}$ Minimo de 8 caracteres;
     */

    //verificação de padrões
    @NotBlank(message = "Senha é obrigatoria")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[\\W_])(?=\\S+$).{8,}$",
            message = "A senha deve conter: pelo menos 8 caracteres, uma letra maiúscula, uma minúscula, um número e um caractere especial"
    )
    private String senha;

    @NotBlank(message = "Confirmação de senha obrigatoria")
    private String confirmacaoSenha;

}
