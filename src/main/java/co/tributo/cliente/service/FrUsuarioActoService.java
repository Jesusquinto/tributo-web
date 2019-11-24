/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.tributo.cliente.service;



import co.tributo.cliente.model.FrUsuarioActo;
import co.tributo.cliente.repository.FrUsuarioActoRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Ricardo
 */
@Service
@Transactional
public class FrUsuarioActoService {
    
            private final Logger logger = LoggerFactory.getLogger(FrUsuarioActoService.class);    

    
    @Autowired
    private FrUsuarioActoRepository frUsuarioActoRepository;
    
    
    public List<FrUsuarioActo> findAllFrUsuarioActo(){
        return frUsuarioActoRepository.findAll();
    }
    
    public Optional<FrUsuarioActo> findOneFrUsuarioActo(int id){
        return frUsuarioActoRepository.findById(id);
    }
    
    
    public FrUsuarioActo findById(int id){
        return frUsuarioActoRepository.getOne(id);

    }
    
    public FrUsuarioActo saveFrUsuarioActo(FrUsuarioActo frUsuarioActo) {
        
        return frUsuarioActoRepository.save(frUsuarioActo);
        
    }
    
    //Actualizar Usuario
    public FrUsuarioActo actualizarFrUsuarioActo(Integer id , FrUsuarioActo frUsuarioActo) {
        
        if (frUsuarioActoRepository.findById(id)==null ){
           
             //Sacar una excepción
            
        }else {
            return frUsuarioActoRepository.save(frUsuarioActo);
        }
        
        return frUsuarioActo;
        
    }
    
    /**
     *
     * @param idUsuario
     * @return lista de Actos del Usuario
     */
    public List<FrUsuarioActo> listarByIdUsuario(Long idUsuario){
        return frUsuarioActoRepository.listarByIdUsuario(idUsuario);
    }
    
    
     /**
     *
     * @param idUsuario
     * @param idEntidadContratantes
     * @param idEntidad
     * @param idActo
     * @param estado
     * @return filtro por todos los criterios Colocar 0 ó '0' se no se requiere en el filtro
     */
    public List<FrUsuarioActo> filterByVarios(Long idUsuario,int idEntidadContratantes, int idEntidad, int idActo, String estado   ){
        return frUsuarioActoRepository.filterByVarios(idUsuario, idEntidadContratantes, idEntidad, idActo, estado);
    }
    
    
    
    
    
    
}
