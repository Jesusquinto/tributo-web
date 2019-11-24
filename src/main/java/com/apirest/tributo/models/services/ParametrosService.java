package com.apirest.tributo.models.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apirest.tributo.models.dao.ParametrosRepository;
import com.apirest.tributo.models.entity.Parametros;

@Service
@Transactional
public class ParametrosService {

	@Autowired
	ParametrosRepository parametrosRepository;

	public List<Parametros> findAllParametros() {
		return parametrosRepository.findAll();
	}

	public List<Parametros> findParametrosAdmin() {
		return parametrosRepository.findParametrosAdmin();
	}

	public List<Parametros> findParametrosGeneral() {
		return parametrosRepository.findParametrosGeneral();
	}

	public Parametros findAllParametroById(Integer id) {
		return parametrosRepository.findById(id).orElse(null);
	}

	public Parametros saveParametro(Parametros parametro) {
		return parametrosRepository.save(parametro);
	}

	public void deleteParametroById(Integer id) {
		parametrosRepository.deleteById(id);
	}
}
