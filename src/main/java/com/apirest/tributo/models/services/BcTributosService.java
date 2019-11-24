package com.apirest.tributo.models.services;

import com.apirest.tributo.models.dao.BcTributosRepository;
import com.apirest.tributo.models.entity.BcTributos;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BcTributosService {

    @Autowired
    private BcTributosRepository bcTributosRepository;

    public List<BcTributos> findAllBcTributos() {
        return bcTributosRepository.findAll();
    }

    public BcTributos findTributoById(Integer id) {
        return bcTributosRepository.findById(id).orElse(null);
    }

    public List<BcTributos> findByEstado(String estado) {
        return bcTributosRepository.findByEstado(estado);
    }

    public List<BcTributos> findTributosDisponible(Integer idEntidad) {
        return bcTributosRepository.findTributosDisponible(idEntidad);
    }

    public List<BcTributos> findTributosDispoblesActos(Integer idEntidad, Integer idActo) {
        return bcTributosRepository.findTributosDispoblesActos(idEntidad, idActo);
    }

    public BcTributos saveBcTributos(BcTributos bcTributos) {
        return bcTributosRepository.save(bcTributos);
    }

    public void deleteBcTributo(Integer id) {
        bcTributosRepository.deleteById(id);
    }

}
