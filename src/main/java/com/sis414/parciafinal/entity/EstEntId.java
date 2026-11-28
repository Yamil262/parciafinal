package com.sis414.parciafinal.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/** Clave primaria compuesta de EstEnt. */
public class EstEntId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer gestion;
    private Integer sectorEnt;
    private Integer subsecEnt;
    private Integer areaEnt;
    private Integer subAreaEnt;
    private Integer nivelInst;

    public EstEntId() {
    }

    public EstEntId(Integer gestion, Integer sectorEnt, Integer subsecEnt, Integer areaEnt, Integer subAreaEnt, Integer nivelInst) {
        this.gestion = gestion;
        this.sectorEnt = sectorEnt;
        this.subsecEnt = subsecEnt;
        this.areaEnt = areaEnt;
        this.subAreaEnt = subAreaEnt;
        this.nivelInst = nivelInst;
    }

    public Integer getGestion() {
        return gestion;
    }

    public void setGestion(Integer gestion) {
        this.gestion = gestion;
    }

    public Integer getSectorEnt() {
        return sectorEnt;
    }

    public void setSectorEnt(Integer sectorEnt) {
        this.sectorEnt = sectorEnt;
    }

    public Integer getSubsecEnt() {
        return subsecEnt;
    }

    public void setSubsecEnt(Integer subsecEnt) {
        this.subsecEnt = subsecEnt;
    }

    public Integer getAreaEnt() {
        return areaEnt;
    }

    public void setAreaEnt(Integer areaEnt) {
        this.areaEnt = areaEnt;
    }

    public Integer getSubAreaEnt() {
        return subAreaEnt;
    }

    public void setSubAreaEnt(Integer subAreaEnt) {
        this.subAreaEnt = subAreaEnt;
    }

    public Integer getNivelInst() {
        return nivelInst;
    }

    public void setNivelInst(Integer nivelInst) {
        this.nivelInst = nivelInst;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EstEntId other)) {
            return false;
        }
        return Objects.equals(gestion, other.gestion) && Objects.equals(sectorEnt, other.sectorEnt) && Objects.equals(subsecEnt, other.subsecEnt) && Objects.equals(areaEnt, other.areaEnt) && Objects.equals(subAreaEnt, other.subAreaEnt) && Objects.equals(nivelInst, other.nivelInst);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gestion, sectorEnt, subsecEnt, areaEnt, subAreaEnt, nivelInst);
    }
}
