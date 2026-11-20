package com.sis414.parciafinal.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cla_depts")
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer codigo;

    @Column(length = 15)
    private String descripcion;

    @Column(length = 10)
    private String sigla;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getCodigo() { return codigo; }
    public void setCodigo(Integer codigo) { this.codigo = codigo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getSigla() { return sigla; }
    public void setSigla(String sigla) { this.sigla = sigla; }
}
