package com.apirest.tributo.controllers;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.tributo.models.entity.Usuario;
import com.apirest.tributo.models.services.UsuarioService;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/rest/v1/")
public class BcUsuariosRest {

	@Autowired
	private UsuarioService userService;

	@GetMapping("/users/list")
	public List<Usuario> findAllUsers() {
		return userService.findAllUsers();

	}
	
	@GetMapping("/users/{idEntidad}")
	public List<Usuario> findByIdEntidad(@PathVariable("idEntidad") Integer idEntidad){
		return userService.findByIdEntidad(idEntidad);
	}
	
	@GetMapping("/users/find/{idUsr}")
	public ResponseEntity<?> findById(@PathVariable("idUsr") Integer idUsr){
		return null;
	}
	
	@PostMapping("/user/create")
    public ResponseEntity<?> create(@RequestBody Usuario usuario) {
        Map<String, Object> response = new HashMap<>();
        Usuario userNew = null;

        try {
        	userNew = userService.crearNuevoUsuario(usuario);

        } catch (DataAccessException e) {

            response.put("mensaje", "Error al Crear el usuario "+usuario.getNombre());
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El usuario: " + userNew.getId() + " con el Nombre " + userNew.getNombre() + " ha sido creado con éxito!");
        response.put("Entidad", userNew);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			// userService.deleteUsuarioById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al Eliminar El Usuario");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El Usuario con id " + id + " ha sido eliminado con éxito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
