package com.sis414.parciafinal.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "entidades")
public class Entidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer gestion;

    @Column(length = 4)
    private String codigo;

    @Column(length = 110)
    private String descEnt;

    @Column(length = 15)
    private String siglaEnt;

    private Integer sectorEnt;

    private Integer subsecEnt;

    private Integer areaEnt;

    private Integer subareaEnt;

    private Integer nivelInst;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estructura_id", nullable = true)
    private EstructuraEntidad estructura;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getGestion() { return gestion; }
    public void setGestion(Integer gestion) { this.gestion = gestion; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

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

    public EstructuraEntidad getEstructura() { return estructura; }
    public void setEstructura(EstructuraEntidad estructura) { this.estructura = estructura; }
}
