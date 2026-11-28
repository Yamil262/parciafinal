package com.sis414.parciafinal.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/** Clave primaria compuesta de ObjGasto. */
public class ObjGastoId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer gestion;
    private String partida;

    public ObjGastoId() {
    }

    public ObjGastoId(Integer gestion, String partida) {
        this.gestion = gestion;
        this.partida = partida;
    }

    public Integer getGestion() {
        return gestion;
    }

    public void setGestion(Integer gestion) {
        this.gestion = gestion;
    }

    public String getPartida() {
        return partida;
    }

    public void setPartida(String partida) {
        this.partida = partida;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ObjGastoId other)) {
            return false;
        }
        return Objects.equals(gestion, other.gestion) && Objects.equals(partida, other.partida);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gestion, partida);
    }
}
