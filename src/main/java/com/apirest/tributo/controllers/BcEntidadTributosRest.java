package com.apirest.tributo.controllers;

import com.apirest.tributo.models.entity.BcEntidadTributos;
import com.apirest.tributo.models.services.BcEntidadTributosService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/rest/v1/")
public class BcEntidadTributosRest {

  @Autowired
  private BcEntidadTributosService bcEntidadTributosService;

  @GetMapping("/entidadtributo/list")
  public List<BcEntidadTributos> listarBcEntidadTributos() {
    return bcEntidadTributosService.findAllBcEntidadTributos();
  }

  @GetMapping("/entidadtributo/entidad/{idEntidad}")
  public List<BcEntidadTributos> listarBcEntidadTributosByIdEntidad(@PathVariable("idEntidad") final int idEntidad) {
    return bcEntidadTributosService.findByIdEntidad(idEntidad);
  }

  @PostMapping("/entidadtributo/entidad/save")
  public BcEntidadTributos saveBcEntidadTributo(@RequestBody BcEntidadTributos bcEntidadTributos) {
    return bcEntidadTributosService.saveBcEntidadTributo(bcEntidadTributos);
  }

  @PutMapping("/entidadtributo/{id}")
  public ResponseEntity<?> update(@RequestBody BcEntidadTributos entidadTributo, @PathVariable int id) {

    BcEntidadTributos entidadTributoActual = bcEntidadTributosService.findById(id);
    BcEntidadTributos entidadTributoUpdated = null;

    Map<String, Object> response = new HashMap<>();

    if (entidadTributoActual == null) {
      response.put("mensaje", "Eror: no se puede editar, el tributo con el ID: No existe");
      return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
    }
    try {

      entidadTributoActual.setIdentidad(entidadTributo.getIdentidad());
      entidadTributoActual.setIdtributo(entidadTributo.getIdtributo());
      entidadTributoActual.setIdusuario(entidadTributo.getIdusuario());
      entidadTributoActual.setEstado(entidadTributo.getEstado());
      entidadTributoActual.setParametroTributo(entidadTributo.getParametroTributo());

      entidadTributoUpdated = bcEntidadTributosService.saveBcEntidadTributo(entidadTributoActual);

    } catch (DataAccessException e) {

      response.put("mensaje", "Error al actualizar el tributo");
      response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
      return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    response.put("mensaje", "El tributo ha sido actualizado con éxito!");
    response.put("tributo", entidadTributoUpdated);

    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
  }

  @DeleteMapping("/entidadtributo/{idEntTributo}")
  public ResponseEntity<?> deleteTributo(@PathVariable("idEntTributo") int idEntTributo) {

    Map<String, Object> response = new HashMap<>();
    try {
      bcEntidadTributosService.deleteTributo(idEntTributo);
    } catch (DataAccessException e) {
      response.put("mensaje", "Error al Eliminar el tributo");
      response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
      return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    response.put("mensaje", "El tributo ha sido eliminado con éxito!");
    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
  }
  
  @GetMapping("/entidadtributo/{idEntidad}/{idTributo}")
  public List<BcEntidadTributos> findByIdEntidadTributo (@PathVariable("idEntidad") Integer idEntidad, @PathVariable("idTributo") Integer idTributo) {
	  return bcEntidadTributosService.findByIdEntidadTributo(idEntidad, idTributo);
  }

}
