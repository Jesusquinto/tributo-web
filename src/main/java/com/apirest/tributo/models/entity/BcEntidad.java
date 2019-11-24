package com.apirest.tributo.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author YAMID
 */
@Entity
@Table(name = "bc_entidad")
@XmlRootElement

public class BcEntidad implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id_entidad")
  private Integer idEntidad;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 100)
  @Column(name = "nombre")
  private String nombre;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 200)
  @Column(name = "direccion")
  private String direccion;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 50)
  @Column(name = "telefono")
  private String telefono;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 20)
  @Column(name = "nit")
  private String nit;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 200)
  @Column(name = "contacto_nombre")
  private String contactoNombre;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 50)
  @Column(name = "contacto_cargo")
  private String contactoCargo;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 50)
  @Column(name = "contacto_telefono")
  private String contactoTelefono;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 50)
  @Column(name = "contacto_email")
  private String contactoEmail;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 50)
  @Column(name = "licencia")
  private String licencia;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 1)
  @Column(name = "orden")
  private String orden;
  @Basic(optional = false)
  @NotNull
  @Column(name = "usuarios")
  private int usuarios;
  @Basic(optional = false)
  @NotNull
  @Column(name = "sesionesconcurrentes")
  private int sesionesconcurrentes;
  @Size(max = 50)
  @Column(name = "funciones")
  private String funciones;
  @Size(max = 2)
  @Column(name = "estado")
  private String estado;
  @Lob
  @Size(max = 65535)
  @Column(name = "logo")
  private String logo;

  @JsonIgnore
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkBcEntidad")
  private List<BcActoEntidad> bcActoEntidadList;

  @JsonIgnore
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkEntidad")
  private List<BcEntidadContratantes> bcEntidadContratantesList;

  @JsonIgnore
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkMaEntidad")
  private List<MaUsuarios> maUsuariosList;

  @JsonIgnore
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "identidad")
  private List<BcEntidadTributos> bcEntidadTributosList;

 
  @JoinColumn(name = "fk_gedivipo", referencedColumnName = "id_ge_divipo")
  @ManyToOne
  private GeDivipo fkGedivipo;

  @XmlTransient
  public List<BcEntidadContratantes> getBcEntidadContratantesList() {
    return bcEntidadContratantesList;
  }

  public void setBcEntidadContratantesList(List<BcEntidadContratantes> bcEntidadContratantesList) {
    this.bcEntidadContratantesList = bcEntidadContratantesList;
  }

  @XmlTransient
  public List<MaUsuarios> getMaUsuariosList() {
    return maUsuariosList;
  }

  public void setMaUsuariosList(List<MaUsuarios> maUsuariosList) {
    this.maUsuariosList = maUsuariosList;
  }

  @XmlTransient
  public List<BcEntidadTributos> getBcEntidadTributosList() {
    return bcEntidadTributosList;
  }

  public void setBcEntidadTributosList(List<BcEntidadTributos> bcEntidadTributosList) {
    this.bcEntidadTributosList = bcEntidadTributosList;
  }

  @XmlTransient
  public List<BcActoEntidad> getBcActoEntidadList() {
    return bcActoEntidadList;
  }

  public void setBcActoEntidadList(List<BcActoEntidad> bcActoEntidadList) {
    this.bcActoEntidadList = bcActoEntidadList;
  }

  public BcEntidad() {
  }

  public BcEntidad(Integer idEntidad) {
    this.idEntidad = idEntidad;
  }

  public Integer getIdEntidad() {
    return idEntidad;
  }

  public void setIdEntidad(Integer idEntidad) {
    this.idEntidad = idEntidad;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
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

  public String getNit() {
    return nit;
  }

  public void setNit(String nit) {
    this.nit = nit;
  }

  public String getContactoNombre() {
    return contactoNombre;
  }

  public void setContactoNombre(String contactoNombre) {
    this.contactoNombre = contactoNombre;
  }

  public String getContactoCargo() {
    return contactoCargo;
  }

  public void setContactoCargo(String contactoCargo) {
    this.contactoCargo = contactoCargo;
  }

  public String getContactoTelefono() {
    return contactoTelefono;
  }

  public void setContactoTelefono(String contactoTelefono) {
    this.contactoTelefono = contactoTelefono;
  }

  public String getContactoEmail() {
    return contactoEmail;
  }

  public void setContactoEmail(String contactoEmail) {
    this.contactoEmail = contactoEmail;
  }

  public String getLicencia() {
    return licencia;
  }

  public void setLicencia(String licencia) {
    this.licencia = licencia;
  }

  public String getOrden() {
    return orden;
  }

  public void setOrden(String orden) {
    this.orden = orden;
  }

  public int getUsuarios() {
    return usuarios;
  }

  public void setUsuarios(int usuarios) {
    this.usuarios = usuarios;
  }

  public int getSesionesconcurrentes() {
    return sesionesconcurrentes;
  }

  public void setSesionesconcurrentes(int sesionesconcurrentes) {
    this.sesionesconcurrentes = sesionesconcurrentes;
  }

  public String getFunciones() {
    return funciones;
  }

  public void setFunciones(String funciones) {
    this.funciones = funciones;
  }

  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

  public String getLogo() {
    return logo;
  }

  public void setLogo(String logo) {
    this.logo = logo;
  }

  public GeDivipo getFkGedivipo() {
    return fkGedivipo;
  }

  public void setFkGedivipo(GeDivipo fkGedivipo) {
    this.fkGedivipo = fkGedivipo;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (idEntidad != null ? idEntidad.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    if (!(object instanceof BcEntidad)) {
      return false;
    }
    BcEntidad other = (BcEntidad) object;
    return !((this.idEntidad == null && other.idEntidad != null) || (this.idEntidad != null && !this.idEntidad.equals(other.idEntidad)));
  }

  @Override
  public String toString() {
    return "com.springboot.backend.apirest.models.entity.BcEntidad[ idEntidad=" + idEntidad + " ]";
  }

}