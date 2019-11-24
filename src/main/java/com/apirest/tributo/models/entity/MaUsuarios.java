/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apirest.tributo.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
//import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "ma_usuarios")
public class MaUsuarios implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_ma_usuarios", unique = true)
  private Integer idMaUsuarios;

  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 45)
  @Column(name = "username")
  private String username;

  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 45)
  @Column(name = "documento")
  private String documento;

  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 100)
  @Column(name = "nombre_largo")
  private String nombreLargo;

  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 100)
  @Column(name = "password")
  private String password;

  private Boolean enabled;

  @JoinColumn(name = "fk_ma_entidad", referencedColumnName = "id_entidad")
  @ManyToOne(optional = false)
  private BcEntidad fkMaEntidad;

  @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinTable(name = "ma_usuarios_perfiles", joinColumns = @JoinColumn(name = "id_ma_usuarios"),
      inverseJoinColumns = @JoinColumn(name = "id_ma_perfiles"))
  private List<MaPerfiles> fkMaPerfil;

  public MaUsuarios() {
	  
  }
  
  public List<MaPerfiles> getFkMaPerfil() {
    return fkMaPerfil;
  }

  public void setFkMaPerfil(List<MaPerfiles> fkMaPerfil) {
    this.fkMaPerfil = fkMaPerfil;
  }

  public MaUsuarios(Integer idMaUsuarios) {
    this.idMaUsuarios = idMaUsuarios;
  }

  public Integer getIdMaUsuarios() {
    return idMaUsuarios;
  }

  public void setIdMaUsuarios(Integer idMaUsuarios) {
    this.idMaUsuarios = idMaUsuarios;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getDocumento() {
    return documento;
  }

  public void setDocumento(String documento) {
    this.documento = documento;
  }

  public String getNombreLargo() {
    return nombreLargo;
  }

  public void setNombreLargo(String nombreLargo) {
    this.nombreLargo = nombreLargo;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public BcEntidad getFkMaEntidad() {
    return fkMaEntidad;
  }

  public void setFkMaEntidad(BcEntidad fkMaEntidad) {
    this.fkMaEntidad = fkMaEntidad;
  }

  public Boolean getEnabled() {
    return enabled;
  }

  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }

  private static final long serialVersionUID = 1L;

}
