package com.apirest.tributo.controllers;

import com.apirest.tributo.models.entity.FrUsuarioActo;
import com.apirest.tributo.models.services.FrUsuarioActoService;

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
public class FrUsuarioActoRest {

  @Autowired
  private FrUsuarioActoService frUsuarioActoService;

  @RequestMapping(value = "/rest/v1/usuariosacto/list", 
    method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public List<FrUsuarioActo> listarFrUsuarioActo() {
    return frUsuarioActoService.findAllFrUsuarioActo();
  }

  @RequestMapping(value = "/rest/v1/usuariosacto/{id}", method = RequestMethod.GET,
    produces = MediaType.APPLICATION_JSON_VALUE)
  public Optional<FrUsuarioActo> getFrUsuarioActoById(@PathVariable("id") final Integer id) {
    return frUsuarioActoService.findOneFrUsuarioActo(id);
  }

  @RequestMapping(value = "/rest/v1/usuariosacto/usuario/{idUsuario}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public List<FrUsuarioActo> listarFrUsuarioActoByIdUsuario(@PathVariable("idUsuario") final Integer idUsuario) {
    return frUsuarioActoService.listarByIdUsuario(idUsuario);
  }

  @RequestMapping(value = "/rest/v1/usuariosacto/filter/{idUsuario}/{idEntidadContratantes}/{idEntidad}/{idActo}/{estado}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public List<FrUsuarioActo> filterByVarios(@PathVariable("idUsuario") final int idUsuario,
      @PathVariable("idEntidadContratantes") final int idEntidadContratantes,
      @PathVariable("idEntidad") final int idEntidad,
      @PathVariable("idActo") final int idActo,
      @PathVariable("estado") final String estado) {
    return frUsuarioActoService.filterByVarios(idUsuario, idEntidadContratantes, idEntidad, idActo, estado);
  }

  @RequestMapping(value = "/rest/v1/usuariosacto/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  public FrUsuarioActo saveFrUsuarioActo(@RequestBody FrUsuarioActo frUsuarioActo) {
    return frUsuarioActoService.saveFrUsuarioActo(frUsuarioActo);
  }

  @RequestMapping(value = "/rest/v1/usuariosacto/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  public FrUsuarioActo updateFrUsuarioActo(@PathVariable("id") final Integer id, @RequestBody FrUsuarioActo frUsuarioActo) {
    return frUsuarioActoService.actualizarFrUsuarioActo(id, frUsuarioActo);
  }

}
