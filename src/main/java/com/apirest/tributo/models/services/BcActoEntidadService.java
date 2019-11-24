/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apirest.tributo.models.services;

import com.apirest.tributo.models.dao.BcActoEntidadRepository;
import com.apirest.tributo.models.entity.BcActoEntidad;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class BcActoEntidadService implements IBcActoEntidadService {

  private final Logger logger = LoggerFactory.getLogger(BcActoEntidadService.class);
  @Autowired
  private BcActoEntidadRepository bcActoEntidadRepository;


  public List<BcActoEntidad> findAllBcActoEntidad() {
    return bcActoEntidadRepository.findAll();
  }

  
  public List<BcActoEntidad> findByIdEntidad(int idEntidad) {
    return bcActoEntidadRepository.findByIdEntidad(idEntidad);
  }


  public BcActoEntidad saveBcActoEntidad(BcActoEntidad bcActoEntidad) {
	if(bcActoEntidad.getCodigo() != null) {	
		logger.info("Codigo acto entidad: "+bcActoEntidad.getCodigo());
	}
    return bcActoEntidadRepository.save(bcActoEntidad);
  }


  @Override
  @Transactional
  public BcActoEntidad findById(int id) {
	return bcActoEntidadRepository.findById(id).orElse(null);
  }
  
  public void deleteActoEntidad(Integer idActoEntidad) {
	  bcActoEntidadRepository.deleteById(idActoEntidad);
	  logger.info("El Trámite: " + idActoEntidad + " ha sido removido");
  }

}
