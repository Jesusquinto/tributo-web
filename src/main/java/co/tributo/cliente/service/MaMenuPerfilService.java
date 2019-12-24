/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.tributo.cliente.service;


import co.tributo.cliente.model.MaMenuPerfil;
import co.tributo.cliente.repository.MaMenuPerfilRepository;
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
public class MaMenuPerfilService {
    
    @Autowired
    private MaMenuPerfilRepository maMenuPerfilRepository;
    
    public List<MaMenuPerfil> findAllMaMenuPerfil() {
        return maMenuPerfilRepository.findAll();
    }
    
}
