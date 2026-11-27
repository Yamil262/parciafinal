package com.sis414.parciafinal.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/** Tabla OBJGASTO.DBF del VSIAF. Clave: gestion, partida. */
@Entity
@Table(name = "vsiaf_objgasto")
@IdClass(ObjetoGasto.Key.class)
public class ObjetoGasto {

    /** DBF: GESTION */
    @Id
    private Integer gestion;

    /** DBF: PARTIDA */
    @Id
    @Column(length = 5)
    private String partida;

    /** DBF: DESCRIP */
    @Column(length = 50)
    private String descrip;

    public Integer getGestion() { return gestion; }
    public void setGestion(Integer gestion) { this.gestion = gestion; }
    public String getPartida() { return partida; }
    public void setPartida(String partida) { this.partida = partida; }
    public String getDescrip() { return descrip; }
    public void setDescrip(String descrip) { this.descrip = descrip; }

    /** Clave compuesta (gestion, partida) */
    public static class Key implements Serializable {
        private Integer gestion;
        private String partida;
        public Key() {}
        public Key(Integer gestion, String partida) {
            this.gestion = gestion;
            this.partida = partida;
        }
        public Integer getGestion() { return gestion; }
        public void setGestion(Integer gestion) { this.gestion = gestion; }
        public String getPartida() { return partida; }
        public void setPartida(String partida) { this.partida = partida; }
        @Override public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Key)) return false;
            Key x = (Key) o;
            return Objects.equals(gestion, x.gestion) && Objects.equals(partida, x.partida);
        }
        @Override public int hashCode() { return Objects.hash(gestion, partida); }
    }
}
