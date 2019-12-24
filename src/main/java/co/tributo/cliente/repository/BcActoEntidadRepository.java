/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.tributo.cliente.repository;


import co.tributo.cliente.model.BcActoEntidad;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Ricardo
 */
public interface BcActoEntidadRepository extends JpaRepository<BcActoEntidad, Integer>{
    
    
    @Query("SELECT b FROM BcActoEntidad b WHERE b.fkBcEntidad.idEntidad = :idEntidad")
    List<BcActoEntidad> findByIdEntidad(@Param("idEntidad") Integer idEntidad);
    
    
    @Query("SELECT b FROM BcActoEntidad b WHERE (b.codigo LIKE CONCAT('%',:codigo,'%') OR :codigo = '0') AND (b.fkBcActo.nombreActo LIKE CONCAT('%',:nombre,'%') OR :nombre = '0' ) AND b.fkBcEntidad.idEntidad = :idEntidad")
    public List<BcActoEntidad> filterByVarios(@Param("codigo") String codigo, @Param("nombre") String nombre, @Param("idEntidad") Integer idEntidad);

    

   }
