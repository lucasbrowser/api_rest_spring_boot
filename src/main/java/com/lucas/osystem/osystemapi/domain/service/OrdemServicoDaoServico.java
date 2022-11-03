/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lucas.osystem.osystemapi.domain.service;

import com.lucas.osystem.osystemapi.domain.exception.EntidadeNaoEncontradaException;
import com.lucas.osystem.osystemapi.domain.exception.NegocioException;
import com.lucas.osystem.osystemapi.domain.model.Cliente;
import com.lucas.osystem.osystemapi.domain.model.Comentario;
import com.lucas.osystem.osystemapi.domain.model.OrdemServico;
import com.lucas.osystem.osystemapi.domain.model.StatusOrdemServico;
import com.lucas.osystem.osystemapi.domain.repository.ClienteRepository;
import com.lucas.osystem.osystemapi.domain.repository.ComentarioRepository;
import com.lucas.osystem.osystemapi.domain.repository.OrdemServicoRepository;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Lucas
 */
@Service
public class OrdemServicoDaoServico {
    @Autowired
    private OrdemServicoRepository osRepository;
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
    private ComentarioRepository comentarioRepository;
    
    public OrdemServico criar(OrdemServico ordemServico){
        Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId())
                .orElseThrow(() -> new NegocioException("Cliente não encontrado!"));
        ordemServico.setCliente(cliente);
        ordemServico.setStatus(StatusOrdemServico.ABERTA);
        ordemServico.setDataAbertura(OffsetDateTime.now());
        return osRepository.save(ordemServico);
    }
    
    public void finalizar(Long ordemServicoId){
        OrdemServico ordemServico = osRepository.findById(ordemServicoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Ordem de Serviço não encontrado!"));
        
        ordemServico.finalizar();
        
        osRepository.save(ordemServico);
    }
    
    public Comentario adicionarComentario(Long ordemServicoId, String descricao){
        OrdemServico ordemServico = osRepository.findById(ordemServicoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Ordem de Serviço não encontrado!"));
        Comentario comentario = new Comentario();
        comentario.setDataEnvio(OffsetDateTime.now());
        comentario.setDescricao(descricao);
        comentario.setOrdemServico(ordemServico);
        return comentarioRepository.save(comentario);
    }
    
}
