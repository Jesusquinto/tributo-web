package com.apirest.tributo.controllers;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.tributo.models.entity.BcContratantes;
import com.apirest.tributo.models.entity.BcEntidadContratantes;
import com.apirest.tributo.models.services.BcContratantesService;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/rest/v1/")
public class BcContratantesRest {
	
    @Autowired
    private BcContratantesService contratantesService;

    @GetMapping("/contratantes")
    public List<BcContratantes> index() {
        return contratantesService.findAllBcContratantes();
    }
    
    @GetMapping("/contratantes/entidad/{idEntidad}")
    public List<BcEntidadContratantes> findContratantesByEntidad(@PathVariable("idEntidad")Integer idEntidad) {
        return contratantesService.findContratanteByEntidad(idEntidad);
    }
    
}
