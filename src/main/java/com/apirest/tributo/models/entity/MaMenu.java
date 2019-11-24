/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apirest.tributo.models.entity;

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "ma_menu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaMenu.findAll", query = "SELECT m FROM MaMenu m")
    , @NamedQuery(name = "MaMenu.findByIdMaMenu", query = "SELECT m FROM MaMenu m WHERE m.idMaMenu = :idMaMenu")
    , @NamedQuery(name = "MaMenu.findByUrlMenu", query = "SELECT m FROM MaMenu m WHERE m.urlMenu = :urlMenu")
    , @NamedQuery(name = "MaMenu.findByNombreMenu", query = "SELECT m FROM MaMenu m WHERE m.nombreMenu = :nombreMenu")
    , @NamedQuery(name = "MaMenu.findByOrden", query = "SELECT m FROM MaMenu m WHERE m.orden = :orden")
    , @NamedQuery(name = "MaMenu.findByIcono", query = "SELECT m FROM MaMenu m WHERE m.icono = :icono")})
public class MaMenu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_ma_menu")
    private Integer idMaMenu;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "url_menu")
    private String urlMenu;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre_menu")
    private String nombreMenu;
    @Basic(optional = false)
    @NotNull
    @Column(name = "orden")
    private short orden;
    @Size(max = 45)
    @Column(name = "icono")
    private String icono;
    
    @JoinColumn(name = "fk_wa_modulo", referencedColumnName = "id_ma_modulos")
    @ManyToOne(optional = false)
    private MaModulos fkWaModulo;
    
    @JsonIgnore
    @OneToMany( cascade = CascadeType.ALL, mappedBy = "fkMaMenu")
    private List<MaMenuPerfil> maMenuPerfilList;

    public MaMenu() {
    }

    public MaMenu(Integer idMaMenu) {
        this.idMaMenu = idMaMenu;
    }

    public MaMenu(Integer idMaMenu, String urlMenu, String nombreMenu, short orden) {
        this.idMaMenu = idMaMenu;
        this.urlMenu = urlMenu;
        this.nombreMenu = nombreMenu;
        this.orden = orden;
    }

    public Integer getIdMaMenu() {
        return idMaMenu;
    }

    public void setIdMaMenu(Integer idMaMenu) {
        this.idMaMenu = idMaMenu;
    }

    public String getUrlMenu() {
        return urlMenu;
    }

    public void setUrlMenu(String urlMenu) {
        this.urlMenu = urlMenu;
    }

    public String getNombreMenu() {
        return nombreMenu;
    }

    public void setNombreMenu(String nombreMenu) {
        this.nombreMenu = nombreMenu;
    }

    public short getOrden() {
        return orden;
    }

    public void setOrden(short orden) {
        this.orden = orden;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public MaModulos getFkWaModulo() {
        return fkWaModulo;
    }

    public void setFkWaModulo(MaModulos fkWaModulo) {
        this.fkWaModulo = fkWaModulo;
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
        hash += (idMaMenu != null ? idMaMenu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof MaMenu)) {
            return false;
        }
        MaMenu other = (MaMenu) object;
        if ((this.idMaMenu == null && other.idMaMenu != null) || (this.idMaMenu != null && !this.idMaMenu.equals(other.idMaMenu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gts.tributo.modelo.MaMenu[ idMaMenu=" + idMaMenu + " ]";
    }
    
}
