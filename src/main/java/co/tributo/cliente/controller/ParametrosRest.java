/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.tributo.cliente.controller;

import co.tributo.cliente.service.parametrosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import co.tributo.cliente.model.Parametros;
import java.util.List;
/**
 *
 * @author jesus
 */
@RestController
@CrossOrigin(origins="*")
@RequestMapping ("/rest/v1/")
public class ParametrosRest {
    
        @Autowired
        private parametrosService ParametrosService; 
        
        
         @GetMapping("/parametros/{idEntidad}/{idGrupo}")
         	public List<Parametros> findByEntidadGrupo(@PathVariable("idEntidad") final Integer idEntidad, @PathVariable("idGrupo") final  Integer idGrupo){
                        return this.ParametrosService.findByEntidadAndGrupo(idEntidad, idGrupo);
                }

        
    
    
}
