package com.sis414.parciafinal.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "est_ent")
public class EstructuraEntidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer gestion;

    private Integer sectorEnt;

    private Integer subsecEnt;

    private Integer areaEnt;

    private Integer subareaEnt;

    private Integer nivelInst;

    @Column(length = 60)
    private String desEstruc;

    @Column(length = 15)
    private String siglaEstru;

    @Column(length = 1)
    private String apropiable;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

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
}
