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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ricardo
 */
@Entity
@Table(name = "bc_entidad_contratantes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BcEntidadContratantes.findAll", query = "SELECT b FROM BcEntidadContratantes b")
    , @NamedQuery(name = "BcEntidadContratantes.findByIdEntidadContratantes", query = "SELECT b FROM BcEntidadContratantes b WHERE b.idEntidadContratantes = :idEntidadContratantes")})
public class BcEntidadContratantes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_entidad_contratantes")
    private Integer idEntidadContratantes;
    
    @JsonIgnore
    @OneToMany( cascade = CascadeType.ALL, mappedBy = "fkEntidadContratantes")
    private List<FrUsuarioActo> frUsuarioActoList;
    
    @JoinColumn(name = "fk_contratante", referencedColumnName = "id_contratantes")
    @ManyToOne(optional = false)
    private BcContratantes fkContratante;
    
    @JoinColumn(name = "fk_entidad", referencedColumnName = "id_entidad")
    @ManyToOne(optional = false)
    private BcEntidad fkEntidad;

    public BcEntidadContratantes() {
    }

    public BcEntidadContratantes(Integer idEntidadContratantes) {
        this.idEntidadContratantes = idEntidadContratantes;
    }

    public Integer getIdEntidadContratantes() {
        return idEntidadContratantes;
    }

    public void setIdEntidadContratantes(Integer idEntidadContratantes) {
        this.idEntidadContratantes = idEntidadContratantes;
    }

    @XmlTransient
    public List<FrUsuarioActo> getFrUsuarioActoList() {
        return frUsuarioActoList;
    }

    public void setFrUsuarioActoList(List<FrUsuarioActo> frUsuarioActoList) {
        this.frUsuarioActoList = frUsuarioActoList;
    }

    public BcContratantes getFkContratante() {
        return fkContratante;
    }

    public void setFkContratante(BcContratantes fkContratante) {
        this.fkContratante = fkContratante;
    }

    public BcEntidad getFkEntidad() {
        return fkEntidad;
    }

    public void setFkEntidad(BcEntidad fkEntidad) {
        this.fkEntidad = fkEntidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEntidadContratantes != null ? idEntidadContratantes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BcEntidadContratantes)) {
            return false;
        }
        BcEntidadContratantes other = (BcEntidadContratantes) object;
        if ((this.idEntidadContratantes == null && other.idEntidadContratantes != null) || (this.idEntidadContratantes != null && !this.idEntidadContratantes.equals(other.idEntidadContratantes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gts.tributo.modelo.BcEntidadContratantes[ idEntidadContratantes=" + idEntidadContratantes + " ]";
    }
    
}
