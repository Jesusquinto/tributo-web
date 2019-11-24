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

import com.apirest.tributo.models.entity.BcActo;

public interface BcActoRepository extends JpaRepository<BcActo, Integer> {
	
	@Query(value = "SELECT * FROM bc_acto WHERE estado='A' and id_acto not in (	select fk_bc_acto from bc_acto_entidad where fk_bc_entidad = :idEntidad) ", nativeQuery = true)
	public List<BcActo> findActosDisponible(@Param("idEntidad") Integer idEntidad);
	
}
