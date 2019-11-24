package com.apirest.tributo.controllers;

import com.apirest.tributo.models.entity.BcActoDetalle;
import com.apirest.tributo.models.services.BcActoDetalleService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/rest/v1/")
public class BcActoDetalleRest {

  @Autowired
  private BcActoDetalleService bcActoDetalleService;

  @GetMapping("/actosdetalle/list")
  public List<BcActoDetalle> listarBcActo() {
    return bcActoDetalleService.findBcActoDetalle();
  }

  @GetMapping("/actosdetalle/actos/{identidad}/{idacto}")
  public List<BcActoDetalle> listarByIdActo(@PathVariable("identidad") Integer identidad, @PathVariable("idacto") Integer idacto) {

    return bcActoDetalleService.findByIdActo(identidad, idacto);
  }

  @PostMapping("/actosdetalle/save")
  public BcActoDetalle saveBcActoDetalle(@RequestBody BcActoDetalle bcActoDetalle) {
    return bcActoDetalleService.newBcActoDetalle(bcActoDetalle);
  }
}
