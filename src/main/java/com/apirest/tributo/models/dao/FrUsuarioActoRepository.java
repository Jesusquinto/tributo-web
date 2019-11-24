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

import com.apirest.tributo.models.entity.FrUsuarioActo;

/**
 *
 * @author Ricardo
 */
public interface FrUsuarioActoRepository extends JpaRepository<FrUsuarioActo, Integer> {

  @Query("SELECT f FROM FrUsuarioActo f WHERE f.fkUsuario.idUsuario = :idUsuario")
  public List<FrUsuarioActo> listarByIdUsuario(@Param("idUsuario") int idUsuario);

  @Query("SELECT f FROM FrUsuarioActo f WHERE (f.fkUsuario.idUsuario = :idUsuario OR :idUsuario = 0) AND (f.fkEntidadContratantes.idEntidadContratantes = :idEntidadContratantes OR :idEntidadContratantes = 0 ) and (f.fkActoEntidad.fkBcEntidad.idEntidad = :idEntidad OR :idEntidad = 0 ) AND (f.fkActoEntidad.fkBcActo.idActo = :idActo OR :idActo = 0 ) AND (f.estado = :estado OR :estado = '0') ")
  public List<FrUsuarioActo> filterByVarios(@Param("idUsuario") int idUsuario, @Param("idEntidadContratantes") int idEntidadContratantes, @Param("idEntidad") int idEntidad, @Param("idActo") int idActo, @Param("estado") String estado);
}
