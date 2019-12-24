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
@Table(name = "ma_perfiles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaPerfiles.findAll", query = "SELECT m FROM MaPerfiles m")
    , @NamedQuery(name = "MaPerfiles.findByIdMaPerfiles", query = "SELECT m FROM MaPerfiles m WHERE m.idMaPerfiles = :idMaPerfiles")
    , @NamedQuery(name = "MaPerfiles.findByNombrePerfil", query = "SELECT m FROM MaPerfiles m WHERE m.nombrePerfil = :nombrePerfil")})
public class MaPerfiles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_ma_perfiles")
    private Integer idMaPerfiles;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre_perfil")
    private String nombrePerfil;
    
    @JsonIgnore
    @OneToMany( cascade = CascadeType.ALL, mappedBy = "fkMaPerfil")
    private List<MaUsuarios> maUsuariosList;
    
    @JsonIgnore
    @OneToMany( cascade = CascadeType.ALL, mappedBy = "fkMaPerfiles")
    private List<MaMenuPerfil> maMenuPerfilList;

    public MaPerfiles() {
    }

    public MaPerfiles(Integer idMaPerfiles) {
        this.idMaPerfiles = idMaPerfiles;
    }

    public MaPerfiles(Integer idMaPerfiles, String nombrePerfil) {
        this.idMaPerfiles = idMaPerfiles;
        this.nombrePerfil = nombrePerfil;
    }

    public Integer getIdMaPerfiles() {
        return idMaPerfiles;
    }

    public void setIdMaPerfiles(Integer idMaPerfiles) {
        this.idMaPerfiles = idMaPerfiles;
    }

    public String getNombrePerfil() {
        return nombrePerfil;
    }

    public void setNombrePerfil(String nombrePerfil) {
        this.nombrePerfil = nombrePerfil;
    }

    @XmlTransient
    public List<MaUsuarios> getMaUsuariosList() {
        return maUsuariosList;
    }

    public void setMaUsuariosList(List<MaUsuarios> maUsuariosList) {
        this.maUsuariosList = maUsuariosList;
    }

    @XmlTransient
    public List<MaMenuPerfil> getMaMenuPerfilList() {
        return maMenuPerfilList;
    }

    public void setMaMenuPerfilList(List<MaMenuPerfil> maMenuPerfilList) {
        this.maMenuPerfilList = maMenuPerfilList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMaPerfiles != null ? idMaPerfiles.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaPerfiles)) {
            return false;
        }
        MaPerfiles other = (MaPerfiles) object;
        if ((this.idMaPerfiles == null && other.idMaPerfiles != null) || (this.idMaPerfiles != null && !this.idMaPerfiles.equals(other.idMaPerfiles))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gts.tributo.modelo.MaPerfiles[ idMaPerfiles=" + idMaPerfiles + " ]";
    }
    
}
