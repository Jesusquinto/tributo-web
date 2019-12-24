/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.tributo.cliente.controller;

import co.tributo.cliente.model.FrUsuarioActo;
import co.tributo.cliente.security.CurrentUser;
import co.tributo.cliente.security.UserPrincipal;
import co.tributo.cliente.service.FrUsuarioActoService;
import co.tributo.cliente.service.SendMailService;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jesus
 */
@RestController
@CrossOrigin(origins="*")
@RequestMapping ("/rest/v1/")
public class sendMailController {
    
    @Autowired
    private SendMailService sendMailService;
    @Autowired
    private FrUsuarioActoService frUsuarioActoService;
    
    
    @PostMapping("/generarllave/{idFrUsuarioActo}")
    public ResponseEntity<?> generarLlave(@PathVariable("idFrUsuarioActo") final int idFrUsuarioActo, @CurrentUser UserPrincipal userPrincipal) throws MessagingException{
       Map<String, Object> response = new HashMap<>(); 
       FrUsuarioActo tramite;
       
        try{
           tramite = frUsuarioActoService.findById(idFrUsuarioActo);
           if(tramite.getExpiracionKeycode() != null){
               Date fechaExpiracion = tramite.getExpiracionKeycode();
               Date fechaActual = new Date();
                if(fechaActual.compareTo(fechaExpiracion) < 0){
                    response.put("mensaje", "Ya tiene una llave generada para el tramite!");
                    response.put("fechaExpiracion", tramite.getExpiracionKeycode());
                    response.put("estado", 1);
                    return new ResponseEntity<>(response, HttpStatus.ACCEPTED);  
                }
           }
        
          sendMailService.sendMail(userPrincipal.getEmail(), userPrincipal.getName(), idFrUsuarioActo);
          tramite = frUsuarioActoService.findById(idFrUsuarioActo);
       }catch(Exception e){
           response.put("error", "Ocurrió un error al generar la llave!");
           return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
       }
   
       response.put("mensaje", "La llave ha sido generada con exito!");
       response.put("fechaExpiracion",tramite.getExpiracionKeycode());
       response.put("estado", 0);
       return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    
    
    @PostMapping("/firmar/{idFrUsuarioActo}/{llave}")
     public ResponseEntity<?> generarLlave(@PathVariable("idFrUsuarioActo") final int idFrUsuarioActo , @PathVariable("llave") final String llave, @CurrentUser UserPrincipal userPrincipal){
                Map<String, Object> response = new HashMap<>(); 
                FrUsuarioActo tramite;
                try{      
                    tramite = frUsuarioActoService.findById(idFrUsuarioActo);
                    if(tramite.getKeycode().equals(llave)){                  
                         tramite.setEstado("PR");
                         tramite.setFechaPresentacion(new Date());
                         frUsuarioActoService.saveFrUsuarioActo(tramite);
                         response.put("mensaje", "El tramite No."+tramite.getConsecutivoActo() +" ha sido firmado y presentado con exito!");
                         return new ResponseEntity<>(response, HttpStatus.CREATED);
                    }
                    
                }catch(Exception e){
                    response.put("mensaje", "Ocurrió un error al fimar la llave");
                    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
                }
                                      
                response.put("mensaje", "La llave es incorrecta");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
     }
    
    
    
}
