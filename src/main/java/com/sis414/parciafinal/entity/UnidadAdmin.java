package com.sis414.parciafinal.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/** Tabla UNIDADADMIN.DBF del VSIAF. Clave: entidad, unidad. */
@Entity
@Table(name = "vsiaf_unidadadmin")
@IdClass(UnidadAdmin.Key.class)
public class UnidadAdmin {

    /** DBF: ENTIDAD */
    @Id
    @Column(length = 4)
    private String entidad;

    /** DBF: UNIDAD */
    @Id
    @Column(length = 5)
    private String unidad;

    /** DBF: DESCRIP */
    @Column(length = 120)
    private String descrip;

    /** DBF: CIUDAD */
    @Column(length = 20)
    private String ciudad;

    /** DBF: ESTADOUNI */
    private Integer estadoUni;

    public String getEntidad() { return entidad; }
    public void setEntidad(String entidad) { this.entidad = entidad; }
    public String getUnidad() { return unidad; }
    public void setUnidad(String unidad) { this.unidad = unidad; }
    public String getDescrip() { return descrip; }
    public void setDescrip(String descrip) { this.descrip = descrip; }
    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }
    public Integer getEstadoUni() { return estadoUni; }
    public void setEstadoUni(Integer estadoUni) { this.estadoUni = estadoUni; }

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
