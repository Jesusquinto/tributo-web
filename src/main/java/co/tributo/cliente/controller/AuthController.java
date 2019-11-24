package co.tributo.cliente.controller;

import co.tributo.cliente.exception.BadRequestException;
import co.tributo.cliente.model.AuthProvider;
import co.tributo.cliente.model.FrUsuario;
import co.tributo.cliente.payload.ApiResponse;
import co.tributo.cliente.payload.AuthResponse;
import co.tributo.cliente.payload.LoginRequest;
import co.tributo.cliente.payload.SignUpRequest;
import co.tributo.cliente.repository.UserRepository;
import co.tributo.cliente.security.TokenProvider;
import co.tributo.cliente.service.FrUsuarioService;
import co.tributo.cliente.service.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenProvider tokenProvider;
    
    @Autowired
    private SendMailService sendMailService;
    
    @Autowired
    private FrUsuarioService frUsuarioService;
    

    
    @GetMapping("/verificar/{codigo}")
    public ResponseEntity<?> verifyUser(@PathVariable("codigo") final String codigo) {
               Map<String, Object> response = new HashMap<>(); 
               try{
                    FrUsuario user = frUsuarioService.findByCode(codigo);
                    if(user != null){
                      user.setVerifiedAccount(0);
                      user.setVerificaionCode(null);
                      user.setExpirationCode(null);
                      user.setFechacreacion(new Date());
                      frUsuarioService.saveFrUsuario(user);
                     sendMailService.sendMailVerificaionSuccess(user);
                    response.put("mensaje", "La cuenta ha sido verificada correctamente");
                    return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
                    }

               }catch(Exception e){
                response.put("mensaje", "Ocurrió un error al verificar la cuenta");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
               }       
                response.put("mensaje", "El codigo no pertenece a ninguna cuenta por verificar");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    
    
    
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
       Map<String, Object> response = new HashMap<>(); 
       try{
            FrUsuario usuario = frUsuarioService.findByEmail(loginRequest.getEmail());
       if(usuario.getVerifiedAccount() == 0){
             Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.createToken(authentication);
        return ResponseEntity.ok(new AuthResponse(token));
       }
       }catch(Exception e){
            response.put("mensaje", "Ocurrió un error al ");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
       }
      
       
       response.put("mensaje", "La cuenta no ha sido verificada");
       return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        Map<String, Object> response = new HashMap<>(); 
        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new BadRequestException("Email address already in use.");
        }

        // Creating user's account
        try{
        FrUsuario user = new FrUsuario();
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getPassword());
        user.setProvider(AuthProvider.local);
        user.setVerifiedAccount(1);
        user.setFechacreacion(new Date());
        String key = UUID.randomUUID().toString() + "-" + signUpRequest.getName().replace(" ", "");
        user.setVerificaionCode(key);
            Calendar cal = Calendar.getInstance(); // creates calendar
            cal.setTime(new Date()); // sets calendar time/date
            cal.add(Calendar.DAY_OF_YEAR, 1); // adds one hour
            user.setExpirationCode(cal.getTime());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        FrUsuario result = userRepository.save(user);
        sendMailService.sendMailVerification(user);
        }catch(Exception e){
                           
              response.put("mensaje", "Ocurrió un error al fimar la llave");
                    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        

       response.put("mensaje", "El usuario ha sido registrado con exito!");
       return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
