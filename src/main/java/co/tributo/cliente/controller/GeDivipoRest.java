package co.tributo.cliente.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import co.tributo.cliente.model.GeDivipo;
import co.tributo.cliente.service.GeDivipoService;

@RestController
@CrossOrigin(origins="*")
@RequestMapping ("/rest/v1/")
public class GeDivipoRest {
	
	@Autowired
	private GeDivipoService geDivipoService;
	
	@GetMapping("/gedivipo/list")
	public List<GeDivipo> findAll() {
        return geDivipoService.findAllGeDivipos();
    }
	
	@GetMapping("/gedivipo/{id}")
	public Optional<GeDivipo> findGeDivipoById(@PathVariable("id") final Integer id){
	return geDivipoService.findAllBcActoByIdGeDivipo(id);
		
	}
	 
}