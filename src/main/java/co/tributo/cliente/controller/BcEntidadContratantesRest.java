package co.tributo.cliente.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import co.tributo.cliente.model.BcEntidadContratantes;
import co.tributo.cliente.service.BcEntidadContratantesService;

@RestController
@CrossOrigin(origins="*")
@RequestMapping ("/rest/v1/")
public class BcEntidadContratantesRest {
	
	@Autowired
	private BcEntidadContratantesService bcEntidadContratanteService;
	
	@GetMapping("/entidadcontratantes/{idEntidad}")
	public List<BcEntidadContratantes> findOneBcActo(@PathVariable("idEntidad") final Integer id) {
        return bcEntidadContratanteService.findContratantesByIdEntidad(id);
    }
	 
}