/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.tributo.cliente.controller;


import co.tributo.cliente.model.BcActo;
import co.tributo.cliente.service.BcActoService;
import java.util.List;
import java.util.Optional;
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
public class BcActoRest {
    
    @Autowired
    private BcActoService bcActoService;
    
    @CrossOrigin(origins="*")
    @RequestMapping (value="/rest/v1/actos/list", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE  )
    public List<BcActo> listarBcActo() {
        return bcActoService.findAllBcActo();
    }
    
    @CrossOrigin(origins="*")
    @RequestMapping (value="/rest/v1/actos/{idacto}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE  )
    public Optional<BcActo> findOneBcActo(@PathVariable("idacto") final Integer id) {
        return bcActoService.findAllBcActoByIdActo(id);
    }
   
    
    
    
}
