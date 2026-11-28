package com.sis414.parciafinal.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import java.math.BigDecimal;
import java.time.LocalDate;

/** Tabla mes (migrada desde MES.DBF). */
@Entity
@Table(name = "mes")
public class Mes {

    @Id
    @Column(name = "mes", nullable = false)
    private Integer mes;

    @Column(name = "nommes", length = 10)
    private String nomMes;

    public Mes() {
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public String getNomMes() {
        return nomMes;
    }

    public void setNomMes(String nomMes) {
        this.nomMes = nomMes;
    }

}
