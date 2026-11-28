package com.sis414.parciafinal.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/** Clave primaria compuesta de Resp. */
public class RespId implements Serializable {

    private static final long serialVersionUID = 1L;

    private String entidad;
    private String unidad;
    private Integer codOfic;
    private Integer codResp;

    public RespId() {
    }

    public RespId(String entidad, String unidad, Integer codOfic, Integer codResp) {
        this.entidad = entidad;
        this.unidad = unidad;
        this.codOfic = codOfic;
        this.codResp = codResp;
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

    public Integer getCodOfic() {
        return codOfic;
    }

    public void setCodOfic(Integer codOfic) {
        this.codOfic = codOfic;
    }

    public Integer getCodResp() {
        return codResp;
    }

    public void setCodResp(Integer codResp) {
        this.codResp = codResp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RespId other)) {
            return false;
        }
        return Objects.equals(entidad, other.entidad) && Objects.equals(unidad, other.unidad) && Objects.equals(codOfic, other.codOfic) && Objects.equals(codResp, other.codResp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entidad, unidad, codOfic, codResp);
    }
}
