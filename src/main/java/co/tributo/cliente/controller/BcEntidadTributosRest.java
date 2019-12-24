/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.tributo.cliente.controller;


import co.tributo.cliente.model.BcEntidadTributos;
import co.tributo.cliente.service.BcEntidadTributosService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Ricardo
 */
@RestController
public class BcEntidadTributosRest {
    
    @Autowired
    private BcEntidadTributosService bcEntidadTributosService;
    
    //GET
    @CrossOrigin(origins="*")
    @RequestMapping (value="/rest/v1/entidadtributo/list", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE  )
    public List<BcEntidadTributos> listarBcEntidadTributos() {
        return bcEntidadTributosService.findAllBcEntidadTributos();
               
    } 
    
    @CrossOrigin(origins="*")
    @RequestMapping (value="/rest/v1/entidadtributo/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE  )
    public List<BcEntidadTributos> listarBcEntidadTributosById(@PathVariable("id") final int idEntidad) {
        return bcEntidadTributosService.findByIdEntidad(idEntidad);
               
    } 
    
    //GET
    @CrossOrigin(origins="*")
    @RequestMapping (value="/rest/v1/entidadtributo/entidad/{idEntidad}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE  )
    public List<BcEntidadTributos> listarBcEntidadTributosByIdEntidad(@PathVariable("idEntidad") final int idEntidad) {
        return bcEntidadTributosService.findByIdEntidad(idEntidad);
               
    } 
    
    
    
    
}
