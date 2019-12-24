/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.tributo.cliente.service;

import co.tributo.cliente.model.BcActo;
import co.tributo.cliente.repository.BcActoRepository;
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
public class BcActoService {
    
    @Autowired
    BcActoRepository bcActoRepository;
    
    /**
    *
    * @author Ricardo
    * Lista de Actos Administrativos
     * @return BcActo
    */
    public List<BcActo> findAllBcActo(){
        return bcActoRepository.findAll();
    }
    
    
    /**
    *
    * @author Ricardo
    * Lista de Actos Administrativos
     * @param IdActo
     * @return BcActo
    */
    public Optional<BcActo> findAllBcActoByIdActo(int IdActo){
        return bcActoRepository.findById(IdActo);
    }
    
    
    
    
    
    
    
    
    
    
    
}
