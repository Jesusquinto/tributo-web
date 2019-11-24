/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.tributo.cliente.controller;


import co.tributo.cliente.model.BcEntidad;
import co.tributo.cliente.service.BcEntidadService;
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

/**
 *
 * @author Ricardo
 */
@RestController
public class BcEntidadRest {
    
    @Autowired
    private BcEntidadService bcEntidadService;
    
    @CrossOrigin(origins="*")
    @RequestMapping (value="/rest/v1/entidad/list", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE  )
    public List<BcEntidad> listarBcEntidad() {
        return bcEntidadService.findAllBcEntidad();
    }
    
    @CrossOrigin(origins="*")
    @RequestMapping (value="/rest/v1/entidad/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE  )
    public Optional<BcEntidad> findOneBcEntidad(@PathVariable("id") final Integer id) {
        return bcEntidadService.findOneBcEntidad(id);
        
    }
    
    @CrossOrigin(origins="*")
    @RequestMapping (value="/rest/v1/dashboard/resumen/{identidad}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE  )
    public List<Object> getEstadisticas(@PathVariable("identidad") final Integer identidad) {
        return bcEntidadService.getEstadisticas(identidad);
    }
    
    @CrossOrigin(origins="*")
    @RequestMapping (value="/rest/v1/dashboard/global/{identidad}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE  )
    public List<Object> getGlobales(@PathVariable("identidad") final Integer identidad) {
        return bcEntidadService.getGlobales(identidad);
    }
    
    //PUT
    @CrossOrigin(origins="*")
    @RequestMapping (value="/rest/v1/entidad/{id}", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE  )
    public BcEntidad updateBcEntEntidad(@PathVariable("id") final Integer id, @RequestBody BcEntidad bcEntidad) {
        
        return bcEntidadService.actualizarBcEntidad(id, bcEntidad);
    }  
    
    @CrossOrigin(origins="*")
    @RequestMapping (value="/rest/v1/entidad/filter/{departamento}/{municipio}/{nombre}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE  )
    public List<BcEntidad> filterByVarios(@PathVariable("departamento") final String departamento, 
                                              @PathVariable("municipio") final String municipio, 
                                              @PathVariable("nombre") final String nombre) {
        return bcEntidadService.filterByVarios(departamento, municipio, nombre);
    }
}
