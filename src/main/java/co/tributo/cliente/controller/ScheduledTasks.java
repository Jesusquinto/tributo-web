package co.tributo.cliente.controller;

import co.tributo.cliente.service.FrUsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class ScheduledTasks {
    
    @Autowired
    private FrUsuarioService frUsuarioService;
  
@Scheduled(fixedRate = 3600000)
public void accountVerficationCheck(){
        frUsuarioService.checkVerifiedAccount();
        System.out.println("CUENTAS SIN VERIFICAR EXPIRADAS ELIMINADAS "+ new java.util.Date());
}
    
    
    
    
}