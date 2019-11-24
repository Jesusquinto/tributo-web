/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apirest.tributo.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apirest.tributo.models.entity.FrUsuario;

/**
 *
 * @author Ricardo
 */
public interface FrUsuarioRepository extends JpaRepository<FrUsuario, Integer> {

}
