package com.sis414.parciafinal.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/** Clave primaria compuesta de UnidadAdmin. */
public class UnidadAdminId implements Serializable {

    private static final long serialVersionUID = 1L;

    private String entidad;
    private String unidad;

    public UnidadAdminId() {
    }

    public UnidadAdminId(String entidad, String unidad) {
        this.entidad = entidad;
        this.unidad = unidad;
    }

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UnidadAdminId other)) {
            return false;
        }
        return Objects.equals(entidad, other.entidad) && Objects.equals(unidad, other.unidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entidad, unidad);
    }
}
