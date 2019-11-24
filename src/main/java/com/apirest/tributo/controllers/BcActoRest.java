package com.apirest.tributo.controllers;

import com.apirest.tributo.models.entity.BcActo;
import com.apirest.tributo.models.services.BcActoService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/rest/v1")
public class BcActoRest {

	@Autowired
	private BcActoService bcActoService;

	@GetMapping("/actos/list")
	public List<BcActo> listarBcActo() {
		return bcActoService.findAllBcActo();
	}

	@GetMapping("/actos/{idacto}")
	public Optional<BcActo> findOneBcActo(@PathVariable("idacto") final Integer id) {
		return bcActoService.findAllBcActoByIdActo(id);
	}

	@GetMapping("/actos/disponibles/{idEntidad}")
	public List<BcActo> findActosDisponible(@PathVariable("idEntidad") Integer idEntidad) {
		return bcActoService.findActosDisponible(idEntidad);
	}

	@PostMapping("/actos/crear")
	public ResponseEntity<?> create(@RequestBody BcActo bcActo) {
		Map<String, Object> response = new HashMap<>();
		BcActo acto = null;
		try {
			acto = bcActoService.saveBcActo(bcActo);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al Crear El Tramite");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El Tramite: " + acto.getIdActo() + " con el Nombre " + acto.getNombreActo()
				+ " ha sido creado con éxito!");
		response.put("Tramite", acto);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@PutMapping("/actos/edit/{id}")
	public ResponseEntity<?> update(@RequestBody BcActo bcActo, @PathVariable Integer id) {

		BcActo actoActual = bcActoService.findActoById(id);
		BcActo actoUpdated = null;

		Map<String, Object> response = new HashMap<>();

		if (actoActual == null) {
			response.put("mensaje", "Eror: no se puede editar, El Tramite con el ID: No existe");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			actoActual.setIdActo(bcActo.getIdActo());
			actoActual.setNombreActo(bcActo.getNombreActo());
			actoActual.setDescripcion(bcActo.getDescripcion());
			actoActual.setEstado(bcActo.getEstado());
			actoUpdated = bcActoService.saveBcActo(actoActual);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar El Tramite");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El Tramite " + actoUpdated.getIdActo() + " con el Nombre "
				+ actoUpdated.getNombreActo() + " ha sido actualizado con éxito!");
		response.put("Tributo", actoUpdated);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@DeleteMapping("/actos/{idActo}")
	public ResponseEntity<?> deleteActo(@PathVariable("idActo") Integer idActo) {

		Map<String, Object> response = new HashMap<>();
		BcActo acto = bcActoService.findActoById(idActo);
		if (acto == null) {
			response.put("mensaje", "Eror: no se puede editar, El Acto con el ID: " + idActo + " No existe");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			bcActoService.deleteActo(idActo);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al Eliminar el Tramite");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El Tramite con id " + idActo + " ha sido eliminado con éxito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
