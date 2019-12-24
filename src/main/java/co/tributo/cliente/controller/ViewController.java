package co.tributo.cliente.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

@RequestMapping({ "/declarar", "/dashboard", "/user-profile", "/vencimientos", "/estado-cuenta", "/mis-contratos", "/login",
"/register", "/verificar/**" })
   public String index() {
       return "forward:/index.html";
   }
} 

