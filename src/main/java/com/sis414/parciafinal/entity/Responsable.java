package com.sis414.parciafinal.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "responsable")
public class Responsable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer codResp;

    @Column(length = 35)
    private String nomResp;

    @Column(length = 35)
    private String cargo;

    @Column(columnDefinition = "text")
    private String observ;

    @Column(length = 20)
    private String ci;

    private LocalDate feult;

    @Column(length = 8)
    private String usuar;

    private Integer apiEstado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "oficina_id", nullable = false)
    private Oficina oficina;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departamento_exp_id", nullable = true)
    private Departamento departamentoExp;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getCodResp() { return codResp; }
    public void setCodResp(Integer codResp) { this.codResp = codResp; }

    public String getNomResp() { return nomResp; }
    public void setNomResp(String nomResp) { this.nomResp = nomResp; }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    public String getObserv() { return observ; }
    public void setObserv(String observ) { this.observ = observ; }

    public String getCi() { return ci; }
    public void setCi(String ci) { this.ci = ci; }

    public LocalDate getFeult() { return feult; }
    public void setFeult(LocalDate feult) { this.feult = feult; }

    public String getUsuar() { return usuar; }
    public void setUsuar(String usuar) { this.usuar = usuar; }

    public Integer getApiEstado() { return apiEstado; }
    public void setApiEstado(Integer apiEstado) { this.apiEstado = apiEstado; }

    public Oficina getOficina() { return oficina; }
    public void setOficina(Oficina oficina) { this.oficina = oficina; }

    public Departamento getDepartamentoExp() { return departamentoExp; }
    public void setDepartamentoExp(Departamento departamentoExp) { this.departamentoExp = departamentoExp; }
}
