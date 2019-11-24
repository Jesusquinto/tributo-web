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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "ma_perfiles")
public class MaPerfiles implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_ma_perfiles")
    private Integer idMaPerfiles;
    
    @Column(name = "nombre_perfil")
    @NotNull
    @Basic(optional = false)
    @Size(min = 1, max = 50)
    private String nombrePerfil;
    
       
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

	private static final long serialVersionUID = 1L;
    
}
