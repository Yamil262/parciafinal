package com.sis414.parciafinal.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/** Clave primaria compuesta de OrganismoFin. */
public class OrganismoFinId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer gestion;
    private String codOf;

    public OrganismoFinId() {
    }

    public OrganismoFinId(Integer gestion, String codOf) {
        this.gestion = gestion;
        this.codOf = codOf;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrganismoFinId other)) {
            return false;
        }
        return Objects.equals(gestion, other.gestion) && Objects.equals(codOf, other.codOf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gestion, codOf);
    }
}
