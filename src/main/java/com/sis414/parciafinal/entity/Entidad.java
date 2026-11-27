package com.sis414.parciafinal.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/** Tabla ENTIDADES.DBF del VSIAF. Clave: gestion, entidad. */
@Entity
@Table(name = "vsiaf_entidades")
@IdClass(Entidad.Key.class)
public class Entidad {

    /** DBF: GESTION */
    @Id
    private Integer gestion;

    /** DBF: ENTIDAD */
    @Id
    @Column(length = 4)
    private String entidad;

    /** DBF: DESC_ENT */
    @Column(length = 110)
    private String descEnt;

    /** DBF: SIGLA_ENT */
    @Column(length = 15)
    private String siglaEnt;

    /** DBF: SECTOR_ENT */
    private Integer sectorEnt;

    /** DBF: SUBSEC_ENT */
    private Integer subsecEnt;

    /** DBF: AREA_ENT */
    private Integer areaEnt;

    /** DBF: SUBAREAENT */
    private Integer subareaEnt;

    /** DBF: NIVEL_INST */
    private Integer nivelInst;

    public Integer getGestion() { return gestion; }
    public void setGestion(Integer gestion) { this.gestion = gestion; }
    public String getEntidad() { return entidad; }
    public void setEntidad(String entidad) { this.entidad = entidad; }
    public String getDescEnt() { return descEnt; }
    public void setDescEnt(String descEnt) { this.descEnt = descEnt; }
    public String getSiglaEnt() { return siglaEnt; }
    public void setSiglaEnt(String siglaEnt) { this.siglaEnt = siglaEnt; }
    public Integer getSectorEnt() { return sectorEnt; }
    public void setSectorEnt(Integer sectorEnt) { this.sectorEnt = sectorEnt; }
    public Integer getSubsecEnt() { return subsecEnt; }
    public void setSubsecEnt(Integer subsecEnt) { this.subsecEnt = subsecEnt; }
    public Integer getAreaEnt() { return areaEnt; }
    public void setAreaEnt(Integer areaEnt) { this.areaEnt = areaEnt; }
    public Integer getSubareaEnt() { return subareaEnt; }
    public void setSubareaEnt(Integer subareaEnt) { this.subareaEnt = subareaEnt; }
    public Integer getNivelInst() { return nivelInst; }
    public void setNivelInst(Integer nivelInst) { this.nivelInst = nivelInst; }

    /** Clave compuesta (gestion, entidad) */
    public static class Key implements Serializable {
        private Integer gestion;
        private String entidad;
        public Key() {}
        public Key(Integer gestion, String entidad) {
            this.gestion = gestion;
            this.entidad = entidad;
        }
        public Integer getGestion() { return gestion; }
        public void setGestion(Integer gestion) { this.gestion = gestion; }
        public String getEntidad() { return entidad; }
        public void setEntidad(String entidad) { this.entidad = entidad; }
        @Override public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Key)) return false;
            Key x = (Key) o;
            return Objects.equals(gestion, x.gestion) && Objects.equals(entidad, x.entidad);
        }
        @Override public int hashCode() { return Objects.hash(gestion, entidad); }
    }
}
