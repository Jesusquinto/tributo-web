/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.tributo.cliente.repository;


import co.tributo.cliente.model.BcEntidadTributos;
import co.tributo.cliente.model.FrUsuario;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Ricardo
 */
public interface BcEntidadTributosRepository extends JpaRepository<BcEntidadTributos,Integer>{
    
    
   @Query("SELECT b FROM BcEntidadTributos b WHERE b.identidad.idEntidad = :idEntidad") 
   List<BcEntidadTributos> findByIdEntidad (@Param("idEntidad") int idEntidad); 
   
   @Query("SELECT b FROM BcEntidadTributos b WHERE b.idEntTributo = :id")
   BcEntidadTributos findById(@Param("id") int id);
    
}
