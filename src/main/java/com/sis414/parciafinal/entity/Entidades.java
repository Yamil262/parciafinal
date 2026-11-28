package com.sis414.parciafinal.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import java.math.BigDecimal;
import java.time.LocalDate;

/** Tabla entidades (migrada desde ENTIDADES.DBF). */
@Entity
@Table(name = "entidades")
@IdClass(EntidadesId.class)
public class Entidades {

    @Id
    @Column(name = "gestion", nullable = false)
    private Integer gestion;

    @Id
    @Column(name = "entidad", length = 4, nullable = false)
    private String entidad;

    @Column(name = "desc_ent", length = 110)
    private String descEnt;

    @Column(name = "sigla_ent", length = 15)
    private String siglaEnt;

    @Column(name = "sector_ent")
    private Integer sectorEnt;

    @Column(name = "subsec_ent")
    private Integer subsecEnt;

    @Column(name = "area_ent")
    private Integer areaEnt;

    @Column(name = "subareaent")
    private Integer subAreaEnt;

    @Column(name = "nivel_inst")
    private Integer nivelInst;

    /** Relación de solo lectura (sin restricción FK, los datos legados pueden tener referencias huérfanas). */
    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumns(value = {
        @JoinColumn(name = "gestion", referencedColumnName = "gestion", insertable = false, updatable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)),
        @JoinColumn(name = "sector_ent", referencedColumnName = "sector_ent", insertable = false, updatable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)),
        @JoinColumn(name = "subsec_ent", referencedColumnName = "subsec_ent", insertable = false, updatable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)),
        @JoinColumn(name = "area_ent", referencedColumnName = "area_ent", insertable = false, updatable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)),
        @JoinColumn(name = "subareaent", referencedColumnName = "subareaent", insertable = false, updatable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)),
        @JoinColumn(name = "nivel_inst", referencedColumnName = "nivel_inst", insertable = false, updatable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    }, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private EstEnt estructura;

    public Entidades() {
    }

    public Integer getGestion() {
        return gestion;
    }

    public void setGestion(Integer gestion) {
        this.gestion = gestion;
    }

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public String getDescEnt() {
        return descEnt;
    }

    public void setDescEnt(String descEnt) {
        this.descEnt = descEnt;
    }

    public String getSiglaEnt() {
        return siglaEnt;
    }

    public void setSiglaEnt(String siglaEnt) {
        this.siglaEnt = siglaEnt;
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

    public EstEnt getEstructura() {
        return estructura;
    }

}
