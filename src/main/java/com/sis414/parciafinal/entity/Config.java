package com.sis414.parciafinal.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/** Tabla CONFIG.DBF del VSIAF. Clave: entidad, unidad. */
@Entity
@Table(name = "vsiaf_config")
@IdClass(Config.Key.class)
public class Config {

    /** DBF: ENTIDAD */
    @Id
    @Column(length = 4)
    private String entidad;

    /** DBF: DESCENT */
    @Column(length = 120)
    private String descEnt;

    /** DBF: UNIDAD */
    @Id
    @Column(length = 5)
    private String unidad;

    /** DBF: DESCUNI */
    @Column(length = 120)
    private String descUni;

    /** DBF: USUARIO */
    @Column(length = 10)
    private String usuario;

    public String getEntidad() { return entidad; }
    public void setEntidad(String entidad) { this.entidad = entidad; }
    public String getDescEnt() { return descEnt; }
    public void setDescEnt(String descEnt) { this.descEnt = descEnt; }
    public String getUnidad() { return unidad; }
    public void setUnidad(String unidad) { this.unidad = unidad; }
    public String getDescUni() { return descUni; }
    public void setDescUni(String descUni) { this.descUni = descUni; }
    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    /** Clave compuesta (entidad, unidad) */
    public static class Key implements Serializable {
        private String entidad;
        private String unidad;
        public Key() {}
        public Key(String entidad, String unidad) {
            this.entidad = entidad;
            this.unidad = unidad;
        }
        public String getEntidad() { return entidad; }
        public void setEntidad(String entidad) { this.entidad = entidad; }
        public String getUnidad() { return unidad; }
        public void setUnidad(String unidad) { this.unidad = unidad; }
        @Override public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Key)) return false;
            Key x = (Key) o;
            return Objects.equals(entidad, x.entidad) && Objects.equals(unidad, x.unidad);
        }
        @Override public int hashCode() { return Objects.hash(entidad, unidad); }
    }
}
