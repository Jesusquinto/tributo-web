/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apirest.tributo.models.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.apirest.tributo.models.entity.BcActoEntidad;

public interface BcActoEntidadRepository extends JpaRepository<BcActoEntidad, Integer> {

  @Query("SELECT b FROM BcActoEntidad b WHERE b.fkBcEntidad.idEntidad = :idEntidad")
  List<BcActoEntidad> findByIdEntidad(@Param("idEntidad") Integer idEntidad);

}
