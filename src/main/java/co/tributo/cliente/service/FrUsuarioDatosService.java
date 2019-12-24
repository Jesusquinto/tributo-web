/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.tributo.cliente.service;


import co.tributo.cliente.model.FrUsuarioDatos;
import co.tributo.cliente.repository.FrUsuarioDatosRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Ricardo
 */
public class FrUsuarioDatosService {
    
    @Autowired
    private FrUsuarioDatosRepository frUsuarioDatosRepository;
    
    public List<FrUsuarioDatos> findAllFrUsuarioDatos(){
        return frUsuarioDatosRepository.findAll();
    }
    
}
