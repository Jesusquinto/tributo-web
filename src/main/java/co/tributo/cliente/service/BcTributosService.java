/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.tributo.cliente.service;


import co.tributo.cliente.model.BcTributos;
import co.tributo.cliente.repository.BcTributosRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Ricardo
 */
@Service
@Transactional
public class BcTributosService {
    
    @Autowired
    private BcTributosRepository bcTributosRepository;
    
    public List<BcTributos> findAllBcTributos(){
        return bcTributosRepository.findAll();
    }
    
    
}
