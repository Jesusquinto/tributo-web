package com.apirest.tributo.controllers;

import com.apirest.tributo.models.entity.FrUsuario;
import com.apirest.tributo.models.services.FrUsuarioService;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"*"})
@RestController
public class FrUsuarioRest {

  @Autowired
  private FrUsuarioService frUsuarioService;

  @RequestMapping(value = "/rest/v1/frusuarios/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public List<FrUsuario> listarFrUsuario() {
    return frUsuarioService.findAllFrUsuario();

  }

  @RequestMapping(value = "/rest/v1/frusuarios/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public Optional<FrUsuario> listarFrUsuarioById(@PathVariable("id") final Integer id) {
    return frUsuarioService.findOneFrUsuario(id);
  }

  @RequestMapping(value = "/rest/v1/frusuarios/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  public FrUsuario saveFrUsuario(@RequestBody FrUsuario frUsuario) {
    return frUsuarioService.saveFrUsuario(frUsuario);
  }

  @RequestMapping(value = "/rest/v1/frusuarios/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  public FrUsuario updateUsuarios(@PathVariable("id") final Integer id, @RequestBody FrUsuario frUsuario) {
    return frUsuarioService.actualizarFrUsuario(id, frUsuario);
  }

}
