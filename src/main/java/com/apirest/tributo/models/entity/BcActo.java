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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "bc_acto")
public class BcActo implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id_acto")
  private Integer idActo;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 45)
  @Column(name = "nombre_acto")
  private String nombreActo;
  @Size(max = 80)
  @Column(name = "descripcion")
  private String descripcion;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 4)
  @Column(name = "estado")
  private String estado;

  @JsonIgnore
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkBcActo")
  private List<BcActoEntidad> bcActoEntidadList;

  @JsonIgnore
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkTipoActo")
  private List<BcActoDetalle> bcActoDetalleList;

  public BcActo() {
  }

  public BcActo(Integer idActo) {
    this.idActo = idActo;
  }

  public BcActo(Integer idActo, String nombreActo, String estado) {
    this.idActo = idActo;
    this.nombreActo = nombreActo;
    this.estado = estado;
  }

  public Integer getIdActo() {
    return idActo;
  }

  public void setIdActo(Integer idActo) {
    this.idActo = idActo;
  }

  public String getNombreActo() {
    return nombreActo;
  }

  public void setNombreActo(String nombreActo) {
    this.nombreActo = nombreActo;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

  public List<BcActoEntidad> getBcActoEntidadList() {
    return bcActoEntidadList;
  }

  public void setBcActoEntidadList(List<BcActoEntidad> bcActoEntidadList) {
    this.bcActoEntidadList = bcActoEntidadList;
  }

  public List<BcActoDetalle> getBcActoDetalleList() {
    return bcActoDetalleList;
  }

  public void setBcActoDetalleList(List<BcActoDetalle> bcActoDetalleList) {
    this.bcActoDetalleList = bcActoDetalleList;
  }
  
}
