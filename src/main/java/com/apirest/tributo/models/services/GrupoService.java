package com.apirest.tributo.models.services;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apirest.tributo.models.dao.GrupoRepository;
import com.apirest.tributo.models.entity.Grupos;

@Service
@Transactional
public class GrupoService {

	@Autowired
	GrupoRepository grupoRepository;
	
	public List<Grupos> finalAllGrupos(){
		return grupoRepository.findAll();
	}
	
	public Grupos finalGrupoById(Integer idGrupo){
		return grupoRepository.findById(idGrupo).orElse(null);
	}
	
	public Grupos saveGrupo(Grupos grupo){
		return grupoRepository.save(grupo);
	}
	
	public void deleteGrupoById(Integer id) {
		grupoRepository.deleteById(id);
	}
}
