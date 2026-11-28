package com.sis414.parciafinal.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import java.math.BigDecimal;
import java.time.LocalDate;

/** Tabla est_ent (migrada desde EST_ENT.DBF). */
@Entity
@Table(name = "est_ent")
@IdClass(EstEntId.class)
public class EstEnt {

    @Id
    @Column(name = "gestion", nullable = false)
    private Integer gestion;

    @Id
    @Column(name = "sector_ent", nullable = false)
    private Integer sectorEnt;

    @Id
    @Column(name = "subsec_ent", nullable = false)
    private Integer subsecEnt;

    @Id
    @Column(name = "area_ent", nullable = false)
    private Integer areaEnt;

    @Id
    @Column(name = "subareaent", nullable = false)
    private Integer subAreaEnt;

    @Id
    @Column(name = "nivel_inst", nullable = false)
    private Integer nivelInst;

    @Column(name = "des_estruc", length = 60)
    private String desEstruc;

    @Column(name = "siglaestru", length = 15)
    private String siglaEstru;

    @Column(name = "apropiable", length = 1)
    private String apropiable;

    public EstEnt() {
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

    public String getDesEstruc() {
        return desEstruc;
    }

    public void setDesEstruc(String desEstruc) {
        this.desEstruc = desEstruc;
    }

    public String getSiglaEstru() {
        return siglaEstru;
    }

    public void setSiglaEstru(String siglaEstru) {
        this.siglaEstru = siglaEstru;
    }

    public String getApropiable() {
        return apropiable;
    }

    public void setApropiable(String apropiable) {
        this.apropiable = apropiable;
    }

}
