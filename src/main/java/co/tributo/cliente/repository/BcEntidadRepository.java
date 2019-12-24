/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.tributo.cliente.repository;


import co.tributo.cliente.model.BcEntidad;
import co.tributo.cliente.model.FrUsuarioActo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Ricardo
 */
public interface BcEntidadRepository extends JpaRepository<BcEntidad, Integer>{
    
    /**
     *
     * @param identidad
     * @return
     */
    @Query(value = "SELECT fk_acto_entidad, sum(valor_apagar) TotalRecaudo FROM fr_usuario_acto ua,bc_acto_entidad ae where fk_bc_entidad = ae.id_acto_entidad and  ae.id_acto_entidad = :identidad group by fk_acto_entidad", nativeQuery = true)
    List<Object> getEstadisticas(@Param("identidad") int identidad);
    
    /**
     *
     * @param identidad
     * @return TotalContratantes, TotalTributos, TotalActos, TotalUsuarios
     */
    @Query(value=" select (SELECT count(*) Entidades FROM bc_entidad_contratantes where fk_entidad = :identidad ),\n" +
"	           (SELECT count(*) Tributos FROM bc_entidad_tributos where identidad = :identidad ),\n" +
"                  (SELECT count(*) TiposActos FROM bc_acto_entidad where fk_bc_entidad = :identidad),\n" +
"                  (SELECT count(fk_usuario) UsuariosTotales FROM fr_usuario_acto ua,bc_acto_entidad ae where fk_bc_entidad = ae.id_acto_entidad and  ae.id_acto_entidad = :identidad)", nativeQuery = true)
    List<Object> getGlobal(@Param("identidad") int identidad);
    
    
    
    @Query("SELECT b FROM BcEntidad b WHERE (b.fkGedivipo.nombreDepartamentp LIKE CONCAT('%',:departamento,'%') OR :departamento = '0') AND (b.fkGedivipo.nombreMunicipio LIKE CONCAT('%',:municipio,'%') OR :municipio = '0' ) and (b.nombre LIKE CONCAT('%',:nombre,'%') OR :nombre = '0' ) and (b.idEntidad <> 0) ")
    public List<BcEntidad> filterByVarios(@Param("departamento") String departamento, @Param("municipio") String municipio, @Param("nombre") String nombre);

    
    
}
