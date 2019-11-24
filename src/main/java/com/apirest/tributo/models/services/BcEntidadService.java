package com.apirest.tributo.models.services;

import com.apirest.tributo.models.dao.BcEntidadRepository;
import com.apirest.tributo.models.entity.BcEntidad;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BcEntidadService implements IBcEntidad {

	private final Logger logger = LoggerFactory.getLogger(BcEntidadService.class);
	@Autowired
	private BcEntidadRepository bcEntidadRepository;

	public List<BcEntidad> findAllBcEntidad() {
		return bcEntidadRepository.findAll();
	}

	public Optional<BcEntidad> findOneBcEntidad(int id) {
		return bcEntidadRepository.findById(id);
	}

	public List<Object> getEstadisticas(int identidad) {
		return bcEntidadRepository.getEstadisticas(identidad);
	}

	public List<Object> getGlobales(int identidad) {
		return bcEntidadRepository.getGlobal(identidad);
	}

	public BcEntidad actualizarBcEntidad(Integer id, BcEntidad bcEntidad) {

		if (bcEntidadRepository.findById(id) == null) {

		} else {
			logger.info("los datos son" + bcEntidad);
			return bcEntidadRepository.save(bcEntidad);
		}

		return bcEntidad;

	}

	@Override
	@Transactional
	public BcEntidad findById(Integer id) {
		return bcEntidadRepository.findById(id).orElse(null);
	}

	public BcEntidad saveBcEntidad(BcEntidad bcEntidad) {
		logger.info("El código gedivipo de la entidad es: " + bcEntidad.getFkGedivipo());
		return bcEntidadRepository.save(bcEntidad);
	}

	public void deleteEntidad(Integer id) {
		bcEntidadRepository.deleteById(id);
	}

}
