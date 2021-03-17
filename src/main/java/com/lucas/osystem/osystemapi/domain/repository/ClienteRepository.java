/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lucas.osystem.osystemapi.domain.repository;

import com.lucas.osystem.osystemapi.domain.model.Cliente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lucas
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
    //busca um cliente pelo nome
    List<Cliente> findByNome(String nome);
    //Retorna um cliente pela parte do nome
    List<Cliente> findByNomeContaining(String nome);
    Cliente findByEmail(String email);
    
}
