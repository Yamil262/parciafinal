package com.sis414.parciafinal.entity;

import jakarta.persistence.*;

/** Tabla MES.DBF del VSIAF. Clave: mes. */
@Entity
@Table(name = "vsiaf_mes")
public class Mes {

    /** DBF: MES */
    @Id
    private Integer mes;

    /** DBF: NOMMES */
    @Column(length = 10)
    private String nomMes;

    public Integer getMes() { return mes; }
    public void setMes(Integer mes) { this.mes = mes; }
    public String getNomMes() { return nomMes; }
    public void setNomMes(String nomMes) { this.nomMes = nomMes; }
}
