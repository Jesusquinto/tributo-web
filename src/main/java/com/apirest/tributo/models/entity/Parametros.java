package com.apirest.tributo.models.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "parametros")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Parametros.findAll", query = "SELECT p FROM Parametros p"),
    @NamedQuery(name = "Parametros.findByIdParametro", query = "SELECT p FROM Parametros p WHERE p.idParametro = :idParametro"),
    @NamedQuery(name = "Parametros.findByCodigo", query = "SELECT p FROM Parametros p WHERE p.codigo = :codigo"),
    @NamedQuery(name = "Parametros.findByValor", query = "SELECT p FROM Parametros p WHERE p.valor = :valor"),
    @NamedQuery(name = "Parametros.findByFecha", query = "SELECT p FROM Parametros p WHERE p.fecha = :fecha"),
    @NamedQuery(name = "Parametros.findByNombre", query = "SELECT p FROM Parametros p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Parametros.findByNombreCorto", query = "SELECT p FROM Parametros p WHERE p.nombreCorto = :nombreCorto")})
public class Parametros implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_parametro")
    private Integer idParametro;
    @Basic(optional = false)
    @Column(name = "codigo")
    private String codigo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "valor")
    private BigDecimal valor;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "nombre_corto")
    private String nombreCorto;
    @JoinColumn(name = "fk_entidad", referencedColumnName = "id_entidad")
    @ManyToOne(optional = false)
    private BcEntidad fkEntidad;
    @JoinColumn(name = "fk_grupo", referencedColumnName = "id_grupo")
    @ManyToOne(optional = false)
    private Grupos fkGrupo;

    public Parametros() {
    }

    public Parametros(Integer idParametro) {
        this.idParametro = idParametro;
    }

    public Parametros(Integer idParametro, String codigo, BigDecimal valor, String nombre, String nombreCorto) {
        this.idParametro = idParametro;
        this.codigo = codigo;
        this.valor = valor;
        this.nombre = nombre;
        this.nombreCorto = nombreCorto;
    }

    public Integer getIdParametro() {
        return idParametro;
    }

    public void setIdParametro(Integer idParametro) {
        this.idParametro = idParametro;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreCorto() {
        return nombreCorto;
    }

    public void setNombreCorto(String nombreCorto) {
        this.nombreCorto = nombreCorto;
    }

    public BcEntidad getFkEntidad() {
        return fkEntidad;
    }

    public void setFkEntidad(BcEntidad fkEntidad) {
        this.fkEntidad = fkEntidad;
    }

    public Grupos getFkGrupo() {
        return fkGrupo;
    }

    public void setFkGrupo(Grupos fkGrupo) {
        this.fkGrupo = fkGrupo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idParametro != null ? idParametro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Parametros)) {
            return false;
        }
        Parametros other = (Parametros) object;
        if ((this.idParametro == null && other.idParametro != null) || (this.idParametro != null && !this.idParametro.equals(other.idParametro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "test.Parametros[ idParametro=" + idParametro + " ]";
    }

}
