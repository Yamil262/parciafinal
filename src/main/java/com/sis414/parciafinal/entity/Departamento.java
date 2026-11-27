package com.sis414.parciafinal.entity;

import jakarta.persistence.*;

/** Tabla CLA_DEPTS.DBF del VSIAF. Clave: codigo. */
@Entity
@Table(name = "vsiaf_cla_depts")
public class Departamento {

    /** DBF: CODIGO */
    @Id
    private Integer codigo;

    /** DBF: DESC */
    @Column(length = 15)
    private String descripcion;

    /** DBF: SIGLA */
    @Column(length = 10)
    private String sigla;

    public Integer getCodigo() { return codigo; }
    public void setCodigo(Integer codigo) { this.codigo = codigo; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getSigla() { return sigla; }
    public void setSigla(String sigla) { this.sigla = sigla; }
}
