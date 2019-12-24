/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.tributo.cliente.service;

import co.tributo.cliente.model.BcActo;
import co.tributo.cliente.model.GeDivipo;
import co.tributo.cliente.repository.BcActoRepository;
import co.tributo.cliente.repository.GeDivipoRepository;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Ricardo
 */
@Service
@Transactional
public class GeDivipoService {
    
    @Autowired
    GeDivipoRepository geDivipoRepository;
    

    public List<GeDivipo> findAllGeDivipos(){
        return geDivipoRepository.findAll();
    }
    
    
  
    public Optional<GeDivipo> findAllBcActoByIdGeDivipo(int IdGeDivipo){
        return geDivipoRepository.findById(IdGeDivipo);
    }
    
    
    
    
    
    
    
    
    
    
    
}
