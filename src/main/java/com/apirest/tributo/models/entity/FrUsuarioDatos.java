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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ricardo
 */
@Entity
@Table(name = "fr_usuario_datos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FrUsuarioDatos.findAll", query = "SELECT f FROM FrUsuarioDatos f")
    , @NamedQuery(name = "FrUsuarioDatos.findByIdUsuarioDatos", query = "SELECT f FROM FrUsuarioDatos f WHERE f.idUsuarioDatos = :idUsuarioDatos")
    , @NamedQuery(name = "FrUsuarioDatos.findByTipoActividad", query = "SELECT f FROM FrUsuarioDatos f WHERE f.tipoActividad = :tipoActividad")
    , @NamedQuery(name = "FrUsuarioDatos.findByCodigoCiu", query = "SELECT f FROM FrUsuarioDatos f WHERE f.codigoCiu = :codigoCiu")
    , @NamedQuery(name = "FrUsuarioDatos.findByNombreEstablecimiento", query = "SELECT f FROM FrUsuarioDatos f WHERE f.nombreEstablecimiento = :nombreEstablecimiento")
    , @NamedQuery(name = "FrUsuarioDatos.findByNombreRepresentante", query = "SELECT f FROM FrUsuarioDatos f WHERE f.nombreRepresentante = :nombreRepresentante")
    , @NamedQuery(name = "FrUsuarioDatos.findByCedulaRepresentante", query = "SELECT f FROM FrUsuarioDatos f WHERE f.cedulaRepresentante = :cedulaRepresentante")
    , @NamedQuery(name = "FrUsuarioDatos.findByCorreoRepresentante", query = "SELECT f FROM FrUsuarioDatos f WHERE f.correoRepresentante = :correoRepresentante")
    , @NamedQuery(name = "FrUsuarioDatos.findByNombreContador", query = "SELECT f FROM FrUsuarioDatos f WHERE f.nombreContador = :nombreContador")
    , @NamedQuery(name = "FrUsuarioDatos.findByCedulaContador", query = "SELECT f FROM FrUsuarioDatos f WHERE f.cedulaContador = :cedulaContador")
    , @NamedQuery(name = "FrUsuarioDatos.findByCorreoContador", query = "SELECT f FROM FrUsuarioDatos f WHERE f.correoContador = :correoContador")
    , @NamedQuery(name = "FrUsuarioDatos.findByFkUsuario", query = "SELECT f FROM FrUsuarioDatos f WHERE f.fkUsuario = :fkUsuario")})
public class FrUsuarioDatos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_usuario_datos")
    private Integer idUsuarioDatos;
    @Size(max = 255)
    @Column(name = "tipo_actividad")
    private String tipoActividad;
    @Size(max = 10)
    @Column(name = "codigo_ciu")
    private String codigoCiu;
    @Size(max = 255)
    @Column(name = "nombre_establecimiento")
    private String nombreEstablecimiento;
    @Lob
    @Size(max = 65535)
    @Column(name = "personas_autorizadas")
    private String personasAutorizadas;
    @Lob
    @Size(max = 65535)
    @Column(name = "tipoo_bligaciones")
    private String tipooBligaciones;
    @Size(max = 255)
    @Column(name = "nombre_representante")
    private String nombreRepresentante;
    @Size(max = 255)
    @Column(name = "cedula_representante")
    private String cedulaRepresentante;
    @Size(max = 255)
    @Column(name = "correo_representante")
    private String correoRepresentante;
    @Size(max = 255)
    @Column(name = "nombre_contador")
    private String nombreContador;
    @Size(max = 255)
    @Column(name = "cedula_contador")
    private String cedulaContador;
    @Size(max = 255)
    @Column(name = "correo_contador")
    private String correoContador;
    @Lob
    @Size(max = 65535)
    @Column(name = "experiencia_sector")
    private String experienciaSector;
    @Column(name = "fk_usuario")
    private Integer fkUsuario;

    public FrUsuarioDatos() {
    }

    public FrUsuarioDatos(Integer idUsuarioDatos) {
        this.idUsuarioDatos = idUsuarioDatos;
    }

    public Integer getIdUsuarioDatos() {
        return idUsuarioDatos;
    }

    public void setIdUsuarioDatos(Integer idUsuarioDatos) {
        this.idUsuarioDatos = idUsuarioDatos;
    }

    public String getTipoActividad() {
        return tipoActividad;
    }

    public void setTipoActividad(String tipoActividad) {
        this.tipoActividad = tipoActividad;
    }

    public String getCodigoCiu() {
        return codigoCiu;
    }

    public void setCodigoCiu(String codigoCiu) {
        this.codigoCiu = codigoCiu;
    }

    public String getNombreEstablecimiento() {
        return nombreEstablecimiento;
    }

    public void setNombreEstablecimiento(String nombreEstablecimiento) {
        this.nombreEstablecimiento = nombreEstablecimiento;
    }

    public String getPersonasAutorizadas() {
        return personasAutorizadas;
    }

    public void setPersonasAutorizadas(String personasAutorizadas) {
        this.personasAutorizadas = personasAutorizadas;
    }

    public String getTipooBligaciones() {
        return tipooBligaciones;
    }

    public void setTipooBligaciones(String tipooBligaciones) {
        this.tipooBligaciones = tipooBligaciones;
    }

    public String getNombreRepresentante() {
        return nombreRepresentante;
    }

    public void setNombreRepresentante(String nombreRepresentante) {
        this.nombreRepresentante = nombreRepresentante;
    }

    public String getCedulaRepresentante() {
        return cedulaRepresentante;
    }

    public void setCedulaRepresentante(String cedulaRepresentante) {
        this.cedulaRepresentante = cedulaRepresentante;
    }

    public String getCorreoRepresentante() {
        return correoRepresentante;
    }

    public void setCorreoRepresentante(String correoRepresentante) {
        this.correoRepresentante = correoRepresentante;
    }

    public String getNombreContador() {
        return nombreContador;
    }

    public void setNombreContador(String nombreContador) {
        this.nombreContador = nombreContador;
    }

    public String getCedulaContador() {
        return cedulaContador;
    }

    public void setCedulaContador(String cedulaContador) {
        this.cedulaContador = cedulaContador;
    }

    public String getCorreoContador() {
        return correoContador;
    }

    public void setCorreoContador(String correoContador) {
        this.correoContador = correoContador;
    }

    public String getExperienciaSector() {
        return experienciaSector;
    }

    public void setExperienciaSector(String experienciaSector) {
        this.experienciaSector = experienciaSector;
    }

    public Integer getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(Integer fkUsuario) {
        this.fkUsuario = fkUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuarioDatos != null ? idUsuarioDatos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof FrUsuarioDatos)) {
            return false;
        }
        FrUsuarioDatos other = (FrUsuarioDatos) object;
        if ((this.idUsuarioDatos == null && other.idUsuarioDatos != null) || (this.idUsuarioDatos != null && !this.idUsuarioDatos.equals(other.idUsuarioDatos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gts.tributo.modelo.FrUsuarioDatos[ idUsuarioDatos=" + idUsuarioDatos + " ]";
    }
    
}
