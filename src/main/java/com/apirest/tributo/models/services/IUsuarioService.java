package com.apirest.tributo.models.services;

import com.apirest.tributo.models.entity.Usuario;

public interface IUsuarioService {
  public Usuario findByUsername(String username);
}
