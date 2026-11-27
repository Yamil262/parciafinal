package com.sis414.parciafinal.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/** Tabla ORGANISMO_FIN.DBF del VSIAF. Clave: gestion, codOf. */
@Entity
@Table(name = "vsiaf_organismo_fin")
@IdClass(OrganismoFin.Key.class)
public class OrganismoFin {

    /** DBF: GESTION */
    @Id
    private Integer gestion;

    /** DBF: OF */
    @Id
    @Column(length = 3)
    private String codOf;

    /** DBF: DES */
    @Column(length = 100)
    private String descripcion;

    /** DBF: SIGLA */
    @Column(length = 15)
    private String sigla;

    public Integer getGestion() { return gestion; }
    public void setGestion(Integer gestion) { this.gestion = gestion; }
    public String getCodOf() { return codOf; }
    public void setCodOf(String codOf) { this.codOf = codOf; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getSigla() { return sigla; }
    public void setSigla(String sigla) { this.sigla = sigla; }

    /** Clave compuesta (gestion, codOf) */
    public static class Key implements Serializable {
        private Integer gestion;
        private String codOf;
        public Key() {}
        public Key(Integer gestion, String codOf) {
            this.gestion = gestion;
            this.codOf = codOf;
        }
        public Integer getGestion() { return gestion; }
        public void setGestion(Integer gestion) { this.gestion = gestion; }
        public String getCodOf() { return codOf; }
        public void setCodOf(String codOf) { this.codOf = codOf; }
        @Override public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Key)) return false;
            Key x = (Key) o;
            return Objects.equals(gestion, x.gestion) && Objects.equals(codOf, x.codOf);
        }
        @Override public int hashCode() { return Objects.hash(gestion, codOf); }
    }
}
