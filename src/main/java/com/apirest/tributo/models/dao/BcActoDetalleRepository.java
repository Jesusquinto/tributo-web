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

import com.apirest.tributo.models.entity.BcActoDetalle;

/**
 *
 * @author Ricardo
 */
public interface BcActoDetalleRepository extends JpaRepository<BcActoDetalle, Integer> {

  /**
   *
   * @param identidad
   * @param idacto
   * @return Detalles por Id del Acto y Entidad
   */
  //@Query(value ="SELECT * FROM bc_acto_detalle where fk_tipo_acto= :idacto", nativeQuery = true )
  @Query("SELECT b FROM BcActoDetalle b where b.fkEntidadTributo.identidad.idEntidad = :identidad AND b.fkTipoActo.idActo = :idacto")
  List<BcActoDetalle> findByIdActo(@Param("identidad") Integer identidad, @Param("idacto") Integer idacto);

}
