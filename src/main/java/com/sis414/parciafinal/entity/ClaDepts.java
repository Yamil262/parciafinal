package com.sis414.parciafinal.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import java.math.BigDecimal;
import java.time.LocalDate;

/** Tabla cla_depts (migrada desde CLA_DEPTS.DBF). */
@Entity
@Table(name = "cla_depts")
public class ClaDepts {

    @Id
    @Column(name = "codigo", nullable = false)
    private Integer codigo;

    @Column(name = "descripcion", length = 15)
    private String descripcion;

    @Column(name = "sigla", length = 10)
    private String sigla;

    public ClaDepts() {
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

}
