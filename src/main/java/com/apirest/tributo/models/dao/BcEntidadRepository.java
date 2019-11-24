package com.apirest.tributo.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.apirest.tributo.models.entity.BcEntidad;

public interface BcEntidadRepository extends JpaRepository<BcEntidad, Integer> {

	@Query(value = "SELECT fk_acto_entidad, sum(valor_apagar) TotalRecaudo FROM fr_usuario_acto ua,bc_acto_entidad ae where fk_bc_entidad = ae.id_acto_entidad and  ae.id_acto_entidad = :identidad group by fk_acto_entidad", nativeQuery = true)
	List<Object> getEstadisticas(@Param("identidad") int identidad);

	@Query(value = " select (SELECT count(*) Entidades FROM bc_entidad_contratantes where fk_entidad = :identidad ),\n"
			+ "	           (SELECT count(*) Tributos FROM bc_entidad_tributos where identidad = :identidad ),\n"
			+ "                  (SELECT count(*) TiposActos FROM bc_acto_entidad where fk_bc_entidad = :identidad),\n"
			+ "                  (SELECT count(fk_usuario) UsuariosTotales FROM fr_usuario_acto ua,bc_acto_entidad ae where fk_bc_entidad = ae.id_acto_entidad and  ae.id_acto_entidad = :identidad)", nativeQuery = true)
	List<Object> getGlobal(@Param("identidad") int identidad);

}
