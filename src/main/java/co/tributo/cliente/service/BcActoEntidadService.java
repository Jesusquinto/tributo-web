/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.tributo.cliente.service;

import co.tributo.cliente.model.BcActoEntidad;
import co.tributo.cliente.model.BcEntidad;
import co.tributo.cliente.repository.BcActoEntidadRepository;
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
public class BcActoEntidadService {
    
    @Autowired
    private BcActoEntidadRepository bcActoEntidadRepository;
    
    
    /**
    *
    * @author Ricardo
     * @return 
    */
    public List<BcActoEntidad> findAllBcActoEntidad(){
        return bcActoEntidadRepository.findAll();
    }
    
    /**
    *
    * @author Ricardo
     * @param idEntidad
     * @return 
    */
    public List<BcActoEntidad> findByIdEntidad(int idEntidad){
        return bcActoEntidadRepository.findByIdEntidad(idEntidad);
    }
    
    
    public Optional<BcActoEntidad> findById(int id){
        return bcActoEntidadRepository.findById(id);
    }
    
    /**
    *
    * @author Ricardo
    * @param bcActoEntidad Una BcActoEntidad
    * @return 
    */
       
    public BcActoEntidad saveBcActoEntidad(BcActoEntidad bcActoEntidad) {
         return bcActoEntidadRepository.save(bcActoEntidad);
        
    }
    
    
    
    
    /**
    *
    * @author Ricardo
    * @param id 
    * @param bcActoEntidad Una BcActoEntidad
    * @return 
    */
    public BcActoEntidad actualizarBcActoEntidad(Integer id , BcActoEntidad bcActoEntidad) {
        
        if (bcActoEntidadRepository.findById(id) == null ){
           
             //Sacar una excepción
            
        }else {
            return bcActoEntidadRepository.save(bcActoEntidad);
        }
        
        return bcActoEntidad;
        
      
    }
    
    
    public List<BcActoEntidad> filterByVarios(String codigo,String nombre, Integer idEntidad ){
        return bcActoEntidadRepository.filterByVarios(codigo, nombre, idEntidad);
    }
    
}