package com.apirest.tributo.models.services;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apirest.tributo.models.dao.BcRoleRepository;
import com.apirest.tributo.models.dao.IUsuarioDao;
import com.apirest.tributo.models.entity.Role;
import com.apirest.tributo.models.entity.Usuario;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@Service
public class UsuarioService implements IUsuarioService, UserDetailsService {

	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	private final Logger logger = LoggerFactory.getLogger(UsuarioService.class);

	
	@Autowired
	private BcRoleRepository roleDao;

	@Autowired
	private IUsuarioDao usuarioDao;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Usuario usuario = usuarioDao.findByUsername(username);

		if (usuario == null) {
			logger.error("Error en el Login: no existe el usuario '" + username + "' En el Sistema!");
			throw new UsernameNotFoundException(
					"Error en el Login: no existe el usuario '" + username + "' En el Sistema!");
		}

		List<GrantedAuthority> authorities = usuario.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getNombre()))
				.peek(authority -> logger.info("Role :" + authority.getAuthority())).collect(Collectors.toList());

		return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true,
				authorities);
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findByUsername(String username) {
		return usuarioDao.findByUsername(username);
	}

	public List<Usuario> findAllUsers() {
		return usuarioDao.findAllUsers();
	}

	public List<Usuario> findByIdEntidad(Integer idEntidad) {
		return usuarioDao.findByIdEntidad(idEntidad);
	}

	public Usuario crearNuevoUsuario(Usuario usuario) {		
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		
		Usuario userNew = new Usuario();
		
		userNew.setApellido(usuario.getApellido());
		userNew.setDocumento(usuario.getDocumento());
		userNew.setEmail(usuario.getEmail());
		userNew.setEnabled(usuario.getEnabled());
		userNew.setFkMaEntidad(usuario.getFkMaEntidad());
		userNew.setNombre(usuario.getNombre());
		userNew.setUsername(usuario.getUsername());
		userNew.setPassword(passwordEncoder().encode(usuario.getPassword()));
		
		Role userRole = roleDao.findByNombre("ROLE_ADMIN");
		
		userNew.setRoles(Arrays.asList(userRole));
		
		String json;
		try {
			json = ow.writeValueAsString(userNew);
			logger.info("usuario creado: " + json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return usuarioDao.save(userNew);
	}

}
