package com.sis414.parciafinal.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import java.math.BigDecimal;
import java.time.LocalDate;

/** Tabla estado (migrada desde ESTADO.DBF). */
@Entity
@Table(name = "estado")
public class Estado {

    @Id
    @Column(name = "codestado", nullable = false)
    private Integer codEstado;

    @Column(name = "nomestado", length = 10)
    private String nomEstado;

    public Estado() {
    }

    public Integer getCodEstado() {
        return codEstado;
    }

    public void setCodEstado(Integer codEstado) {
        this.codEstado = codEstado;
    }

    public String getNomEstado() {
        return nomEstado;
    }

    public void setNomEstado(String nomEstado) {
        this.nomEstado = nomEstado;
    }

}
