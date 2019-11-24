/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.tributo.cliente.service;


import co.tributo.cliente.controller.FrUsuarioActoRest;
import co.tributo.cliente.model.FrUsuario;
import co.tributo.cliente.repository.FrUsuarioRepository;
import java.util.Date;
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
public class FrUsuarioService {

    @Autowired
    private FrUsuarioRepository frUsuarioRepository;
    
    public List<FrUsuario> findAllFrUsuario(){
        return frUsuarioRepository.findAll();
    }
    
    
    public Optional<FrUsuario> findOneFrUsuario(int id){
        return frUsuarioRepository.findById(id);
    }
    
    public FrUsuario findByEmail(String email){
        return frUsuarioRepository.getFrUsuarioByEmail(email);
    }
    
    public void checkVerifiedAccount(){
        frUsuarioRepository.checkVerifiedAccount();
    }
    
    
    public FrUsuario saveFrUsuario(FrUsuario frUsuario) {
    	if(frUsuario.getFechacreacion() == null) {
            frUsuario.setFechacreacion(new Date());	
    	}
    	
        return frUsuarioRepository.save(frUsuario);
        
        
    }
    
    public FrUsuario findByCode(String verificaionCode){
        return frUsuarioRepository.getFrUsuarioByVerificaionCode(verificaionCode);
    }
    
    
    
    
    //Actualizar Usuario
    public FrUsuario actualizarFrUsuario(Integer id , FrUsuario frUsuario) {
        
        if (frUsuarioRepository.findById(id)==null ){
           
             //Sacar una excepción
            
        }else {
            return frUsuarioRepository.save(frUsuario);
        }
        
        return frUsuario;
        
    }
    
    
    
    
}
