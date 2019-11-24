package com.apirest.tributo.controllers;

import com.apirest.tributo.models.entity.BcTributos;
import com.apirest.tributo.models.services.BcTributosService;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/rest/v1/")
public class BcTributosRest {

	@Autowired
	private BcTributosService bcTributosService;

	@GetMapping("/tributos/list")
	public List<BcTributos> listarBcEntidadTributos() {
		return bcTributosService.findAllBcTributos();

	}

	@GetMapping("/tributos/estado/{estado}")
	public List<BcTributos> listarTributosPorEstado(@PathVariable("estado") String estado) {
		return bcTributosService.findByEstado(estado);
	}

	@GetMapping("/tributos/disponibles/{idEntidad}")
	public List<BcTributos> findTributosDisponible(@PathVariable("idEntidad") Integer idEntidad) {
		return bcTributosService.findTributosDisponible(idEntidad);
	}

	@GetMapping("/tributos/disponiblesactos/{idEntidad}/{idActo}")
	public List<BcTributos> findTributosDispoblesActos(@PathVariable("idEntidad") Integer idEntidad,
			@PathVariable("idActo") Integer idActo) {
		return bcTributosService.findTributosDispoblesActos(idEntidad, idActo);
	}

	@PostMapping("/tributo/nuevo")
	public ResponseEntity<?> create(@RequestBody BcTributos bcTributos) {
		Map<String, Object> response = new HashMap<>();
		BcTributos tributos = null;

		try {
			tributos = bcTributosService.saveBcTributos(bcTributos);

		} catch (DataAccessException e) {

			response.put("mensaje", "Error al Crear El Tributo");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El Tributo: " + tributos.getIdTributo() + " con el Nombre " + tributos.getNombre()
				+ " ha sido creado con éxito!");
		response.put("Tributo", tributos);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@PutMapping("/tributo/edit/{id}")
	public ResponseEntity<?> update(@RequestBody BcTributos bcTributos, @PathVariable Integer id) {

		BcTributos tributoActual = bcTributosService.findTributoById(id);
		BcTributos tributoUpdated = null;

		Map<String, Object> response = new HashMap<>();

		if (tributoActual == null) {
			response.put("mensaje", "Eror: no se puede editar, El Tributo con el ID: No existe");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			tributoActual.setIdTributo(bcTributos.getIdTributo());
			tributoActual.setNombre(bcTributos.getNombre());
			tributoActual.setEstado(bcTributos.getEstado());
			tributoUpdated = bcTributosService.saveBcTributos(tributoActual);
		} catch (DataAccessException e) {

			response.put("mensaje", "Error al actualizar El Tributo");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El Tributo " + tributoUpdated.getIdTributo() + " con el Nombre "
				+ tributoUpdated.getNombre() + " ha sido actualizado con éxito!");
		response.put("Tributo", tributoUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@DeleteMapping("/tributo/{idActoEntidad}")
	public ResponseEntity<?> deleteActo(@PathVariable("idActoEntidad") Integer idTributo) {

		Map<String, Object> response = new HashMap<>();
		try {
			bcTributosService.deleteBcTributo(idTributo);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al Eliminar el Tributo");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El Tributo con id " + idTributo + " ha sido eliminado con éxito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
