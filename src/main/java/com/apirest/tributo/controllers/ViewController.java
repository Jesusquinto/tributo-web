package com.apirest.tributo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

@RequestMapping({  "/dasboard", "/entidades", "/empresa-perfil", "/tramites", "/tramites-admin", "/tributos",
"/tributos-admin", "/parametros-admin", "/parametros-general", "/seguridad", "/administracion",
"/administracion", "/gestion", "/estadisticas", "/reportes", "/contratantes", "/bancos",
"/login"})
   public String index() {
       return "forward:/index.html";
   }
} 