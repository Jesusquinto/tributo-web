/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.tributo.cliente.repository;

import co.tributo.cliente.model.Parametros;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author IDitroyer
 */
public interface parametrosRepository extends JpaRepository<Parametros, Integer>{

    @Query("SELECT p FROM Parametros p where p.fkEntidad = :identidad AND p.codigo = :codigo")
    List<Parametros> findByfkEntidadAndCodigo (@Param("identidad") Integer identidad, @Param("codigo") String codigo); 
    
    
    @Query("SELECT p FROM Parametros p where p.fkEntidad = :identidad AND p.fkGrupo = :grupo")
    List<Parametros> findByfkEntidadAndGrupo (@Param("identidad") Integer identidad, @Param("grupo") int grupo);      
    

    
    
    
    
}