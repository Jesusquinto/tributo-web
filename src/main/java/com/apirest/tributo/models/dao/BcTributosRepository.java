package com.apirest.tributo.models.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.apirest.tributo.models.entity.BcTributos;

public interface BcTributosRepository extends JpaRepository<BcTributos, Integer> {

	public List<BcTributos> findByEstado(@Param("estado") String estado);

	@Query(value = "SELECT * FROM bc_tributos WHERE estado='A' and id_tributo not in (select idtributo from bc_entidad_tributos where identidad = :idEntidad) ", nativeQuery = true)
	public List<BcTributos> findTributosDisponible(@Param("idEntidad") Integer idEntidad);

	@Query(value = "SELECT * FROM bc_tributos WHERE estado='A' and id_tributo not in (select DISTINCT(idtributo) from bc_acto_detalle, bc_entidad_tributos where bc_acto_detalle.fk_entidad_tributo = bc_entidad_tributos.id_ent_tributo and identidad = :idEntidad and  bc_acto_detalle.fk_tipo_acto = :idActo)", nativeQuery = true)
	public List<BcTributos> findTributosDispoblesActos(@Param("idEntidad") Integer idEntidad,
			@Param("idActo") Integer idActo);
}
