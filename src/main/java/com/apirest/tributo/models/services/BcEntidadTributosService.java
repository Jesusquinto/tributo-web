package com.apirest.tributo.models.services;

import com.apirest.tributo.models.dao.BcEntidadTributosRepository;
import com.apirest.tributo.models.entity.BcEntidadTributos;

import java.util.Date;
import java.util.List;

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
public class BcEntidadTributosService implements IBcEntidadTributoService {

 private final Logger logger = LoggerFactory.getLogger(BcEntidadTributosService.class);
	
  @Autowired
  private BcEntidadTributosRepository bcEntidadTributosRepository;

  public List<BcEntidadTributos> findAllBcEntidadTributos() {
    return bcEntidadTributosRepository.findAll();
  }

  public List<BcEntidadTributos> findByIdEntidad(int IdEntidad) {
    return bcEntidadTributosRepository.findByIdEntidad(IdEntidad);
  }

  public BcEntidadTributos saveBcEntidadTributo(BcEntidadTributos bcEntidadTributos) {
    if (bcEntidadTributos.getFechacreacion() == null) {
      bcEntidadTributos.setFechacreacion(new Date());

     logger.info("Parametro Tributo: "+bcEntidadTributos.getParametroTributo());
    }
    return bcEntidadTributosRepository.save(bcEntidadTributos);
  }
  
  public void deleteTributo(int idEntTributo) {
	  bcEntidadTributosRepository.deleteById(idEntTributo);
	  logger.info("El tributo: " +idEntTributo + "ha sido eliminado");
  }

  @Override
  @Transactional
  public BcEntidadTributos findById(int id) {
	return bcEntidadTributosRepository.findById(id).orElse(null);
  }
  
  public List<BcEntidadTributos> findByIdEntidadTributo(Integer idEntidad, Integer idTributo) {
	  return bcEntidadTributosRepository.findByIdEntidadTributo(idEntidad, idTributo);
  }
}
