package com.sis414.parciafinal.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "unidad_admin")
public class UnidadAdmin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 5)
    private String unidad;

    @Column(length = 120)
    private String descrip;

    @Column(length = 20)
    private String ciudad;

    private Integer estadoUni;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entidad_id", nullable = false)
    private Entidad entidad;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUnidad() { return unidad; }
    public void setUnidad(String unidad) { this.unidad = unidad; }

    public String getDescrip() { return descrip; }
    public void setDescrip(String descrip) { this.descrip = descrip; }

    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }

    public Integer getEstadoUni() { return estadoUni; }
    public void setEstadoUni(Integer estadoUni) { this.estadoUni = estadoUni; }

    public Entidad getEntidad() { return entidad; }
    public void setEntidad(Entidad entidad) { this.entidad = entidad; }
}
