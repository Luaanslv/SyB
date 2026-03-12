package com.github.luaanslv.sysb.model.entitiy;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "TB_PERMISSOES")

public class Permissao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPermissao")
    private Integer id;

    @NotBlank(message = "O nome do perfil é obrigatório")
    @Column(name = "nomePerfil", length = 100, nullable = false)
    private String nomePerfil;

    @NotBlank(message = "A descrição é obrigatória")
    @Column(name = "descricao", length = 100, nullable = false)
    private String descricao;

    /* --- VENDAS --- */
    @Builder.Default
    private Boolean podeVender = true;

    @Builder.Default
    private Boolean podeCancelarItens = true;

    @Builder.Default
    private Boolean podeCancelarVenda = true;

    @Builder.Default
    private Boolean podeAplicarDesconto = true;

    @Builder.Default
    private Boolean descontoMaximo = true;

    /* --- PRODUTOS --- */
    @Builder.Default
    private Boolean podeCadastrarProduto = true;

    @Builder.Default
    private Boolean podeEditarProduto = true;

    @Builder.Default
    private Boolean podeExcluirProduto = true;

    /* --- ESTOQUE --- */
    @Builder.Default
    private Boolean podeGerenciarEstoque = true;

    @Builder.Default
    private Boolean podeAjustarEstoque = true;

    /* --- CLIENTES --- */
    @Builder.Default
    private Boolean podeCadastrarCliente = true;

    @Builder.Default
    private Boolean podeEditarCliente = true;

    @Builder.Default
    private Boolean podeVisualizarCliente = true;

    /* --- FINANCEIRO --- */
    @Builder.Default
    private Boolean podeVisualizarRelatorios = true;

    @Builder.Default
    private Boolean podeVisualizarVisualizarFaturamento = true;

    @Builder.Default
    private Boolean podeAbrirCaixa = true;

    @Builder.Default
    private Boolean podeFecharCaixa = true;

    /* --- ADMINISTRATIVO --- */
    @Builder.Default
    private Boolean podeGerenciarUsuarios = true;

    @Builder.Default
    private Boolean podeGerenciarPermissoes = true;

    @Builder.Default
    private Boolean podeAcessarConfigSistema = true;

    @Builder.Default
    private Boolean ativa = true;
}
