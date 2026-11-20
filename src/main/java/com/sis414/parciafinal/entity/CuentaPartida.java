package com.sis414.parciafinal.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cuenta_partida")
public class CuentaPartida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 5)
    private String partida;

    private Integer gestion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grupo_id", nullable = false)
    private Grupo grupo;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPartida() { return partida; }
    public void setPartida(String partida) { this.partida = partida; }

    public Integer getGestion() { return gestion; }
    public void setGestion(Integer gestion) { this.gestion = gestion; }

    public Grupo getGrupo() { return grupo; }
    public void setGrupo(Grupo grupo) { this.grupo = grupo; }
}
