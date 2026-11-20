package com.sis414.parciafinal.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "organismo_fin")
public class OrganismoFin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer gestion;

    @Column(length = 3)
    private String codigo;

    @Column(length = 100)
    private String descripcion;

    @Column(length = 15)
    private String sigla;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getGestion() { return gestion; }
    public void setGestion(Integer gestion) { this.gestion = gestion; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getSigla() { return sigla; }
    public void setSigla(String sigla) { this.sigla = sigla; }
}
