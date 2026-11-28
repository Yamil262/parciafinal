package com.sis414.parciafinal.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import java.math.BigDecimal;
import java.time.LocalDate;

/** Tabla resp (migrada desde RESP.DBF). */
@Entity
@Table(name = "resp")
@IdClass(RespId.class)
public class Resp {

    @Id
    @Column(name = "entidad", length = 4, nullable = false)
    private String entidad;

    @Id
    @Column(name = "unidad", length = 5, nullable = false)
    private String unidad;

    @Id
    @Column(name = "codofic", nullable = false)
    private Integer codOfic;

    @Id
    @Column(name = "codresp", nullable = false)
    private Integer codResp;

    @Column(name = "nomresp", length = 35)
    private String nomResp;

    @Column(name = "cargo", length = 35)
    private String cargo;

    @Column(name = "observ", columnDefinition = "TEXT")
    private String observ;

    @Column(name = "ci", length = 20)
    private String ci;

    @Column(name = "feult")
    private LocalDate feult;

    @Column(name = "usuar", length = 8)
    private String usuar;

    @Column(name = "cod_exp")
    private Integer codExp;

    @Column(name = "api_estado")
    private Integer apiEstado;

    /** Relación de solo lectura (sin restricción FK, los datos legados pueden tener referencias huérfanas). */
    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumns(value = {
        @JoinColumn(name = "entidad", referencedColumnName = "entidad", insertable = false, updatable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)),
        @JoinColumn(name = "unidad", referencedColumnName = "unidad", insertable = false, updatable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)),
        @JoinColumn(name = "codofic", referencedColumnName = "codofic", insertable = false, updatable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    }, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Oficina oficina;

    public Resp() {
    }

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public Integer getCodOfic() {
        return codOfic;
    }

    public void setCodOfic(Integer codOfic) {
        this.codOfic = codOfic;
    }

    public Integer getCodResp() {
        return codResp;
    }

    public void setCodResp(Integer codResp) {
        this.codResp = codResp;
    }

    public String getNomResp() {
        return nomResp;
    }

    public void setNomResp(String nomResp) {
        this.nomResp = nomResp;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getObserv() {
        return observ;
    }

    public void setObserv(String observ) {
        this.observ = observ;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public LocalDate getFeult() {
        return feult;
    }

    public void setFeult(LocalDate feult) {
        this.feult = feult;
    }

    public String getUsuar() {
        return usuar;
    }

    public void setUsuar(String usuar) {
        this.usuar = usuar;
    }

    public Integer getCodExp() {
        return codExp;
    }

    public void setCodExp(Integer codExp) {
        this.codExp = codExp;
    }

    public Integer getApiEstado() {
        return apiEstado;
    }

    public void setApiEstado(Integer apiEstado) {
        this.apiEstado = apiEstado;
    }

    public Oficina getOficina() {
        return oficina;
    }

}
