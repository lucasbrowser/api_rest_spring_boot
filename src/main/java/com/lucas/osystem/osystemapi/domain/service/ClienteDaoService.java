/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lucas.osystem.osystemapi.domain.service;

import com.lucas.osystem.osystemapi.domain.exception.NegocioException;
import com.lucas.osystem.osystemapi.domain.model.Cliente;
import com.lucas.osystem.osystemapi.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Lucas
 */

@Service
public class ClienteDaoService {
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    public Cliente salvar(Cliente cliente){
        Cliente clienteExistente = clienteRepository.findByEmail(cliente.getEmail());
        
        if (clienteExistente != null && !clienteExistente.equals(cliente)){
            throw new NegocioException("Já existe um cliente cadastrado com esse email.");
        }
        return clienteRepository.save(cliente);
    }
    
    public void excluir(Long clienteId){
        clienteRepository.deleteById(clienteId);
    }
    
}
