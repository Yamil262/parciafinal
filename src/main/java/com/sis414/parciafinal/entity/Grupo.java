package com.sis414.parciafinal.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "grupo")
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer codCont;

    @Column(length = 50)
    private String nombre;

    private Integer vidaUtil;

    @Column(columnDefinition = "text")
    private String observ;

    private Boolean depreciar;

    private Boolean actualizar;

    private LocalDate feult;

    @Column(length = 8)
    private String usuar;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getCodCont() { return codCont; }
    public void setCodCont(Integer codCont) { this.codCont = codCont; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Integer getVidaUtil() { return vidaUtil; }
    public void setVidaUtil(Integer vidaUtil) { this.vidaUtil = vidaUtil; }

    public String getObserv() { return observ; }
    public void setObserv(String observ) { this.observ = observ; }

    public Boolean getDepreciar() { return depreciar; }
    public void setDepreciar(Boolean depreciar) { this.depreciar = depreciar; }

    public Boolean getActualizar() { return actualizar; }
    public void setActualizar(Boolean actualizar) { this.actualizar = actualizar; }

    public LocalDate getFeult() { return feult; }
    public void setFeult(LocalDate feult) { this.feult = feult; }

    public String getUsuar() { return usuar; }
    public void setUsuar(String usuar) { this.usuar = usuar; }
}
