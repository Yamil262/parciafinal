package com.sis414.parciafinal.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "estado")
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer codEstado;

    @Column(length = 10)
    private String nomEstado;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getCodEstado() { return codEstado; }
    public void setCodEstado(Integer codEstado) { this.codEstado = codEstado; }

    public String getNomEstado() { return nomEstado; }
    public void setNomEstado(String nomEstado) { this.nomEstado = nomEstado; }
}
