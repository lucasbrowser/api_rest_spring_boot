/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lucas.osystem.osystemapi.controller;

import com.lucas.osystem.osystemapi.domain.exception.EntidadeNaoEncontradaException;
import com.lucas.osystem.osystemapi.domain.model.Comentario;
import com.lucas.osystem.osystemapi.domain.model.OrdemServico;
import com.lucas.osystem.osystemapi.domain.repository.ComentarioRepository;
import com.lucas.osystem.osystemapi.domain.repository.OrdemServicoRepository;
import com.lucas.osystem.osystemapi.domain.service.OrdemServicoDaoServico;
import com.lucas.osystem.osystemapi.model.ComentarioDTO;
import com.lucas.osystem.osystemapi.model.ComentarioPostDTO;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Lucas
 */
@RestController
@RequestMapping("/os/{osId}/comentarios")
public class ComentarioController {
    
    @Autowired
    private OrdemServicoDaoServico osDaoService;
    
    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private ComentarioRepository comentarioRepository;
    
    @Autowired
    private OrdemServicoRepository osRepository;
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ComentarioDTO adicionar(@PathVariable Long osId,
            @Valid @RequestBody ComentarioPostDTO comentarioPost){
        Comentario comentario = osDaoService.adicionarComentario(osId, comentarioPost.getDescricao());
        return toModel(comentario);
    }
    
    @GetMapping
    public List<ComentarioDTO> listarTodos(@PathVariable Long osId){
        OrdemServico ordemServico = osRepository.findById(osId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Ordem de Serviço não encontrado."));
        return toCollectionDTO(ordemServico.getComentarios());
    }
    
    //Método para mapear o DTO com Model Mapper
    private ComentarioDTO toModel(Comentario comment){
        return modelMapper.map(comment, ComentarioDTO.class);
    }
    
    //Método para converter a lista do domain para DTO
    private List<ComentarioDTO> toCollectionDTO(List<Comentario> comment){
        return comment.stream()
                .map(comentario -> toModel(comentario))
                .collect(Collectors.toList());
    }
    
}
