/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apirest.tributo.models.services;

import com.apirest.tributo.models.dao.FrUsuarioRepository;
import com.apirest.tributo.models.entity.FrUsuario;

import java.util.Date;
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
public class FrUsuarioService {

  @Autowired
  private FrUsuarioRepository frUsuarioRepository;

  public List<FrUsuario> findAllFrUsuario() {
    return frUsuarioRepository.findAll();
  }

  public Optional<FrUsuario> findOneFrUsuario(int id) {
    return frUsuarioRepository.findById(id);
  }

  public FrUsuario saveFrUsuario(FrUsuario frUsuario) {
    frUsuario.setFechacreacion(new Date());
    return frUsuarioRepository.save(frUsuario);

  }

  //Actualizar Usuario
  public FrUsuario actualizarFrUsuario(Integer id, FrUsuario frUsuario) {

    if (frUsuarioRepository.findById(id) == null) {

      //Sacar una excepción
    } else {
      return frUsuarioRepository.save(frUsuario);
    }

    return frUsuario;

  }

}
