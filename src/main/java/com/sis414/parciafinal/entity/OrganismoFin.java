package com.sis414.parciafinal.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import java.math.BigDecimal;
import java.time.LocalDate;

/** Tabla organismo_fin (migrada desde ORGANISMO_FIN.DBF). */
@Entity
@Table(name = "organismo_fin")
@IdClass(OrganismoFinId.class)
public class OrganismoFin {

    @Id
    @Column(name = "gestion", nullable = false)
    private Integer gestion;

    @Id
    @Column(name = "cod_of", length = 3, nullable = false)
    private String codOf;

    @Column(name = "des", length = 100)
    private String des;

    @Column(name = "sigla", length = 15)
    private String sigla;

    public OrganismoFin() {
    }

    public Integer getGestion() {
        return gestion;
    }

    public void setGestion(Integer gestion) {
        this.gestion = gestion;
    }

    public String getCodOf() {
        return codOf;
    }

    public void setCodOf(String codOf) {
        this.codOf = codOf;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

}
