package com.sis414.parciafinal.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "objeto_gasto")
public class ObjetoGasto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer gestion;

    @Column(length = 5)
    private String partida;

    @Column(length = 50)
    private String descrip;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getGestion() { return gestion; }
    public void setGestion(Integer gestion) { this.gestion = gestion; }

    public String getPartida() { return partida; }
    public void setPartida(String partida) { this.partida = partida; }

    public String getDescrip() { return descrip; }
    public void setDescrip(String descrip) { this.descrip = descrip; }
}
