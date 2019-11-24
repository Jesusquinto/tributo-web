/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.tributo.cliente.controller;


import co.tributo.cliente.model.BcTributos;
import co.tributo.cliente.service.BcTributosService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Ricardo
 */
@RestController
public class BcTributosRest {
    
    @Autowired
    private BcTributosService bcTributosService;
    
     //GET
    @CrossOrigin(origins="*")
    @RequestMapping (value="/rest/v1/tributos/list", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE  )
    public List<BcTributos> listarBcEntidadTributos() {
        return bcTributosService.findAllBcTributos();
               
    } 
    
}
