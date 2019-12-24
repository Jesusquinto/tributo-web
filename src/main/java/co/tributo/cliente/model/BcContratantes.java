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
@Table(name = "bc_contratantes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BcContratantes.findAll", query = "SELECT b FROM BcContratantes b")
    , @NamedQuery(name = "BcContratantes.findByIdContratantes", query = "SELECT b FROM BcContratantes b WHERE b.idContratantes = :idContratantes")
    , @NamedQuery(name = "BcContratantes.findByNitContratante", query = "SELECT b FROM BcContratantes b WHERE b.nitContratante = :nitContratante")
    , @NamedQuery(name = "BcContratantes.findByNombreContratante", query = "SELECT b FROM BcContratantes b WHERE b.nombreContratante = :nombreContratante")
    , @NamedQuery(name = "BcContratantes.findByTipoContratante", query = "SELECT b FROM BcContratantes b WHERE b.tipoContratante = :tipoContratante")})
public class BcContratantes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
   
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Basic(optional = false)
    @Column(name = "id_contratantes")
    private Integer idContratantes;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nit_contratante")
    private String nitContratante;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre_contratante")
    private String nombreContratante;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_contratante")
    private String tipoContratante;
    
    @JsonIgnore
    @OneToMany( cascade = CascadeType.ALL, mappedBy = "fkContratante")
    private List<BcEntidadContratantes> bcEntidadContratantesList;

    public BcContratantes() {
    }

    public BcContratantes(Integer idContratantes) {
        this.idContratantes = idContratantes;
    }

    public BcContratantes(Integer idContratantes, String nitContratante, String nombreContratante, String tipoContratante) {
        this.idContratantes = idContratantes;
        this.nitContratante = nitContratante;
        this.nombreContratante = nombreContratante;
        this.tipoContratante = tipoContratante;
    }

    public Integer getIdContratantes() {
        return idContratantes;
    }

    public void setIdContratantes(Integer idContratantes) {
        this.idContratantes = idContratantes;
    }

    public String getNitContratante() {
        return nitContratante;
    }

    public void setNitContratante(String nitContratante) {
        this.nitContratante = nitContratante;
    }

    public String getNombreContratante() {
        return nombreContratante;
    }

    public void setNombreContratante(String nombreContratante) {
        this.nombreContratante = nombreContratante;
    }

    public String getTipoContratante() {
        return tipoContratante;
    }

    public void setTipoContratante(String tipoContratante) {
        this.tipoContratante = tipoContratante;
    }

    @XmlTransient
    public List<BcEntidadContratantes> getBcEntidadContratantesList() {
        return bcEntidadContratantesList;
    }

    public void setBcEntidadContratantesList(List<BcEntidadContratantes> bcEntidadContratantesList) {
        this.bcEntidadContratantesList = bcEntidadContratantesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idContratantes != null ? idContratantes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BcContratantes)) {
            return false;
        }
        BcContratantes other = (BcContratantes) object;
        if ((this.idContratantes == null && other.idContratantes != null) || (this.idContratantes != null && !this.idContratantes.equals(other.idContratantes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gts.tributo.modelo.BcContratantes[ idContratantes=" + idContratantes + " ]";
    }
    
}
