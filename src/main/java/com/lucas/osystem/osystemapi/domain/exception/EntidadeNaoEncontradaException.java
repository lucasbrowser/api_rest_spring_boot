/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lucas.osystem.osystemapi.domain.exception;

/**
 *
 * @author Lucas
 */
public class EntidadeNaoEncontradaException extends NegocioException{
    
    private static final long serialVersionUID = 1L;
    
    public EntidadeNaoEncontradaException(String message) {
        super(message);
    }
    
}
