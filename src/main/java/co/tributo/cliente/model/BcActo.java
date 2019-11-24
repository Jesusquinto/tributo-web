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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
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
@Table(name = "bc_acto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BcActo.findAll", query = "SELECT b FROM BcActo b")
    , @NamedQuery(name = "BcActo.findByIdActo", query = "SELECT b FROM BcActo b WHERE b.idActo = :idActo")
    , @NamedQuery(name = "BcActo.findByNombreActo", query = "SELECT b FROM BcActo b WHERE b.nombreActo = :nombreActo")
    , @NamedQuery(name = "BcActo.findByDescripcion", query = "SELECT b FROM BcActo b WHERE b.descripcion = :descripcion")
    , @NamedQuery(name = "BcActo.findByEstado", query = "SELECT b FROM BcActo b WHERE b.estado = :estado")})
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

    @XmlTransient
    public List<BcActoEntidad> getBcActoEntidadList() {
        return bcActoEntidadList;
    }

    public void setBcActoEntidadList(List<BcActoEntidad> bcActoEntidadList) {
        this.bcActoEntidadList = bcActoEntidadList;
    }

    @XmlTransient
    public List<BcActoDetalle> getBcActoDetalleList() {
        return bcActoDetalleList;
    }

    public void setBcActoDetalleList(List<BcActoDetalle> bcActoDetalleList) {
        this.bcActoDetalleList = bcActoDetalleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idActo != null ? idActo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BcActo)) {
            return false;
        }
        BcActo other = (BcActo) object;
        if ((this.idActo == null && other.idActo != null) || (this.idActo != null && !this.idActo.equals(other.idActo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gts.tributo.modelo.BcActo[ idActo=" + idActo + " ]";
    }
    
}
