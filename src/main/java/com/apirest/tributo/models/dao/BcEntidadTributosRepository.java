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

import com.apirest.tributo.models.entity.BcEntidadTributos;

/**
 *
 * @author Ricardo
 */
public interface BcEntidadTributosRepository extends JpaRepository<BcEntidadTributos, Integer> {

  @Query("SELECT b FROM BcEntidadTributos b WHERE b.identidad.idEntidad = :idEntidad")
  List<BcEntidadTributos> findByIdEntidad(@Param("idEntidad") int idEntidad);
  
  @Query("SELECT b FROM BcEntidadTributos b WHERE b.identidad.idEntidad = :idEntidad and b.idtributo.idTributo = :idTributo")
  List<BcEntidadTributos> findByIdEntidadTributo(@Param("idEntidad") Integer idEntidad, @Param("idTributo") Integer idTributo);
    
}
