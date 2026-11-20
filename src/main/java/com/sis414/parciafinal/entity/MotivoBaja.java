package com.sis414.parciafinal.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "motivo_baja")
public class MotivoBaja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer codBaja;

    @Column(length = 120)
    private String descBaja;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getCodBaja() { return codBaja; }
    public void setCodBaja(Integer codBaja) { this.codBaja = codBaja; }

    public String getDescBaja() { return descBaja; }
    public void setDescBaja(String descBaja) { this.descBaja = descBaja; }
}
