package com.sis414.parciafinal.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import java.math.BigDecimal;
import java.time.LocalDate;

/** Tabla baja (migrada desde BAJA.DBF). */
@Entity
@Table(name = "baja")
public class MotivoBaja {

    @Id
    @Column(name = "codbaja", nullable = false)
    private Integer codBaja;

    @Column(name = "descbaja", length = 120)
    private String descBaja;

    public MotivoBaja() {
    }

    public Integer getCodBaja() {
        return codBaja;
    }

    public void setCodBaja(Integer codBaja) {
        this.codBaja = codBaja;
    }

    public String getDescBaja() {
        return descBaja;
    }

    public void setDescBaja(String descBaja) {
        this.descBaja = descBaja;
    }

}
