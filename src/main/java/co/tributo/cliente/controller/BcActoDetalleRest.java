/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.tributo.cliente.controller;



import co.tributo.cliente.model.BcActoDetalle;
import co.tributo.cliente.service.BcActoDetalleService;
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
public class BcActoDetalleRest {
    
    @Autowired
    private BcActoDetalleService bcActoDetalleService;
    
    @CrossOrigin(origins="*")
    @RequestMapping (value="/rest/v1/actosdetalle/list", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE  )
    public List<BcActoDetalle> listarBcActo() {
        return bcActoDetalleService.findBcActoDetalle();
    }
    
    
    @CrossOrigin(origins="*")
    @RequestMapping (value="/rest/v1/actosdetalle/actos/{identidad}/{idacto}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE  )
    public List<BcActoDetalle> listarByIdActo(@PathVariable("identidad") final Integer identidad, @PathVariable("idacto") final Integer idacto) {
        
        return bcActoDetalleService.findByIdActo(identidad, idacto);
    }
}
