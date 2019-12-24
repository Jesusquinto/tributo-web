/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.tributo.cliente.service;


import co.tributo.cliente.model.BcActoDetalle;
import co.tributo.cliente.repository.BcActoDetalleRepository;
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
public class BcActoDetalleService {
    
  @Autowired
  private BcActoDetalleRepository bcActoDetalleRepository;  
  
  
  public List<BcActoDetalle> findBcActoDetalle(){
      return bcActoDetalleRepository.findAll();
  } 
  
  public List<BcActoDetalle> findByIdActo(Integer identidad, Integer idacto){
      return bcActoDetalleRepository.findByIdActo(identidad, idacto);
  } 
    
}
