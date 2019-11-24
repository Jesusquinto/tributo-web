/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apirest.tributo.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ricardo
 */
@Entity
@Table(name = "fr_usuario")
@XmlRootElement

public class FrUsuario implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id_usuario")
  private Integer idUsuario;

  @Size(max = 50)
  @Column(name = "tipo_identificacion")
  private String tipoIdentificacion;

  @Size(max = 100)
  @Column(name = "identificacion")
  private String identificacion;

  @Basic(optional = false)
  @Size(min = 1, max = 100)
  @Column(name = "name")
  private String nombre;

  @Size(max = 255)
  @Column(name = "razonsocial")
  private String razonsocial;

  @Size(max = 50)
  @Column(name = "sexo")
  private String sexo;

  @Column(name = "fecha_nacimiento")
  @Temporal(TemporalType.DATE)
  private Date fechaNacimiento;

  @Size(max = 255)
  @Column(name = "direccion")
  private String direccion;

  @Size(max = 100)
  @Column(name = "telefono")
  private String telefono;

  @Size(max = 100)
  @Column(name = "celular")
  private String celular;

  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 255)
  @Column(name = "email")
  private String email;

  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 100)
  @Column(name = "usuario")
  private String usuario;

  @Column(name = "tipousuario")
  private String tipousuario;

  @Basic(optional = false)
  @Column(name = "esadministrador")
  private boolean esadministrador;

  @Column(name = "ultima_conexion")
  @Temporal(TemporalType.TIMESTAMP)
  private Date ultimaConexion;

  @Size(max = 255)
  @Column(name = "ultima_geoposicion")
  private String ultimaGeoposicion;

  @Size(max = 100)
  @Column(name = "ultima_ip")
  private String ultimaIp;

  @Size(max = 255)
  @Column(name = "ultimo_so")
  private String ultimoSo;

  @Size(max = 255)
  @Column(name = "ultimo_navegador")
  private String ultimoNavegador;

  @Size(max = 255)
  @Column(name = "ultimasesion")
  private String ultimasesion;

  @Column(name = "acceso_multiple")
  private Boolean accesoMultiple;

  @Size(max = 255)
  @Column(name = "codigoreinicio")
  private String codigoreinicio;

  @Column(name = "fechahorareinicio")
  @Temporal(TemporalType.TIMESTAMP)
  private Date fechahorareinicio;

  @Column(name = "fechacreacion")
  @Temporal(TemporalType.TIMESTAMP)
  private Date fechacreacion;


  @Size(max = 4)
  @Column(name = "tipocontribuyente")
  private String tipocontribuyente;

  @Size(max = 4)
  @Column(name = "estado")
  private String estado;

  @Size(max = 255)
  @Column(name = "image_url")
  private String urlImagen;

  @JsonIgnore
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkUsuario")
  private List<FrUsuarioActo> frUsuarioActoList;

  public Integer getIdUsuario() {
    return idUsuario;
  }

  public void setIdUsuario(Integer idUsuario) {
    this.idUsuario = idUsuario;
  }

  public String getTipoIdentificacion() {
    return tipoIdentificacion;
  }

  public void setTipoIdentificacion(String tipoIdentificacion) {
    this.tipoIdentificacion = tipoIdentificacion;
  }

  public String getIdentificacion() {
    return identificacion;
  }

  public void setIdentificacion(String identificacion) {
    this.identificacion = identificacion;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getRazonsocial() {
    return razonsocial;
  }

  public void setRazonsocial(String razonsocial) {
    this.razonsocial = razonsocial;
  }

  public String getSexo() {
    return sexo;
  }

  public void setSexo(String sexo) {
    this.sexo = sexo;
  }

  public Date getFechaNacimiento() {
    return fechaNacimiento;
  }

  public void setFechaNacimiento(Date fechaNacimiento) {
    this.fechaNacimiento = fechaNacimiento;
  }

  public String getDireccion() {
    return direccion;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public String getCelular() {
    return celular;
  }

  public void setCelular(String celular) {
    this.celular = celular;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getUsuario() {
    return usuario;
  }

  public void setUsuario(String usuario) {
    this.usuario = usuario;
  }

  public String getTipousuario() {
    return tipousuario;
  }

  public void setTipousuario(String tipousuario) {
    this.tipousuario = tipousuario;
  }

  public boolean getEsadministrador() {
    return esadministrador;
  }

  public void setEsadministrador(boolean esadministrador) {
    this.esadministrador = esadministrador;
  }

  public Date getUltimaConexion() {
    return ultimaConexion;
  }

  public void setUltimaConexion(Date ultimaConexion) {
    this.ultimaConexion = ultimaConexion;
  }

  public String getUltimaGeoposicion() {
    return ultimaGeoposicion;
  }

  public void setUltimaGeoposicion(String ultimaGeoposicion) {
    this.ultimaGeoposicion = ultimaGeoposicion;
  }

  public String getUltimaIp() {
    return ultimaIp;
  }

  public void setUltimaIp(String ultimaIp) {
    this.ultimaIp = ultimaIp;
  }

  public String getUltimoSo() {
    return ultimoSo;
  }

  public void setUltimoSo(String ultimoSo) {
    this.ultimoSo = ultimoSo;
  }

  public String getUltimoNavegador() {
    return ultimoNavegador;
  }

  public void setUltimoNavegador(String ultimoNavegador) {
    this.ultimoNavegador = ultimoNavegador;
  }

  public String getUltimasesion() {
    return ultimasesion;
  }

  public void setUltimasesion(String ultimasesion) {
    this.ultimasesion = ultimasesion;
  }

  public Boolean getAccesoMultiple() {
    return accesoMultiple;
  }

  public void setAccesoMultiple(Boolean accesoMultiple) {
    this.accesoMultiple = accesoMultiple;
  }

  public String getCodigoreinicio() {
    return codigoreinicio;
  }

  public void setCodigoreinicio(String codigoreinicio) {
    this.codigoreinicio = codigoreinicio;
  }

  public Date getFechahorareinicio() {
    return fechahorareinicio;
  }

  public void setFechahorareinicio(Date fechahorareinicio) {
    this.fechahorareinicio = fechahorareinicio;
  }

  public Date getFechacreacion() {
    return fechacreacion;
  }

  public void setFechacreacion(Date fechacreacion) {
    this.fechacreacion = fechacreacion;
  }

  public String getTipocontribuyente() {
    return tipocontribuyente;
  }

  public void setTipocontribuyente(String tipocontribuyente) {
    this.tipocontribuyente = tipocontribuyente;
  }

  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

  public String getUrlImagen() {
    return urlImagen;
  }

  public void setUrlImagen(String urlImagen) {
    this.urlImagen = urlImagen;
  }

  @XmlTransient
  public List<FrUsuarioActo> getFrUsuarioActoList() {
    return frUsuarioActoList;
  }

  public void setFrUsuarioActoList(List<FrUsuarioActo> frUsuarioActoList) {
    this.frUsuarioActoList = frUsuarioActoList;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (idUsuario != null ? idUsuario.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    if (!(object instanceof FrUsuario)) {
      return false;
    }
    FrUsuario other = (FrUsuario) object;
    return !((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario)));
  }

  @Override
  public String toString() {
    return "com.gts.tributo.modelo.FrUsuario[ idUsuario=" + idUsuario + " ]";
  }

}
