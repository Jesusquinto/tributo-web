package com.apirest.tributo.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apirest.tributo.models.entity.Grupos;

public interface GrupoRepository extends JpaRepository<Grupos, Integer>{

}