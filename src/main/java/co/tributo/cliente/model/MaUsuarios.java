/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.tributo.cliente.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "ma_usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaUsuarios.findAll", query = "SELECT m FROM MaUsuarios m")
    , @NamedQuery(name = "MaUsuarios.findByIdMaUsuarios", query = "SELECT m FROM MaUsuarios m WHERE m.idMaUsuarios = :idMaUsuarios")
    , @NamedQuery(name = "MaUsuarios.findByNombre", query = "SELECT m FROM MaUsuarios m WHERE m.nombre = :nombre")
    , @NamedQuery(name = "MaUsuarios.findByDocumento", query = "SELECT m FROM MaUsuarios m WHERE m.documento = :documento")
    , @NamedQuery(name = "MaUsuarios.findByNombreLargo", query = "SELECT m FROM MaUsuarios m WHERE m.nombreLargo = :nombreLargo")
    , @NamedQuery(name = "MaUsuarios.findByPassword", query = "SELECT m FROM MaUsuarios m WHERE m.password = :password")
    , @NamedQuery(name = "MaUsuarios.findByToken", query = "SELECT m FROM MaUsuarios m WHERE m.token = :token")})
public class MaUsuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_ma_usuarios")
    private Integer idMaUsuarios;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "documento")
    private String documento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre_largo")
    private String nombreLargo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "token")
    private String token;
    
    
    @JoinColumn(name = "fk_ma_entidad", referencedColumnName = "id_entidad")
    @ManyToOne(optional = false)
    private BcEntidad fkMaEntidad;
    
    
    @JoinColumn(name = "fk_ma_perfil", referencedColumnName = "id_ma_perfiles")
    @ManyToOne(optional = false)
    private MaPerfiles fkMaPerfil;

    public MaUsuarios() {
    }

    public MaUsuarios(Integer idMaUsuarios) {
        this.idMaUsuarios = idMaUsuarios;
    }

    public MaUsuarios(Integer idMaUsuarios, String nombre, String documento, String nombreLargo, String password, String token) {
        this.idMaUsuarios = idMaUsuarios;
        this.nombre = nombre;
        this.documento = documento;
        this.nombreLargo = nombreLargo;
        this.password = password;
        this.token = token;
    }

    public Integer getIdMaUsuarios() {
        return idMaUsuarios;
    }

    public void setIdMaUsuarios(Integer idMaUsuarios) {
        this.idMaUsuarios = idMaUsuarios;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombreLargo() {
        return nombreLargo;
    }

    public void setNombreLargo(String nombreLargo) {
        this.nombreLargo = nombreLargo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public BcEntidad getFkMaEntidad() {
        return fkMaEntidad;
    }

    public void setFkMaEntidad(BcEntidad fkMaEntidad) {
        this.fkMaEntidad = fkMaEntidad;
    }

    public MaPerfiles getFkMaPerfil() {
        return fkMaPerfil;
    }

    public void setFkMaPerfil(MaPerfiles fkMaPerfil) {
        this.fkMaPerfil = fkMaPerfil;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMaUsuarios != null ? idMaUsuarios.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaUsuarios)) {
            return false;
        }
        MaUsuarios other = (MaUsuarios) object;
        if ((this.idMaUsuarios == null && other.idMaUsuarios != null) || (this.idMaUsuarios != null && !this.idMaUsuarios.equals(other.idMaUsuarios))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gts.tributo.modelo.MaUsuarios[ idMaUsuarios=" + idMaUsuarios + " ]";
    }
    
}
