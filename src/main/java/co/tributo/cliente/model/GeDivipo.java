/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.tributo.cliente.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author IDitroyer
 */
@Entity
@Table(name = "ge_divipo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GeDivipo.findAll", query = "SELECT g FROM GeDivipo g"),
    @NamedQuery(name = "GeDivipo.findByIdGeDivipo", query = "SELECT g FROM GeDivipo g WHERE g.idGeDivipo = :idGeDivipo"),
    @NamedQuery(name = "GeDivipo.findByCodigoDepartemento", query = "SELECT g FROM GeDivipo g WHERE g.codigoDepartemento = :codigoDepartemento"),
    @NamedQuery(name = "GeDivipo.findByCodigoMunicipio", query = "SELECT g FROM GeDivipo g WHERE g.codigoMunicipio = :codigoMunicipio"),
    @NamedQuery(name = "GeDivipo.findByNombreDepartamentp", query = "SELECT g FROM GeDivipo g WHERE g.nombreDepartamentp = :nombreDepartamentp"),
    @NamedQuery(name = "GeDivipo.findByNombreMunicipio", query = "SELECT g FROM GeDivipo g WHERE g.nombreMunicipio = :nombreMunicipio"),
    @NamedQuery(name = "GeDivipo.findByPoblacion", query = "SELECT g FROM GeDivipo g WHERE g.poblacion = :poblacion")})
public class GeDivipo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_ge_divipo")
    private Integer idGeDivipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "codigo_departemento")
    private String codigoDepartemento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "codigo_municipio")
    private String codigoMunicipio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre_departamentp")
    private String nombreDepartamentp;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre_municipio")
    private String nombreMunicipio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "poblacion")
    private String poblacion;
    
    @JsonIgnore
    @OneToMany(mappedBy = "fkGedivipo")
    private List<BcEntidad> bcEntidadList;
    
    @JsonIgnore
    @OneToMany(mappedBy = "fkDivipo")
    private List<FrUsuario> frUsuarioList;

    public GeDivipo() {
    }

    public GeDivipo(Integer idGeDivipo) {
        this.idGeDivipo = idGeDivipo;
    }

    public GeDivipo(Integer idGeDivipo, String codigoDepartemento, String codigoMunicipio, String nombreDepartamentp, String nombreMunicipio, String poblacion) {
        this.idGeDivipo = idGeDivipo;
        this.codigoDepartemento = codigoDepartemento;
        this.codigoMunicipio = codigoMunicipio;
        this.nombreDepartamentp = nombreDepartamentp;
        this.nombreMunicipio = nombreMunicipio;
        this.poblacion = poblacion;
    }

    public Integer getIdGeDivipo() {
        return idGeDivipo;
    }

    public void setIdGeDivipo(Integer idGeDivipo) {
        this.idGeDivipo = idGeDivipo;
    }

    public String getCodigoDepartemento() {
        return codigoDepartemento;
    }

    public void setCodigoDepartemento(String codigoDepartemento) {
        this.codigoDepartemento = codigoDepartemento;
    }

    public String getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(String codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    public String getNombreDepartamentp() {
        return nombreDepartamentp;
    }

    public void setNombreDepartamentp(String nombreDepartamentp) {
        this.nombreDepartamentp = nombreDepartamentp;
    }

    public String getNombreMunicipio() {
        return nombreMunicipio;
    }

    public void setNombreMunicipio(String nombreMunicipio) {
        this.nombreMunicipio = nombreMunicipio;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    @XmlTransient
    public List<BcEntidad> getBcEntidadList() {
        return bcEntidadList;
    }

    public void setBcEntidadList(List<BcEntidad> bcEntidadList) {
        this.bcEntidadList = bcEntidadList;
    }

    @XmlTransient
    public List<FrUsuario> getFrUsuarioList() {
        return frUsuarioList;
    }

    public void setFrUsuarioList(List<FrUsuario> frUsuarioList) {
        this.frUsuarioList = frUsuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGeDivipo != null ? idGeDivipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GeDivipo)) {
            return false;
        }
        GeDivipo other = (GeDivipo) object;
        if ((this.idGeDivipo == null && other.idGeDivipo != null) || (this.idGeDivipo != null && !this.idGeDivipo.equals(other.idGeDivipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.springsocial.model.GeDivipo[ idGeDivipo=" + idGeDivipo + " ]";
    }
    
}
