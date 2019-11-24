
package com.apirest.tributo.models.services;

import com.apirest.tributo.models.dao.BcActoRepository;
import com.apirest.tributo.models.entity.BcActo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class BcActoService {

    @Autowired
    BcActoRepository bcActoRepository;

   
    public List<BcActo> findAllBcActo() {
        return bcActoRepository.findAll();
    }

 
    public Optional<BcActo> findAllBcActoByIdActo(int IdActo) {
        return bcActoRepository.findById(IdActo);
    }

    public List<BcActo> findActosDisponible(Integer idEntidad) {
        return bcActoRepository.findActosDisponible(idEntidad);
    }

    public BcActo findActoById(Integer idActo) {
        return bcActoRepository.findById(idActo).orElse(null);
    }

    public BcActo saveBcActo(BcActo bcActo) {
        return bcActoRepository.save(bcActo);
    }

    public void deleteActo(Integer idActo) {
        bcActoRepository.deleteById(idActo);
    }

}
