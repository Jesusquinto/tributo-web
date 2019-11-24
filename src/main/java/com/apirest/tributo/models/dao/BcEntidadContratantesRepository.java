/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apirest.tributo.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.apirest.tributo.models.entity.BcEntidadContratantes;

/**
 *
 * @author Ricardo
 */
public interface BcEntidadContratantesRepository extends JpaRepository<BcEntidadContratantes, Integer> {

	@Query(value="SELECT * FROM bc_entidad_contratantes where fk_entidad = :idEntidad", nativeQuery = true)
	List<BcEntidadContratantes> findAllByIdEntidad(Integer idEntidad);

}
