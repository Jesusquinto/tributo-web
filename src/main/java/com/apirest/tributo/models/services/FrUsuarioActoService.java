/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apirest.tributo.models.services;

import com.apirest.tributo.models.dao.FrUsuarioActoRepository;
import com.apirest.tributo.models.entity.FrUsuarioActo;

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
public class FrUsuarioActoService {

  @Autowired
  private FrUsuarioActoRepository frUsuarioActoRepository;

  public List<FrUsuarioActo> findAllFrUsuarioActo() {
    return frUsuarioActoRepository.findAll();
  }

  public Optional<FrUsuarioActo> findOneFrUsuarioActo(int id) {
    return frUsuarioActoRepository.findById(id);
  }

  public FrUsuarioActo saveFrUsuarioActo(FrUsuarioActo frUsuarioActo) {

    return frUsuarioActoRepository.save(frUsuarioActo);

  }

  //Actualizar Usuario
  public FrUsuarioActo actualizarFrUsuarioActo(Integer id, FrUsuarioActo frUsuarioActo) {

    if (frUsuarioActoRepository.findById(id) == null) {

      //Sacar una excepción
    } else {
      return frUsuarioActoRepository.save(frUsuarioActo);
    }

    return frUsuarioActo;

  }

  /**
   *
   * @param idUsuario
   * @return lista de Actos del Usuario
   */
  public List<FrUsuarioActo> listarByIdUsuario(int idUsuario) {
    return frUsuarioActoRepository.listarByIdUsuario(idUsuario);
  }

  /**
   *
   * @param idUsuario
   * @param idEntidadContratantes
   * @param idEntidad
   * @param idActo
   * @param estado
   * @return filtro por todos los criterios Colocar 0 ó '0' se no se requiere en el filtro
   */
  public List<FrUsuarioActo> filterByVarios(int idUsuario, int idEntidadContratantes, int idEntidad, int idActo, String estado) {
    return frUsuarioActoRepository.filterByVarios(idUsuario, idEntidadContratantes, idEntidad, idActo, estado);
  }

}
