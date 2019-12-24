/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.tributo.cliente.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ricardo
 */
@Entity
@Table(name = "bc_tributos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BcTributos.findAll", query = "SELECT b FROM BcTributos b")
    , @NamedQuery(name = "BcTributos.findByIdTributo", query = "SELECT b FROM BcTributos b WHERE b.idTributo = :idTributo")
    , @NamedQuery(name = "BcTributos.findByNombre", query = "SELECT b FROM BcTributos b WHERE b.nombre = :nombre")
    , @NamedQuery(name = "BcTributos.findByEstado", query = "SELECT b FROM BcTributos b WHERE b.estado = :estado")})
public class BcTributos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tributo")
    private Integer idTributo;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre")
    private String nombre;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "estado")
    private String estado;

    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "tipo")
    private String tipo;
    
    @JsonIgnore
    @OneToMany( cascade = CascadeType.ALL, mappedBy = "idtributo")
    private List<BcEntidadTributos> bcEntidadTributosList;

    public BcTributos() {
    }

    public BcTributos(Integer idTributo) {
        this.idTributo = idTributo;
    }

    public BcTributos(Integer idTributo, String nombre, String estado) {
        this.idTributo = idTributo;
        this.nombre = nombre;
        this.estado = estado;
    }

    public Integer getIdTributo() {
        return idTributo;
    }

    public void setIdTributo(Integer idTributo) {
        this.idTributo = idTributo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @XmlTransient
    public List<BcEntidadTributos> getBcEntidadTributosList() {
        return bcEntidadTributosList;
    }

    public void setBcEntidadTributosList(List<BcEntidadTributos> bcEntidadTributosList) {
        this.bcEntidadTributosList = bcEntidadTributosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTributo != null ? idTributo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BcTributos)) {
            return false;
        }
        BcTributos other = (BcTributos) object;
        if ((this.idTributo == null && other.idTributo != null) || (this.idTributo != null && !this.idTributo.equals(other.idTributo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gts.tributo.modelo.BcTributos[ idTributo=" + idTributo + " ]";
    }
    
}
