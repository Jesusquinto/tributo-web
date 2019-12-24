/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.tributo.cliente.service;



import co.tributo.cliente.model.BcEntidad;
import co.tributo.cliente.model.FrUsuarioActo;
import co.tributo.cliente.repository.BcEntidadRepository;
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
public class BcEntidadService {
    
    @Autowired
    private BcEntidadRepository bcEntidadRepository;
    
    public List<BcEntidad> findAllBcEntidad() {
        return  bcEntidadRepository.findAll();
    }
        
    public Optional<BcEntidad> findOneBcEntidad(int id) {
        return  bcEntidadRepository.findById(id);
    }
    
    /**
     *
     * @param identidad
     * @return estadísticas Globales de la Entidad
     */
    public List<Object> getEstadisticas(int identidad) {
        return  bcEntidadRepository.getEstadisticas(identidad);
    }
    
    /**
     *
     * @param identidad
     * @return TotalContratantes, TotalTributos, TotalActos, TotalUsuarios
     */
    public List<Object> getGlobales(int identidad) {
        return  bcEntidadRepository.getGlobal(identidad);
    }
    
    
    
    
    /**
    *
    * @author Ricardo
    * @param id
    * @param bcEntidad
    * @return 
    */
    public BcEntidad actualizarBcEntidad(Integer id , BcEntidad bcEntidad) {
        
        if (bcEntidadRepository.findById(id) == null ){
           
             //Sacar una excepción
            
        }else {
            return bcEntidadRepository.save(bcEntidad);
        }
        
        return bcEntidad;
        
    }
    
    public List<BcEntidad> filterByVarios(String departamento,String municipio, String nombre ){
        return bcEntidadRepository.filterByVarios(departamento, municipio, nombre);
    }
    
    
}
