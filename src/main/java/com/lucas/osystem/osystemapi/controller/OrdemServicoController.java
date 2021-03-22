/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lucas.osystem.osystemapi.controller;

import com.lucas.osystem.osystemapi.domain.model.OrdemServico;
import com.lucas.osystem.osystemapi.domain.repository.OrdemServicoRepository;
import com.lucas.osystem.osystemapi.domain.service.OrdemServicoDaoServico;
import com.lucas.osystem.osystemapi.model.OrdemServicoDTO;
import com.lucas.osystem.osystemapi.model.OrdemServicoPostDTO;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Lucas
 */
@RestController
@RequestMapping("/os")
public class OrdemServicoController {
    
    @Autowired
    private OrdemServicoDaoServico osService;
    
    @Autowired
    private OrdemServicoRepository osRepository;
    
    @Autowired
    private ModelMapper modelMapper;
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrdemServicoDTO criar(@Valid @RequestBody OrdemServicoPostDTO osPostDTO){
        OrdemServico oS = toEntity(osPostDTO);
        return toModel(osService.criar(oS));
    }
    
    @GetMapping
    public List<OrdemServicoDTO> listarTodos(){
        return toCollectionDTO(osRepository.findAll());
    }
    
    @GetMapping("/{osId}")
    public ResponseEntity<OrdemServicoDTO> buscar(@PathVariable Long osId){
        Optional<OrdemServico> oS = osRepository.findById(osId);
        if(oS.isPresent()){
            OrdemServicoDTO osDto = toModel(oS.get());
            return ResponseEntity.ok(osDto);
        }
        return ResponseEntity.notFound().build();
    }
    
    @PutMapping("/{ordemServicoId}/finalizacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void finalizar(@PathVariable Long ordemServicoId){
        osService.finalizar(ordemServicoId);
    }
    
    //Método para mapear o DTO com Model Mapper
    private OrdemServicoDTO toModel(OrdemServico os){
        return modelMapper.map(os, OrdemServicoDTO.class);
    }
    
    //Método para converter a lista do domain para DTO
    private List<OrdemServicoDTO> toCollectionDTO(List<OrdemServico> ordensServico){
        return ordensServico.stream()
                .map(ordemServico -> toModel(ordemServico))
                .collect(Collectors.toList());
    }
    
    private OrdemServico toEntity(OrdemServicoPostDTO osPostDTO){
        return modelMapper.map(osPostDTO, OrdemServico.class);
    }
}
