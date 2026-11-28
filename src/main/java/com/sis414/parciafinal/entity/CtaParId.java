package com.sis414.parciafinal.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/** Clave primaria compuesta de CtaPar. */
public class CtaParId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer codCont;
    private String partida;
    private Integer gestion;

    public CtaParId() {
    }

    public CtaParId(Integer codCont, String partida, Integer gestion) {
        this.codCont = codCont;
        this.partida = partida;
        this.gestion = gestion;
    }

    public Integer getCodCont() {
        return codCont;
    }

    public void setCodCont(Integer codCont) {
        this.codCont = codCont;
    }

    public String getPartida() {
        return partida;
    }

    public void setPartida(String partida) {
        this.partida = partida;
    }

    public Integer getGestion() {
        return gestion;
    }

    public void setGestion(Integer gestion) {
        this.gestion = gestion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CtaParId other)) {
            return false;
        }
        return Objects.equals(codCont, other.codCont) && Objects.equals(partida, other.partida) && Objects.equals(gestion, other.gestion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codCont, partida, gestion);
    }
}
