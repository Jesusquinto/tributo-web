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
@Table(name = "ma_modulos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaModulos.findAll", query = "SELECT m FROM MaModulos m")
    , @NamedQuery(name = "MaModulos.findByIdMaModulos", query = "SELECT m FROM MaModulos m WHERE m.idMaModulos = :idMaModulos")
    , @NamedQuery(name = "MaModulos.findByNombreModulo", query = "SELECT m FROM MaModulos m WHERE m.nombreModulo = :nombreModulo")
    , @NamedQuery(name = "MaModulos.findByIcono", query = "SELECT m FROM MaModulos m WHERE m.icono = :icono")})
public class MaModulos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_ma_modulos")
    private Integer idMaModulos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre_modulo")
    private String nombreModulo;
    @Size(max = 45)
    @Column(name = "icono")
    private String icono;
    
    @JsonIgnore
    @OneToMany( cascade = CascadeType.ALL, mappedBy = "fkWaModulo")
    private List<MaMenu> maMenuList;

    public MaModulos() {
    }

    public MaModulos(Integer idMaModulos) {
        this.idMaModulos = idMaModulos;
    }

    public MaModulos(Integer idMaModulos, String nombreModulo) {
        this.idMaModulos = idMaModulos;
        this.nombreModulo = nombreModulo;
    }

    public Integer getIdMaModulos() {
        return idMaModulos;
    }

    public void setIdMaModulos(Integer idMaModulos) {
        this.idMaModulos = idMaModulos;
    }

    public String getNombreModulo() {
        return nombreModulo;
    }

    public void setNombreModulo(String nombreModulo) {
        this.nombreModulo = nombreModulo;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    @XmlTransient
    public List<MaMenu> getMaMenuList() {
        return maMenuList;
    }

    public void setMaMenuList(List<MaMenu> maMenuList) {
        this.maMenuList = maMenuList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMaModulos != null ? idMaModulos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaModulos)) {
            return false;
        }
        MaModulos other = (MaModulos) object;
        if ((this.idMaModulos == null && other.idMaModulos != null) || (this.idMaModulos != null && !this.idMaModulos.equals(other.idMaModulos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gts.tributo.modelo.MaModulos[ idMaModulos=" + idMaModulos + " ]";
    }
    
}
