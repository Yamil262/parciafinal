package com.sis414.parciafinal.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/** Clave primaria compuesta de Auxiliar. */
public class AuxiliarId implements Serializable {

    private static final long serialVersionUID = 1L;

    private String entidad;
    private String unidad;
    private Integer codCont;
    private Integer codAux;

    public AuxiliarId() {
    }

    public AuxiliarId(String entidad, String unidad, Integer codCont, Integer codAux) {
        this.entidad = entidad;
        this.unidad = unidad;
        this.codCont = codCont;
        this.codAux = codAux;
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

    public Integer getCodCont() {
        return codCont;
    }

    public void setCodCont(Integer codCont) {
        this.codCont = codCont;
    }

    public Integer getCodAux() {
        return codAux;
    }

    public void setCodAux(Integer codAux) {
        this.codAux = codAux;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AuxiliarId other)) {
            return false;
        }
        return Objects.equals(entidad, other.entidad) && Objects.equals(unidad, other.unidad) && Objects.equals(codCont, other.codCont) && Objects.equals(codAux, other.codAux);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entidad, unidad, codCont, codAux);
    }
}
