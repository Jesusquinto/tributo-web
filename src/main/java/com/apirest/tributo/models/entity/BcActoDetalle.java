/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apirest.tributo.models.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "bc_acto_detalle")
public class BcActoDetalle implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id_tipo_acto_detalle")
  private Integer idTipoActoDetalle;

  @JoinColumn(name = "fk_entidad_tributo", referencedColumnName = "id_ent_tributo")
  @ManyToOne(optional = false)
  private BcEntidadTributos fkEntidadTributo;

  @JoinColumn(name = "fk_tipo_acto", referencedColumnName = "id_acto")
  @ManyToOne(optional = false)
  private BcActo fkTipoActo;

  public BcActoDetalle() {
  }

  public BcActoDetalle(Integer idTipoActoDetalle) {
    this.idTipoActoDetalle = idTipoActoDetalle;
  }

  public Integer getIdTipoActoDetalle() {
    return idTipoActoDetalle;
  }

  public void setIdTipoActoDetalle(Integer idTipoActoDetalle) {
    this.idTipoActoDetalle = idTipoActoDetalle;
  }

  public BcEntidadTributos getFkEntidadTributo() {
    return fkEntidadTributo;
  }

  public void setFkEntidadTributo(BcEntidadTributos fkEntidadTributo) {
    this.fkEntidadTributo = fkEntidadTributo;
  }

  public BcActo getFkTipoActo() {
    return fkTipoActo;
  }

  public void setFkTipoActo(BcActo fkTipoActo) {
    this.fkTipoActo = fkTipoActo;
  }

}
