package com.apirest.tributo.controllers;

import com.apirest.tributo.models.entity.Parametros;
import com.apirest.tributo.models.services.ParametrosService;

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
public class ParametrosRest {

    @Autowired
    ParametrosService parametrosService;

    @GetMapping("parametros/list")
    public List<Parametros> findAllParametros() {
        return parametrosService.findAllParametros();
    }

    @GetMapping("parametros/{id}")
    public Parametros findParametroById(@PathVariable("id") Integer id) {
        return parametrosService.findAllParametroById(id);
    }

    @GetMapping("parametros/admin")
    public List<Parametros> findParametrosAdmin() {
        return parametrosService.findParametrosAdmin();
    }

    @GetMapping("parametros/general")
    public List<Parametros> findParametrosGeneral() {
        return parametrosService.findParametrosGeneral();
    }

    @PostMapping("parametros")
    public ResponseEntity<?> crearParametro(@RequestBody Parametros parametro) {
        Map<String, Object> response = new HashMap<>();
        Parametros parametroeNew = null;
        try {
            parametroeNew = parametrosService.saveParametro(parametro);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al Crear El Parametro");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El Parametro: " + parametroeNew.getIdParametro() + " con el Nombre " + parametroeNew.getNombre()
                + " ha sido creado con éxito!");
        response.put("Parametro", parametroeNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PutMapping("parametros/{id}")
    public ResponseEntity<?> editarParametro(@RequestBody Parametros parametro, @PathVariable Integer id) {
        Map<String, Object> response = new HashMap<>();
        Parametros parametroeToUpdate = null;
        Parametros parametroeActual = parametrosService.findAllParametroById(id);
        if (parametroeActual == null) {
            response.put("mensaje", "Eror: no se puede editar, El Parametro con el ID: " + id + " No existe");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {
            parametroeActual.setCodigo(parametro.getCodigo());
            parametroeActual.setFecha(parametro.getFecha());
            parametroeActual.setNombre(parametro.getNombre());
            parametroeActual.setNombreCorto(parametro.getNombreCorto());
            parametroeActual.setValor(parametro.getValor());
            parametroeToUpdate = parametrosService.saveParametro(parametro);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al Editar El Parametro");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El Parametro: " + parametroeToUpdate.getIdParametro() + " con el Nombre " + parametroeToUpdate.getNombre()
                + " ha sido creado con éxito!");
        response.put("Parametro", parametroeToUpdate);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("parametros/{id}")
    public ResponseEntity<?> deleteParametro(@PathVariable("id") Integer id) {
        Map<String, Object> response = new HashMap<>();
        Parametros parametro = parametrosService.findAllParametroById(id);
        if (parametro == null) {
            response.put("mensaje", "Eror: no se puede editar, El Parametro con el ID: " + id + " No existe");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {
            parametrosService.deleteParametroById(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al Eliminar El Parametro");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El Parametro con id " + id + " ha sido eliminado con éxito!");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

}
