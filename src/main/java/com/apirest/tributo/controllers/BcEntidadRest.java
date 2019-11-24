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
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.apirest.tributo.models.entity.BcEntidad;
import com.apirest.tributo.models.services.BcEntidadService;

@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping("/rest/v1/")
public class BcEntidadRest {

	@SuppressWarnings("unused")
	private final Logger log = LoggerFactory.getLogger(BcEntidadRest.class);

	@Autowired
	private BcEntidadService bcEntidadService;

	@GetMapping("/entidad/list")
	public List<BcEntidad> listarBcEntidad() {
		return bcEntidadService.findAllBcEntidad();
	}

	@GetMapping("/entidad/{id}")
	public Optional<BcEntidad> findOneBcEntidad(@PathVariable("id") final Integer id) {
		return bcEntidadService.findOneBcEntidad(id);
	}

	@GetMapping("/dashboard/resumen/{identidad}")
	public List<Object> getEstadisticas(@PathVariable("identidad") final Integer identidad) {
		return bcEntidadService.getEstadisticas(identidad);
	}

	@GetMapping("/dashboard/global/{identidad}")
	public List<Object> getGlobales(@PathVariable("identidad") final Integer identidad) {
		return bcEntidadService.getGlobales(identidad);
	}


	@PutMapping("/entidad/{id}")
	public ResponseEntity<?> update(@RequestBody BcEntidad entidad, @PathVariable Integer id) {

		BcEntidad entidadActual = bcEntidadService.findById(id);
		BcEntidad entidadUpdated = null;

		Map<String, Object> response = new HashMap<>();

		if (entidadActual == null) {
			response.put("mensaje", "Eror: no se puede editar, la entidad con el ID: No existe");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			entidadActual.setContactoCargo(entidad.getContactoCargo());
			entidadActual.setContactoEmail(entidad.getContactoEmail());
			entidadActual.setContactoNombre(entidad.getContactoNombre());
			entidadActual.setContactoTelefono(entidad.getContactoTelefono());
			entidadActual.setDireccion(entidad.getDireccion());
			entidadActual.setEstado(entidad.getEstado());
			entidadActual.setFkGedivipo(entidad.getFkGedivipo());
			entidadActual.setFunciones(entidad.getFunciones());
			entidadActual.setIdEntidad(entidad.getIdEntidad());
			entidadActual.setLicencia(entidad.getLicencia());
			entidadActual.setNit(entidad.getNit());
			entidadActual.setNombre(entidad.getNombre());
			entidadActual.setOrden(entidad.getOrden());
			entidadActual.setSesionesconcurrentes(entidad.getSesionesconcurrentes());
			entidadActual.setTelefono(entidad.getTelefono());
			entidadActual.setUsuarios(entidad.getUsuarios());

			entidadUpdated = bcEntidadService.saveBcEntidad(entidadActual);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar la entidad");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "La Entidad ha sido actualizada con éxito!");
		response.put("Entidad", entidadUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@PostMapping("/entidad/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Integer id) {

		Map<String, Object> response = new HashMap<>();

		BcEntidad entidad = bcEntidadService.findById(id);

		if (!archivo.isEmpty()) {
			String nombreArchivo = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename().replace(" ", "");
			Path rutaArchivo = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();
			try {
				Files.copy(archivo.getInputStream(), rutaArchivo);
			} catch (IOException e) {
				response.put("mensaje", "Error al subir la imagen " + nombreArchivo);
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			String nombreLogoAnterior = entidad.getLogo();

			if (nombreLogoAnterior != null && nombreLogoAnterior.length() > 0) {
				Path rutaLogoAnterior = Paths.get("uploads").resolve(nombreLogoAnterior).toAbsolutePath();
				File archivoLogoAnterior = rutaLogoAnterior.toFile();
				if (archivoLogoAnterior.exists() && archivoLogoAnterior.canRead()) {
					archivoLogoAnterior.delete();
				}
			}

			entidad.setLogo(nombreArchivo);

			bcEntidadService.saveBcEntidad(entidad);

			response.put("Entidad", entidad);
			response.put("mensaje", "Has subido correctamente el logo: " + nombreArchivo);
		}

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@GetMapping("/uploads/img/{nombreLogo:.+}")
	public ResponseEntity<Resource> verLogo(@PathVariable String nombreLogo) {

		Path rutaArchivo = Paths.get("uploads").resolve(nombreLogo).toAbsolutePath();
		Resource recurso = null;
		try {
			recurso = new UrlResource(rutaArchivo.toUri());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		if (!recurso.exists() && !recurso.isReadable()) {
			throw new RuntimeException("Error no se pudo cargar la imagen: " + nombreLogo);
		}

		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
	}

	@PostMapping("/entidad/crear")
	public ResponseEntity<?> create(@RequestBody BcEntidad entidad) {
		Map<String, Object> response = new HashMap<>();
		BcEntidad entidadSent = null;

		try {
			entidadSent = bcEntidadService.saveBcEntidad(entidad);

		} catch (DataAccessException e) {

			response.put("mensaje", "Error al Crear la entidad");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "La Entidad: " + entidadSent.getIdEntidad() + " con el Nombre "
				+ entidadSent.getNombre() + " ha sido creada con éxito!");
		response.put("Entidad", entidadSent);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@DeleteMapping("/entidad/{id}")
	public ResponseEntity<?> deleteEntidad(@PathVariable("id") Integer id) {
		Map<String, Object> response = new HashMap<>();

		try {
			bcEntidadService.deleteEntidad(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al Eliminar la Entidad");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La Entidad con id " + id + " ha sido eliminado con éxito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
