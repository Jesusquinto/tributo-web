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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "bc_acto_entidad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BcActoEntidad.findAll", query = "SELECT b FROM BcActoEntidad b")
    , @NamedQuery(name = "BcActoEntidad.findByIdActoEntidad", query = "SELECT b FROM BcActoEntidad b WHERE b.idActoEntidad = :idActoEntidad")
    , @NamedQuery(name = "BcActoEntidad.findByCodigo", query = "SELECT b FROM BcActoEntidad b WHERE b.codigo = :codigo")
    , @NamedQuery(name = "BcActoEntidad.findByIdEntidad", query = "SELECT b FROM BcActoEntidad b WHERE b.fkBcEntidad.idEntidad = :idEntidad")        
    , @NamedQuery(name = "BcActoEntidad.findByTipoperiodo", query = "SELECT b FROM BcActoEntidad b WHERE b.tipoperiodo = :tipoperiodo")})
public class BcActoEntidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_acto_entidad")
    private Integer idActoEntidad;
    @Size(max = 4)
    @Column(name = "codigo")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    
    @Size(min = 1, max = 2000)
    @Column(name = "esquema")
    private String esquema;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipoperiodo")
    private int tipoperiodo;
    
    @JoinColumn(name = "fk_bc_entidad", referencedColumnName = "id_entidad")
    @ManyToOne(optional = false)
    private BcEntidad fkBcEntidad;
    
    @JoinColumn(name = "fk_bc_acto", referencedColumnName = "id_acto")
    @ManyToOne(optional = false)
    private BcActo fkBcActo;
    
    @JsonIgnore
    @OneToMany( cascade = CascadeType.ALL, mappedBy = "fkActoEntidad")
    private List<FrUsuarioActo> frUsuarioActoList;

    public BcActoEntidad() {
    }

    public BcActoEntidad(Integer idActoEntidad) {
        this.idActoEntidad = idActoEntidad;
    }

    public BcActoEntidad(Integer idActoEntidad, String esquema, int tipoperiodo) {
        this.idActoEntidad = idActoEntidad;
        this.esquema = esquema;
        this.tipoperiodo = tipoperiodo;
    }

    public Integer getIdActoEntidad() {
        return idActoEntidad;
    }

    public void setIdActoEntidad(Integer idActoEntidad) {
        this.idActoEntidad = idActoEntidad;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEsquema() {
        return esquema;
    }

    public void setEsquema(String esquema) {
        this.esquema = esquema;
    }

    public int getTipoperiodo() {
        return tipoperiodo;
    }

    public void setTipoperiodo(int tipoperiodo) {
        this.tipoperiodo = tipoperiodo;
    }

    public BcEntidad getFkBcEntidad() {
        return fkBcEntidad;
    }

    public void setFkBcEntidad(BcEntidad fkBcEntidad) {
        this.fkBcEntidad = fkBcEntidad;
    }

    public BcActo getFkBcActo() {
        return fkBcActo;
    }

    public void setFkBcActo(BcActo fkBcActo) {
        this.fkBcActo = fkBcActo;
    }

    @XmlTransient
    public List<FrUsuarioActo> getFrUsuarioActoList() {
        return frUsuarioActoList;
    }

    public void setFrUsuarioActoList(List<FrUsuarioActo> frUsuarioActoList) {
        this.frUsuarioActoList = frUsuarioActoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idActoEntidad != null ? idActoEntidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BcActoEntidad)) {
            return false;
        }
        BcActoEntidad other = (BcActoEntidad) object;
        if ((this.idActoEntidad == null && other.idActoEntidad != null) || (this.idActoEntidad != null && !this.idActoEntidad.equals(other.idActoEntidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gts.tributo.modelo.BcActoEntidad[ idActoEntidad=" + idActoEntidad + " ]";
    }
    
}
