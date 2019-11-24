package com.apirest.tributo.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.apirest.tributo.models.entity.Parametros;

public interface ParametrosRepository extends JpaRepository<Parametros, Integer> {

    @Query(value = "select * from parametros where fk_entidad = 0", nativeQuery = true)
    List<Parametros> findParametrosAdmin();

    @Query(value = "select * from parametros where fk_entidad <> 0", nativeQuery = true)
    List<Parametros> findParametrosGeneral();
}