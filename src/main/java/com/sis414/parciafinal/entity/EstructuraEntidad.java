package com.sis414.parciafinal.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/** Tabla EST_ENT.DBF del VSIAF. Clave: gestion, sectorEnt, subsecEnt, areaEnt, subareaEnt, nivelInst. */
@Entity
@Table(name = "vsiaf_est_ent")
@IdClass(EstructuraEntidad.Key.class)
public class EstructuraEntidad {

    /** DBF: GESTION */
    @Id
    private Integer gestion;

    /** DBF: SECTOR_ENT */
    @Id
    private Integer sectorEnt;

    /** DBF: SUBSEC_ENT */
    @Id
    private Integer subsecEnt;

    /** DBF: AREA_ENT */
    @Id
    private Integer areaEnt;

    /** DBF: SUBAREAENT */
    @Id
    private Integer subareaEnt;

    /** DBF: NIVEL_INST */
    @Id
    private Integer nivelInst;

    /** DBF: DES_ESTRUC */
    @Column(length = 60)
    private String desEstruc;

    /** DBF: SIGLAESTRU */
    @Column(length = 15)
    private String siglaEstru;

    /** DBF: APROPIABLE */
    @Column(length = 1)
    private String apropiable;

    public Integer getGestion() { return gestion; }
    public void setGestion(Integer gestion) { this.gestion = gestion; }
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
    public String getDesEstruc() { return desEstruc; }
    public void setDesEstruc(String desEstruc) { this.desEstruc = desEstruc; }
    public String getSiglaEstru() { return siglaEstru; }
    public void setSiglaEstru(String siglaEstru) { this.siglaEstru = siglaEstru; }
    public String getApropiable() { return apropiable; }
    public void setApropiable(String apropiable) { this.apropiable = apropiable; }

    /** Clave compuesta (gestion, sectorEnt, subsecEnt, areaEnt, subareaEnt, nivelInst) */
    public static class Key implements Serializable {
        private Integer gestion;
        private Integer sectorEnt;
        private Integer subsecEnt;
        private Integer areaEnt;
        private Integer subareaEnt;
        private Integer nivelInst;
        public Key() {}
        public Key(Integer gestion, Integer sectorEnt, Integer subsecEnt, Integer areaEnt, Integer subareaEnt, Integer nivelInst) {
            this.gestion = gestion;
            this.sectorEnt = sectorEnt;
            this.subsecEnt = subsecEnt;
            this.areaEnt = areaEnt;
            this.subareaEnt = subareaEnt;
            this.nivelInst = nivelInst;
        }
        public Integer getGestion() { return gestion; }
        public void setGestion(Integer gestion) { this.gestion = gestion; }
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
        @Override public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Key)) return false;
            Key x = (Key) o;
            return Objects.equals(gestion, x.gestion) && Objects.equals(sectorEnt, x.sectorEnt) && Objects.equals(subsecEnt, x.subsecEnt) && Objects.equals(areaEnt, x.areaEnt) && Objects.equals(subareaEnt, x.subareaEnt) && Objects.equals(nivelInst, x.nivelInst);
        }
        @Override public int hashCode() { return Objects.hash(gestion, sectorEnt, subsecEnt, areaEnt, subareaEnt, nivelInst); }
    }
}
