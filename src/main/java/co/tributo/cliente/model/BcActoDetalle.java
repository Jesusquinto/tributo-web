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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ricardo
 */
@Entity
@Table(name = "bc_acto_detalle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BcActoDetalle.findAll", query = "SELECT b FROM BcActoDetalle b")
    ,@NamedQuery(name = "BcActoDetalle.findByIdActo", query = "SELECT b FROM BcActoDetalle b where b.fkEntidadTributo.identidad.idEntidad = :identidad and b.fkTipoActo.idActo = :idacto")    
    , @NamedQuery(name = "BcActoDetalle.findByIdTipoActoDetalle", query = "SELECT b FROM BcActoDetalle b WHERE b.idTipoActoDetalle = :idTipoActoDetalle")})
public class BcActoDetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_acto_detalle")
    private Integer idTipoActoDetalle;
    
    @JoinColumn(name = "fk_entidad_tributo", referencedColumnName = "id_ent_tributo")
    @ManyToOne(optional = false)
    private BcEntidadTributos fkEntidadTributo;
    
    @JoinColumn(name = "fk_tipo_acto", referencedColumnName = "id_acto")
    @ManyToOne(optional = false)
    private BcActo fkTipoActo;

    public BcActoDetalle() {
    }

    public BcActoDetalle(Integer idTipoActoDetalle) {
        this.idTipoActoDetalle = idTipoActoDetalle;
    }

    public Integer getIdTipoActoDetalle() {
        return idTipoActoDetalle;
    }

    public void setIdTipoActoDetalle(Integer idTipoActoDetalle) {
        this.idTipoActoDetalle = idTipoActoDetalle;
    }

    public BcEntidadTributos getFkEntidadTributo() {
        return fkEntidadTributo;
    }

    public void setFkEntidadTributo(BcEntidadTributos fkEntidadTributo) {
        this.fkEntidadTributo = fkEntidadTributo;
    }

    public BcActo getFkTipoActo() {
        return fkTipoActo;
    }

    public void setFkTipoActo(BcActo fkTipoActo) {
        this.fkTipoActo = fkTipoActo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoActoDetalle != null ? idTipoActoDetalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BcActoDetalle)) {
            return false;
        }
        BcActoDetalle other = (BcActoDetalle) object;
        if ((this.idTipoActoDetalle == null && other.idTipoActoDetalle != null) || (this.idTipoActoDetalle != null && !this.idTipoActoDetalle.equals(other.idTipoActoDetalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gts.tributo.modelo.BcActoDetalle[ idTipoActoDetalle=" + idTipoActoDetalle + " ]";
    }
    
}
