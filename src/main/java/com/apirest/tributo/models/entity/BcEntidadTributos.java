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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "bc_entidad_tributos")
@XmlRootElement

public class BcEntidadTributos implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id_ent_tributo")
  private Integer idEntTributo;

  @Basic(optional = false)
  @Column(name = "fechacreacion")
  @Temporal(TemporalType.TIMESTAMP)
  private Date fechacreacion;

  @Basic(optional = false)
  @NotNull
  @Column(name = "idusuario")
  private int idusuario;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 1)
  @Column(name = "estado")
  private String estado;
  @Size(max = 500)
  @Column(name = "parametro_tributo")
  private String parametroTributo;
  @Column(name = "fecha_inicio")
  @Temporal(TemporalType.TIMESTAMP)
  private Date fechaInicio;
  @Column(name = "fecha_final")
  @Temporal(TemporalType.TIMESTAMP)
  private Date fechaFinal;

  @JsonIgnore
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkEntidadTributo")
  private List<BcActoDetalle> bcActoDetalleList;

  @JoinColumn(name = "identidad", referencedColumnName = "id_entidad")
  @ManyToOne(optional = false)
  private BcEntidad identidad;

  @JoinColumn(name = "idtributo", referencedColumnName = "id_tributo")
  @ManyToOne(optional = false)
  private BcTributos idtributo;

  public BcEntidadTributos() {
  }

  public BcEntidadTributos(Integer idEntTributo) {
    this.idEntTributo = idEntTributo;
  }

  public BcEntidadTributos(Integer idEntTributo, Date fechacreacion, int idusuario, String estado) {
    this.idEntTributo = idEntTributo;
    this.fechacreacion = fechacreacion;
    this.idusuario = idusuario;
    this.estado = estado;
  }

  public Integer getIdEntTributo() {
    return idEntTributo;
  }

  public void setIdEntTributo(Integer idEntTributo) {
    this.idEntTributo = idEntTributo;
  }

  public Date getFechacreacion() {
    return fechacreacion;
  }

  public void setFechacreacion(Date fechacreacion) {
    this.fechacreacion = fechacreacion;
  }

  public int getIdusuario() {
    return idusuario;
  }

  public void setIdusuario(int idusuario) {
    this.idusuario = idusuario;
  }

  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

  public String getParametroTributo() {
    return parametroTributo;
  }

  public void setParametroTributo(String parametroTributo) {
    this.parametroTributo = parametroTributo;
  }

  public Date getFechaInicio() {
    return fechaInicio;
  }

  public void setFechaInicio(Date fechaInicio) {
    this.fechaInicio = fechaInicio;
  }

  public Date getFechaFinal() {
    return fechaFinal;
  }

  public void setFechaFinal(Date fechaFinal) {
    this.fechaFinal = fechaFinal;
  }

  @XmlTransient
  public List<BcActoDetalle> getBcActoDetalleList() {
    return bcActoDetalleList;
  }

  public void setBcActoDetalleList(List<BcActoDetalle> bcActoDetalleList) {
    this.bcActoDetalleList = bcActoDetalleList;
  }

  public BcEntidad getIdentidad() {
    return identidad;
  }

  public void setIdentidad(BcEntidad identidad) {
    this.identidad = identidad;
  }

  public BcTributos getIdtributo() {
    return idtributo;
  }

  public void setIdtributo(BcTributos idtributo) {
    this.idtributo = idtributo;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (idEntTributo != null ? idEntTributo.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    if (!(object instanceof BcEntidadTributos)) {
      return false;
    }
    BcEntidadTributos other = (BcEntidadTributos) object;
    if ((this.idEntTributo == null && other.idEntTributo != null) || (this.idEntTributo != null && !this.idEntTributo.equals(other.idEntTributo))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.gts.tributo.modelo.BcEntidadTributos[ idEntTributo=" + idEntTributo + " ]";
  }

}
