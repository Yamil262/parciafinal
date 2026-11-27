package com.sis414.parciafinal.entity;

import jakarta.persistence.*;

/** Tabla BAJA.DBF del VSIAF. Clave: codBaja. */
@Entity
@Table(name = "vsiaf_baja")
public class MotivoBaja {

    /** DBF: CODBAJA */
    @Id
    private Integer codBaja;

    /** DBF: DESCBAJA */
    @Column(length = 120)
    private String descBaja;

    public Integer getCodBaja() { return codBaja; }
    public void setCodBaja(Integer codBaja) { this.codBaja = codBaja; }
    public String getDescBaja() { return descBaja; }
    public void setDescBaja(String descBaja) { this.descBaja = descBaja; }
}
