package com.apirest.tributo.models.services;

import com.apirest.tributo.models.dao.BcContratantesRepository;
import com.apirest.tributo.models.dao.BcEntidadContratantesRepository;
import com.apirest.tributo.models.entity.BcContratantes;
import com.apirest.tributo.models.entity.BcEntidadContratantes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BcContratantesService {

  @Autowired
  private BcContratantesRepository bcContratantesRepository;
  
  @Autowired
  private BcEntidadContratantesRepository bcEntidadContratantesRepository;

  public List<BcContratantes> findAllBcContratantes() {
    return bcContratantesRepository.findAll();
  }
  
  public List<BcEntidadContratantes> findContratanteByEntidad(Integer idEntidad){
    return bcEntidadContratantesRepository.findAllByIdEntidad(idEntidad);
  }

}
