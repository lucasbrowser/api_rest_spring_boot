/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lucas.osystem.osystemapi.model;

import java.math.BigDecimal;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Lucas
 */
public class OrdemServicoPostDTO {
    
    @NotBlank
    private String descricao;
    
    @NotNull
    private BigDecimal preco;
    
    @Valid
    @NotNull
    private ClienteIdPostDTO cliente;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public ClienteIdPostDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteIdPostDTO cliente) {
        this.cliente = cliente;
    }
    
    
    
}
