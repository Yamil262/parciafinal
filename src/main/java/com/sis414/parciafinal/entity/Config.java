package com.sis414.parciafinal.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "config")
public class Config {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 120)
    private String descEnt;

    @Column(length = 120)
    private String descUni;

    @Column(length = 10)
    private String usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unidad_id", nullable = false)
    private UnidadAdmin unidad;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescEnt() { return descEnt; }
    public void setDescEnt(String descEnt) { this.descEnt = descEnt; }

    public String getDescUni() { return descUni; }
    public void setDescUni(String descUni) { this.descUni = descUni; }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public UnidadAdmin getUnidad() { return unidad; }
    public void setUnidad(UnidadAdmin unidad) { this.unidad = unidad; }
}
