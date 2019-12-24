/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.tributo.cliente.service;


import co.tributo.cliente.model.MaPerfiles;
import co.tributo.cliente.repository.MaPerfilesRepository;
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
public class MaPerfilesService {
    
    @Autowired
    private MaPerfilesRepository maPerfilesRepository;
    
    public List<MaPerfiles> findAllMaPerfiles(){
        return maPerfilesRepository.findAll();
    }
    
}
