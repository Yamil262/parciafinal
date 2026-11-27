package com.sis414.parciafinal.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/** Tabla CTA_PAR.DBF del VSIAF. Clave: codCont, partida, gestion. */
@Entity
@Table(name = "vsiaf_cta_par")
@IdClass(CuentaPartida.Key.class)
public class CuentaPartida {

    /** DBF: CODCONT */
    @Id
    private Integer codCont;

    /** DBF: PARTIDA */
    @Id
    @Column(length = 5)
    private String partida;

    /** DBF: GESTION */
    @Id
    private Integer gestion;

    public Integer getCodCont() { return codCont; }
    public void setCodCont(Integer codCont) { this.codCont = codCont; }
    public String getPartida() { return partida; }
    public void setPartida(String partida) { this.partida = partida; }
    public Integer getGestion() { return gestion; }
    public void setGestion(Integer gestion) { this.gestion = gestion; }

    /** Clave compuesta (codCont, partida, gestion) */
    public static class Key implements Serializable {
        private Integer codCont;
        private String partida;
        private Integer gestion;
        public Key() {}
        public Key(Integer codCont, String partida, Integer gestion) {
            this.codCont = codCont;
            this.partida = partida;
            this.gestion = gestion;
        }
        public Integer getCodCont() { return codCont; }
        public void setCodCont(Integer codCont) { this.codCont = codCont; }
        public String getPartida() { return partida; }
        public void setPartida(String partida) { this.partida = partida; }
        public Integer getGestion() { return gestion; }
        public void setGestion(Integer gestion) { this.gestion = gestion; }
        @Override public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Key)) return false;
            Key x = (Key) o;
            return Objects.equals(codCont, x.codCont) && Objects.equals(partida, x.partida) && Objects.equals(gestion, x.gestion);
        }
        @Override public int hashCode() { return Objects.hash(codCont, partida, gestion); }
    }
}
