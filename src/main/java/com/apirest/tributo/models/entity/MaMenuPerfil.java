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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ricardo
 */
@Entity
@Table(name = "ma_menu_perfil")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaMenuPerfil.findAll", query = "SELECT m FROM MaMenuPerfil m")
    , @NamedQuery(name = "MaMenuPerfil.findByIdMaMenuPerfil", query = "SELECT m FROM MaMenuPerfil m WHERE m.idMaMenuPerfil = :idMaMenuPerfil")})
public class MaMenuPerfil implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_ma_menu_perfil")
    private Integer idMaMenuPerfil;
    
    @JoinColumn(name = "fk_ma_perfiles", referencedColumnName = "id_ma_perfiles")
    @ManyToOne(optional = false)
    private MaPerfiles fkMaPerfiles;
    
    @JoinColumn(name = "fk_ma_menu", referencedColumnName = "id_ma_menu")
    @ManyToOne(optional = false)
    private MaMenu fkMaMenu;

    public MaMenuPerfil() {
    }

    public MaMenuPerfil(Integer idMaMenuPerfil) {
        this.idMaMenuPerfil = idMaMenuPerfil;
    }

    public Integer getIdMaMenuPerfil() {
        return idMaMenuPerfil;
    }

    public void setIdMaMenuPerfil(Integer idMaMenuPerfil) {
        this.idMaMenuPerfil = idMaMenuPerfil;
    }

    public MaPerfiles getFkMaPerfiles() {
        return fkMaPerfiles;
    }

    public void setFkMaPerfiles(MaPerfiles fkMaPerfiles) {
        this.fkMaPerfiles = fkMaPerfiles;
    }

    public MaMenu getFkMaMenu() {
        return fkMaMenu;
    }

    public void setFkMaMenu(MaMenu fkMaMenu) {
        this.fkMaMenu = fkMaMenu;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMaMenuPerfil != null ? idMaMenuPerfil.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof MaMenuPerfil)) {
            return false;
        }
        MaMenuPerfil other = (MaMenuPerfil) object;
        if ((this.idMaMenuPerfil == null && other.idMaMenuPerfil != null) || (this.idMaMenuPerfil != null && !this.idMaMenuPerfil.equals(other.idMaMenuPerfil))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gts.tributo.modelo.MaMenuPerfil[ idMaMenuPerfil=" + idMaMenuPerfil + " ]";
    }
    
}
