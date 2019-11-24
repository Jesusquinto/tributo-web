package com.apirest.tributo.controllers;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.apirest.tributo.models.entity.BcActoEntidad;
import com.apirest.tributo.models.services.BcActoEntidadService;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/rest/v1/")
public class BcActoEntidadRest {

	@Autowired
	private BcActoEntidadService bcActoEntidadService;

	@GetMapping("/actosentidad/list")
	public List<BcActoEntidad> listarBcActoEntidad() {
		return bcActoEntidadService.findAllBcActoEntidad();

	}

	@GetMapping("/actosentidad/{idEntidad}")
	public List<BcActoEntidad> findOneBcActo(@PathVariable("idEntidad") final Integer id) {
		return bcActoEntidadService.findByIdEntidad(id);
	}

	@PostMapping("/actosentidad/new")
	public BcActoEntidad saveBcEntEntidad(@RequestBody BcActoEntidad bcActoEntidad) {
		return bcActoEntidadService.saveBcActoEntidad(bcActoEntidad);
	}

	@PutMapping("/actosentidad/{id}")
	public ResponseEntity<?> update(@RequestBody BcActoEntidad bcActoEntidad, @PathVariable int id) {

		BcActoEntidad actoEntidadActual = bcActoEntidadService.findById(id);
		BcActoEntidad actoEntidadUpdated = null;

		Map<String, Object> response = new HashMap<>();

		if (actoEntidadActual == null) {
			response.put("mensaje", "Eror: no se puede editar, el tramite con el ID: No existe");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {

			actoEntidadActual.setFkBcActo(bcActoEntidad.getFkBcActo());
			actoEntidadActual.setFkBcEntidad(bcActoEntidad.getFkBcEntidad());
			actoEntidadActual.setCodigo(bcActoEntidad.getCodigo());
			actoEntidadActual.setEsquema(bcActoEntidad.getEsquema());
			actoEntidadActual.setTipoperiodo(bcActoEntidad.getTipoperiodo());

			actoEntidadUpdated = bcActoEntidadService.saveBcActoEntidad(actoEntidadActual);

		} catch (DataAccessException e) {

			response.put("mensaje", "Error al actualizar el tramite");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		response.put("mensaje", "El tramite ha sido actualizado con éxito!");
		response.put("el tramite", actoEntidadUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@DeleteMapping("/entidadacto/{idActoEntidad}")
	public ResponseEntity<?> deleteActo(@PathVariable("idActoEntidad") Integer idActoEntidad) {

		Map<String, Object> response = new HashMap<>();
		try {
			bcActoEntidadService.deleteActoEntidad(idActoEntidad);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al Eliminar el trámite");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El trámite ha sido eliminado con éxito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@PostMapping("/entidad/tramite/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Integer id) {

		Map<String, Object> response = new HashMap<>();

		BcActoEntidad acto = bcActoEntidadService.findById(id);

		if (!archivo.isEmpty()) {
			String nombreArchivo = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename().replace(" ", "");
			Path rutaArchivo = Paths.get("formatos").resolve(nombreArchivo).toAbsolutePath();

			try {
				Files.copy(archivo.getInputStream(), rutaArchivo);
			} catch (IOException e) {
				response.put("mensaje", "Error al subir el formato " + nombreArchivo);
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			String nombreFormatoAnterior = acto.getEsquema();

			if (nombreFormatoAnterior != null && nombreFormatoAnterior.length() > 0) {
				Path rutaFormatoAnterior = Paths.get("formatos").resolve(nombreFormatoAnterior).toAbsolutePath();
				File archivoFormatoAnterior = rutaFormatoAnterior.toFile();
				if (archivoFormatoAnterior.exists() && archivoFormatoAnterior.canRead()) {
					archivoFormatoAnterior.delete();
				}
			}

			acto.setEsquema(nombreArchivo);

			bcActoEntidadService.saveBcActoEntidad(acto);

			response.put("Trámite", acto);
			response.put("mensaje", "Has subido correctamente el Formato: " + nombreArchivo);
		}

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@GetMapping("/formatos/tramites/{nombreFormato:.+}")
	public ResponseEntity<Resource> verFormato(@PathVariable String nombreFormato) {

		Path rutaArchivo = Paths.get("formatos").resolve(nombreFormato).toAbsolutePath();
		Resource recurso = null;
		try {
			recurso = new UrlResource(rutaArchivo.toUri());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		if (!recurso.exists() && !recurso.isReadable()) {
			throw new RuntimeException("Error no se pudo cargar el formato: " + nombreFormato);
		}

		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
	}
}
