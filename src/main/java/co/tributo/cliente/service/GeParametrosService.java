/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.tributo.cliente.service;


import co.tributo.cliente.model.GeParametros;
import co.tributo.cliente.repository.GeParametrosRepository;
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

public class GeParametrosService {

@Autowired
private GeParametrosRepository geParametrosRepository;

public List<GeParametros> findAllGeParametros() {
    return geParametrosRepository.findAll();
}
    
}
