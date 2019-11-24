/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apirest.tributo.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Ricardo
 */
@Entity
@Table(name = "fr_usuario_acto")
public class FrUsuarioActo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_usuario_acto")
    private Integer idUsuarioActo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_crea")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCrea;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "estado")
    private String estado;
    
    @Column(name = "fecha_borrador")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaBorrador;
    
    @Column(name = "fecha_presentacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPresentacion;
    
    @Column(name = "fecha_liquidacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaLiquidacion;
    
    @Column(name = "fecha_pago")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPago;
    
    @Column(name = "fecha_anulado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAnulado;
    @Basic(optional = false)
    
    @NotNull
    @Column(name = "valor_apagar")
    private float valorApagar;
    
    @Size(max = 4)
    @Column(name = "tipo_periodo")
    private String tipoPeriodo;
    @Basic(optional = false)
    
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "numero_acto")
    private String numeroActo;
    @Basic(optional = false)
    
    @NotNull
    @Column(name = "valor_acto")
    private long valorActo;
    @Basic(optional = false)
    
    
    @Column(name = "fecha_inicio_acto")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicioActo;
    
    
    @Column(name = "pdf_ruta")
    private String pdfRuta;
    
    @Column(name = "datos_esquema")
    private String datosEsquema;
      
    @Column(name = "descripcion")
    private String descripcion;
    
    
    @JoinColumn(name = "fk_acto_entidad", referencedColumnName = "id_acto_entidad")
    @ManyToOne(optional = false)
    private BcActoEntidad fkActoEntidad;
    
    @JoinColumn(name = "fk_entidad_contratantes", referencedColumnName = "id_entidad_contratantes")
    @ManyToOne(optional = false)
    private BcEntidadContratantes fkEntidadContratantes;
    
    @JoinColumn(name = "fk_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private FrUsuario fkUsuario;

    public FrUsuarioActo() {
    }

    public FrUsuarioActo(Integer idUsuarioActo) {
        this.idUsuarioActo = idUsuarioActo;
    }

    public FrUsuarioActo(Integer idUsuarioActo, Date fechaCrea, String estado, float valorApagar, String numeroActo, long valorActo, Date fechaInicioActo) {
        this.idUsuarioActo = idUsuarioActo;
        this.fechaCrea = fechaCrea;
        this.estado = estado;
        this.valorApagar = valorApagar;
        this.numeroActo = numeroActo;
        this.valorActo = valorActo;
        this.fechaInicioActo = fechaInicioActo;
    }

    public Integer getIdUsuarioActo() {
        return idUsuarioActo;
    }

    public void setIdUsuarioActo(Integer idUsuarioActo) {
        this.idUsuarioActo = idUsuarioActo;
    }

    public Date getFechaCrea() {
        return fechaCrea;
    }

    public void setFechaCrea(Date fechaCrea) {
        this.fechaCrea = fechaCrea;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaBorrador() {
        return fechaBorrador;
    }

    public void setFechaBorrador(Date fechaBorrador) {
        this.fechaBorrador = fechaBorrador;
    }

    public Date getFechaPresentacion() {
        return fechaPresentacion;
    }

    public void setFechaPresentacion(Date fechaPresentacion) {
        this.fechaPresentacion = fechaPresentacion;
    }

    public Date getFechaLiquidacion() {
        return fechaLiquidacion;
    }

    public void setFechaLiquidacion(Date fechaLiquidacion) {
        this.fechaLiquidacion = fechaLiquidacion;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Date getFechaAnulado() {
        return fechaAnulado;
    }

    public void setFechaAnulado(Date fechaAnulado) {
        this.fechaAnulado = fechaAnulado;
    }

    public float getValorApagar() {
        return valorApagar;
    }

    public void setValorApagar(float valorApagar) {
        this.valorApagar = valorApagar;
    }

    public String getTipoPeriodo() {
        return tipoPeriodo;
    }

    public void setTipoPeriodo(String tipoPeriodo) {
        this.tipoPeriodo = tipoPeriodo;
    }

    public String getNumeroActo() {
        return numeroActo;
    }

    public void setNumeroActo(String numeroActo) {
        this.numeroActo = numeroActo;
    }

    public long getValorActo() {
        return valorActo;
    }

    public void setValorActo(long valorActo) {
        this.valorActo = valorActo;
    }

    public Date getFechaInicioActo() {
        return fechaInicioActo;
    }

    public void setFechaInicioActo(Date fechaInicioActo) {
        this.fechaInicioActo = fechaInicioActo;
    }

    public BcActoEntidad getFkActoEntidad() {
        return fkActoEntidad;
    }

    public void setFkActoEntidad(BcActoEntidad fkActoEntidad) {
        this.fkActoEntidad = fkActoEntidad;
    }

    public BcEntidadContratantes getFkEntidadContratantes() {
        return fkEntidadContratantes;
    }

    public void setFkEntidadContratantes(BcEntidadContratantes fkEntidadContratantes) {
        this.fkEntidadContratantes = fkEntidadContratantes;
    }

    public FrUsuario getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(FrUsuario fkUsuario) {
        this.fkUsuario = fkUsuario;
    }

    public String getPdfRuta() {
        return pdfRuta;
    }

    public void setPdfRuta(String pdfRuta) {
        this.pdfRuta = pdfRuta;
    }

    public String getDatosEsquema() {
        return datosEsquema;
    }

    public void setDatosEsquema(String datosEsquema) {
        this.datosEsquema = datosEsquema;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    
    
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuarioActo != null ? idUsuarioActo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof FrUsuarioActo)) {
            return false;
        }
        FrUsuarioActo other = (FrUsuarioActo) object;
        if ((this.idUsuarioActo == null && other.idUsuarioActo != null) || (this.idUsuarioActo != null && !this.idUsuarioActo.equals(other.idUsuarioActo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gts.tributo.modelo.FrUsuarioActo[ idUsuarioActo=" + idUsuarioActo + " ]";
    }
    
}
