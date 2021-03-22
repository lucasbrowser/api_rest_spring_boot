/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lucas.osystem.osystemapi.domain.repository;

import com.lucas.osystem.osystemapi.domain.model.OrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lucas
 */
@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long>{
    
}
