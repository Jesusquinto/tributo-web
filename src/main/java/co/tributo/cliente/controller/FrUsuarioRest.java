/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.tributo.cliente.controller;

import co.tributo.cliente.model.FrUsuario;
import co.tributo.cliente.service.FrUsuarioService;
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
public class FrUsuarioRest {
    
    @Autowired
    private FrUsuarioService frUsuarioService;
    
    
    //GET
    @CrossOrigin(origins="*")
    @RequestMapping (value="/rest/v1/frusuarios/list", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE  )
    public List<FrUsuario> listarFrUsuario() {
        return frUsuarioService.findAllFrUsuario();
               
    } 
    
    
    @CrossOrigin(origins="*")
    @RequestMapping (value="/rest/v1/frusuarios/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE  )
    public Optional<FrUsuario> listarFrUsuarioById(@PathVariable("id") final Integer id) {
        return frUsuarioService.findOneFrUsuario(id);
    }
    
    
   // @CrossOrigin(origins="*")
    //@RequestMapping (value="/rest/v1/frusuarios/email/{email:.+}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE  )
   // public List<FrUsuario> findByEmail(@PathVariable("email") final String email) {
    //    System.out.println("Email es: "+email);
     //   return frUsuarioService.findByEmail(email);
   // }
    
    
    
    //POST
    @CrossOrigin(origins="*")
    @RequestMapping (value="/rest/v1/frusuarios/save", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE  )
    public FrUsuario saveFrUsuario(@RequestBody FrUsuario frUsuario) {
       return frUsuarioService.saveFrUsuario(frUsuario);
    }  
    
    
    
   
    
    
}
