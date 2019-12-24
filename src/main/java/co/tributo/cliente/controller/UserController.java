package co.tributo.cliente.controller;

import co.tributo.cliente.exception.ResourceNotFoundException;
import co.tributo.cliente.model.FrUsuario;
import co.tributo.cliente.repository.UserRepository;
import co.tributo.cliente.security.CurrentUser;
import co.tributo.cliente.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public FrUsuario getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }
    
  @GetMapping("/user/isLogged")
  @PreAuthorize("hasRole('USER')")
  public boolean isLogged(@CurrentUser UserPrincipal userPrincipal){
      if(userPrincipal.getId() !=null){
          return true;
      }
      System.out.print(userPrincipal.getId());
      return false;
  }
  
  @CrossOrigin(origins="*")
  @PreAuthorize("hasRole('USER')")
  @RequestMapping (value="/rest/v1/frusuarios/update", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE  )
  public FrUsuario updateUsuarios(@RequestBody FrUsuario frUsuario,@CurrentUser UserPrincipal userPrincipal) {
  	
	  if (userRepository.findById(userPrincipal.getId())==null ){
          //Sacar una excepción   
     }else {
    	 frUsuario.setIdUsuario(userPrincipal.getId());
         return userRepository.save(frUsuario);
     }
     return frUsuario; 
  }  
  
   
}
