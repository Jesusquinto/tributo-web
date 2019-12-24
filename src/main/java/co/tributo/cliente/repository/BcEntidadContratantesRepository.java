/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.tributo.cliente.repository;



import co.tributo.cliente.model.BcEntidadContratantes;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Ricardo
 */
public interface BcEntidadContratantesRepository extends JpaRepository<BcEntidadContratantes, Integer>{
	
	
	@Query("SELECT b FROM BcEntidadContratantes b WHERE b.fkEntidad.idEntidad = :idEntidad")
	List<BcEntidadContratantes> findContratantesByIdEntidad(@Param("idEntidad") Integer idEntidad );
}
