package com.sis414.parciafinal.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/** Clave primaria compuesta de Entidades. */
public class EntidadesId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer gestion;
    private String entidad;

    public EntidadesId() {
    }

    public EntidadesId(Integer gestion, String entidad) {
        this.gestion = gestion;
        this.entidad = entidad;
    }

    public Integer getGestion() {
        return gestion;
    }

    public void setGestion(Integer gestion) {
        this.gestion = gestion;
    }

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EntidadesId other)) {
            return false;
        }
        return Objects.equals(gestion, other.gestion) && Objects.equals(entidad, other.entidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gestion, entidad);
    }
}
