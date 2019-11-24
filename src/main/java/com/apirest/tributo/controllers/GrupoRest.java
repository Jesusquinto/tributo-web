package com.apirest.tributo.controllers;

import com.apirest.tributo.models.entity.Grupos;
import com.apirest.tributo.models.services.GrupoService;

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
public class GrupoRest {

    @Autowired
    GrupoService grupoService;

    @GetMapping("grupos/list")
    public List<Grupos> findAllGrupos() {
        return grupoService.finalAllGrupos();
    }

    @GetMapping("grupos/{idGrupo}")
    public Grupos findGrupoById(@PathVariable("idGrupo") Integer idGrupo) {
        return grupoService.finalGrupoById(idGrupo);
    }

    @PostMapping("grupos")
    public ResponseEntity<?> createGrupo(@RequestBody Grupos grupo) {
        Map<String, Object> response = new HashMap<>();
        Grupos grupoToSave = null;
        try {
            grupoToSave = grupoService.saveGrupo(grupo);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al Crear El Grupo");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El Grupo: " + grupoToSave.getIdGrupo() + " con el Nombre "
                + grupoToSave.getNombreGrupo() + " ha sido creado con éxito!");
        response.put("grupo", grupoToSave);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PutMapping("grupos")
    public ResponseEntity<?> editGrupo(@RequestBody Grupos grupo) {
        Map<String, Object> response = new HashMap<>();
        Grupos grupoToSave = null;
        Grupos grupoActual = grupoService.finalGrupoById(grupo.getIdGrupo());
        if (grupoActual == null) {
            response.put("mensaje",
                    "Eror: no se puede editar, El Grupo con el ID: " + grupo.getIdGrupo() + " No existe");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {
            grupoActual.setIdGrupo(grupo.getIdGrupo());
            grupoActual.setFkEmpresa(grupo.getFkEmpresa());
            grupoActual.setNombreGrupo(grupo.getNombreGrupo());
            grupoToSave = grupoService.saveGrupo(grupoActual);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al Editar El Grupo");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El Grupo: " + grupoToSave.getIdGrupo() + " con el Nombre "
                + grupoToSave.getNombreGrupo() + " ha sido creado con éxito!");
        response.put("grupo", grupoToSave);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("grupos/{idGrupo}")
    public ResponseEntity<?> deleteGrupo(@PathVariable("idGrupo") Integer idGrupo) {
        Map<String, Object> response = new HashMap<>();
        Grupos grupo = grupoService.finalGrupoById(idGrupo);
        if (grupo == null) {
            response.put("mensaje", "Eror: no se puede editar, El Grupo con el ID: " + idGrupo + " No existe");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {
            grupoService.deleteGrupoById(idGrupo);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al Eliminar El Parametro");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El Grupo con id " + idGrupo + " ha sido eliminado con éxito!");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

}
