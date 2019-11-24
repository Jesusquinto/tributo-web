package com.apirest.tributo.models.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.apirest.tributo.models.entity.Role;


public interface BcRoleRepository extends CrudRepository<Role, Long>{
	
	public Role findByNombre(String nombre);
	
	public Optional<Role> findById(Long id);
	
}
