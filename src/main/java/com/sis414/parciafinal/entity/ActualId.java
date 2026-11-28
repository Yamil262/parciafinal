package com.sis414.parciafinal.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/** Clave primaria compuesta de Actual. */
public class ActualId implements Serializable {

    private static final long serialVersionUID = 1L;

    private String entidad;
    private String unidad;
    private String codigo;

    public ActualId() {
    }

    public ActualId(String entidad, String unidad, String codigo) {
        this.entidad = entidad;
        this.unidad = unidad;
        this.codigo = codigo;
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ActualId other)) {
            return false;
        }
        return Objects.equals(entidad, other.entidad) && Objects.equals(unidad, other.unidad) && Objects.equals(codigo, other.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entidad, unidad, codigo);
    }
}
