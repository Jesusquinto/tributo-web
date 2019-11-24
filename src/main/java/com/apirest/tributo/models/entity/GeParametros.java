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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ricardo
 */
@Entity
@Table(name = "ge_parametros")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GeParametros.findAll", query = "SELECT g FROM GeParametros g")
    , @NamedQuery(name = "GeParametros.findByIdParametros", query = "SELECT g FROM GeParametros g WHERE g.idParametros = :idParametros")
    , @NamedQuery(name = "GeParametros.findByValor", query = "SELECT g FROM GeParametros g WHERE g.valor = :valor")
    , @NamedQuery(name = "GeParametros.findByDescripcion", query = "SELECT g FROM GeParametros g WHERE g.descripcion = :descripcion")
    , @NamedQuery(name = "GeParametros.findByColor", query = "SELECT g FROM GeParametros g WHERE g.color = :color")
    , @NamedQuery(name = "GeParametros.findByOrden", query = "SELECT g FROM GeParametros g WHERE g.orden = :orden")
    , @NamedQuery(name = "GeParametros.findByGrupo", query = "SELECT g FROM GeParametros g WHERE g.grupo = :grupo")
    , @NamedQuery(name = "GeParametros.findBySubgrupo", query = "SELECT g FROM GeParametros g WHERE g.subgrupo = :subgrupo")
    , @NamedQuery(name = "GeParametros.findByEstado", query = "SELECT g FROM GeParametros g WHERE g.estado = :estado")
    , @NamedQuery(name = "GeParametros.findByDetalle", query = "SELECT g FROM GeParametros g WHERE g.detalle = :detalle")})
public class GeParametros implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_parametros")
    private Integer idParametros;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "valor")
    private String valor;
    @Size(max = 255)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 50)
    @Column(name = "color")
    private String color;
    @Column(name = "orden")
    private Integer orden;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "grupo")
    private String grupo;
    @Size(max = 255)
    @Column(name = "subgrupo")
    private String subgrupo;
    @Size(max = 1)
    @Column(name = "estado")
    private String estado;
    @Size(max = 255)
    @Column(name = "detalle")
    private String detalle;

    public GeParametros() {
    }

    public GeParametros(Integer idParametros) {
        this.idParametros = idParametros;
    }

    public GeParametros(Integer idParametros, String valor, String grupo) {
        this.idParametros = idParametros;
        this.valor = valor;
        this.grupo = grupo;
    }

    public Integer getIdParametros() {
        return idParametros;
    }

    public void setIdParametros(Integer idParametros) {
        this.idParametros = idParametros;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getSubgrupo() {
        return subgrupo;
    }

    public void setSubgrupo(String subgrupo) {
        this.subgrupo = subgrupo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idParametros != null ? idParametros.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof GeParametros)) {
            return false;
        }
        GeParametros other = (GeParametros) object;
        if ((this.idParametros == null && other.idParametros != null) || (this.idParametros != null && !this.idParametros.equals(other.idParametros))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gts.tributo.modelo.GeParametros[ idParametros=" + idParametros + " ]";
    }
    
}
