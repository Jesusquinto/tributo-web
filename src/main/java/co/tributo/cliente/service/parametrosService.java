/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.tributo.cliente.service;

import co.tributo.cliente.model.Parametros;
import co.tributo.cliente.repository.parametrosRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Ricardo
 */
@Service
@Transactional
public class parametrosService {

    @Autowired
    private parametrosRepository ParametrosRepository;


    /**
    *
    * @author Ricardo
     * @return 
    */
    public List<Parametros> findAllParametros(){
        return ParametrosRepository.findAll();
    }

    /**
    *
    * @author Ricardo
     * @param idEntidad
     * @return 
    */
    public List<Parametros> findByfkEntidadAndCodigo(int idEntidad, String codigo){
        return ParametrosRepository.findByfkEntidadAndCodigo(idEntidad, codigo);
    }
    
    
    public List<Parametros> findByEntidadAndGrupo (int idEntidad, int grupo){
        return ParametrosRepository.findByfkEntidadAndGrupo(idEntidad, grupo);
    }




    public Parametros saveParametros(Parametros parametro) {
         return ParametrosRepository.save(parametro);

    }




}