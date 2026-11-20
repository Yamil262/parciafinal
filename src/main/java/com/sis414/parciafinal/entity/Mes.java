package com.sis414.parciafinal.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "mes")
public class Mes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer mes;

    @Column(length = 10)
    private String nomMes;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getMes() { return mes; }
    public void setMes(Integer mes) { this.mes = mes; }

    public String getNomMes() { return nomMes; }
    public void setNomMes(String nomMes) { this.nomMes = nomMes; }
}
