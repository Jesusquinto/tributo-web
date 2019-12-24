/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.tributo.cliente.service;


import co.tributo.cliente.model.BcEntidadTributos;
import co.tributo.cliente.repository.BcEntidadTributosRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class BcEntidadTributosService {
   
    @Autowired
    private BcEntidadTributosRepository bcEntidadTributosRepository;
    
    public List<BcEntidadTributos> findAllBcEntidadTributos() {
        return bcEntidadTributosRepository.findAll();
    }
    
    
    public List<BcEntidadTributos> findByIdEntidad(int IdEntidad) {
        return bcEntidadTributosRepository.findByIdEntidad(IdEntidad);
    }
    public BcEntidadTributos findById(int id) {
        return bcEntidadTributosRepository.findById(id);
    }
    
    
    
}
