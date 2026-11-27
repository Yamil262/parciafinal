package com.sis414.parciafinal.entity;

import jakarta.persistence.*;

/** Tabla ESTADO.DBF del VSIAF. Clave: codEstado. */
@Entity
@Table(name = "vsiaf_estado")
public class Estado {

    /** DBF: CODESTADO */
    @Id
    private Integer codEstado;

    /** DBF: NOMESTADO */
    @Column(length = 10)
    private String nomEstado;

    public Integer getCodEstado() { return codEstado; }
    public void setCodEstado(Integer codEstado) { this.codEstado = codEstado; }
    public String getNomEstado() { return nomEstado; }
    public void setNomEstado(String nomEstado) { this.nomEstado = nomEstado; }
}
