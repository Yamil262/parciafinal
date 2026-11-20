package com.sis414.parciafinal.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "auxiliar")
public class Auxiliar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer codAux;

    @Column(length = 50)
    private String nomAux;

    @Column(columnDefinition = "text")
    private String observ;

    private LocalDate feult;

    @Column(length = 8)
    private String usuar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unidad_id", nullable = false)
    private UnidadAdmin unidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grupo_id", nullable = false)
    private Grupo grupo;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getCodAux() { return codAux; }
    public void setCodAux(Integer codAux) { this.codAux = codAux; }

    public String getNomAux() { return nomAux; }
    public void setNomAux(String nomAux) { this.nomAux = nomAux; }

    public String getObserv() { return observ; }
    public void setObserv(String observ) { this.observ = observ; }

    public LocalDate getFeult() { return feult; }
    public void setFeult(LocalDate feult) { this.feult = feult; }

    public String getUsuar() { return usuar; }
    public void setUsuar(String usuar) { this.usuar = usuar; }

    public UnidadAdmin getUnidad() { return unidad; }
    public void setUnidad(UnidadAdmin unidad) { this.unidad = unidad; }

    public Grupo getGrupo() { return grupo; }
    public void setGrupo(Grupo grupo) { this.grupo = grupo; }
}
