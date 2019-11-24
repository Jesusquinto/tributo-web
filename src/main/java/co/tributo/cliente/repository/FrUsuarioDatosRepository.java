/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.tributo.cliente.repository;


import co.tributo.cliente.model.FrUsuarioDatos;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Ricardo
 */
public interface FrUsuarioDatosRepository extends JpaRepository<FrUsuarioDatos, Integer> {
    
}
