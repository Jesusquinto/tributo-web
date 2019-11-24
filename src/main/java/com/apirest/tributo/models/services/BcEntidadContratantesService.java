/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apirest.tributo.models.services;

import com.apirest.tributo.models.dao.BcEntidadContratantesRepository;
import com.apirest.tributo.models.entity.BcEntidadContratantes;

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
public class BcEntidadContratantesService {

  @Autowired
  private BcEntidadContratantesRepository bcEntidadContratantesRepository;

  public List<BcEntidadContratantes> finAllBcEntidadContratantes() {
    return bcEntidadContratantesRepository.findAll();
  }

}
