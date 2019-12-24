/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.tributo.cliente.controller;


import co.tributo.cliente.model.BcActoEntidad;
import co.tributo.cliente.model.BcEntidad;
import co.tributo.cliente.service.BcActoEntidadService;
import java.util.List;
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
public class BcActoEntidadRest {
    
    @Autowired
    private BcActoEntidadService bcActoEntidadService;
    
    @CrossOrigin(origins="*")
    @RequestMapping (value="/rest/v1/actosentidad/list", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE  )
    public List<BcActoEntidad> listarBcActoEntidad() {
        return bcActoEntidadService.findAllBcActoEntidad();
               
    }
    
    @CrossOrigin(origins="*")
    @RequestMapping (value="/rest/v1/actosentidad/{idEntidad}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE  )
    public List<BcActoEntidad> findOneBcActo(@PathVariable("idEntidad") final Integer id) {
        return bcActoEntidadService.findByIdEntidad(id);
    }
    
    
    //POST
    @CrossOrigin(origins="*")
    @RequestMapping (value="/rest/v1/actosentidad/new", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE  )
    public BcActoEntidad saveBcEntEntidad(@RequestBody BcActoEntidad bcActoEntidad) {

        return bcActoEntidadService.saveBcActoEntidad(bcActoEntidad);
    }  
    
    
    
    
    //PUT
    @CrossOrigin(origins="*")
    @RequestMapping (value="/rest/v1/actosentidad/{id}", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE  )
    public BcActoEntidad updateBcEntEntidad(@PathVariable("id") final Integer id, @RequestBody BcActoEntidad bcActoEntidad) {

        return bcActoEntidadService.actualizarBcActoEntidad(id, bcActoEntidad);
    }  
    
    
    @CrossOrigin(origins="*")
    @RequestMapping (value="/rest/v1/actosentidad/filter/{codigo}/{nombre}/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE  )
    public List<BcActoEntidad> filterByVarios(@PathVariable("codigo") final String codigo, 
                                              @PathVariable("nombre") final String nombre,
                                              @PathVariable("id") final Integer id
                                             ) {
        return bcActoEntidadService.filterByVarios(codigo, nombre, id);
    }
    
    
}
