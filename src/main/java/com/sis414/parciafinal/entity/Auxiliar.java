package com.sis414.parciafinal.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import java.math.BigDecimal;
import java.time.LocalDate;

/** Tabla auxiliar (migrada desde AUXILIAR.DBF). */
@Entity
@Table(name = "auxiliar")
@IdClass(AuxiliarId.class)
public class Auxiliar {

    @Id
    @Column(name = "entidad", length = 4, nullable = false)
    private String entidad;

    @Id
    @Column(name = "unidad", length = 5, nullable = false)
    private String unidad;

    @Id
    @Column(name = "codcont", nullable = false)
    private Integer codCont;

    @Id
    @Column(name = "codaux", nullable = false)
    private Integer codAux;

    @Column(name = "nomaux", length = 50)
    private String nomAux;

    @Column(name = "observ", columnDefinition = "TEXT")
    private String observ;

    @Column(name = "feult")
    private LocalDate feult;

    @Column(name = "usuar", length = 8)
    private String usuar;

    /** Relación de solo lectura (sin restricción FK, los datos legados pueden tener referencias huérfanas). */
    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumns(value = {
        @JoinColumn(name = "entidad", referencedColumnName = "entidad", insertable = false, updatable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)),
        @JoinColumn(name = "unidad", referencedColumnName = "unidad", insertable = false, updatable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    }, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private UnidadAdmin unidadAdmin;

    /** Relación de solo lectura (sin restricción FK, los datos legados pueden tener referencias huérfanas). */
    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "codcont", referencedColumnName = "codcont", insertable = false, updatable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private CodCont cuenta;

    public Auxiliar() {
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

    public Integer getCodCont() {
        return codCont;
    }

    public void setCodCont(Integer codCont) {
        this.codCont = codCont;
    }

    public Integer getCodAux() {
        return codAux;
    }

    public void setCodAux(Integer codAux) {
        this.codAux = codAux;
    }

    public String getNomAux() {
        return nomAux;
    }

    public void setNomAux(String nomAux) {
        this.nomAux = nomAux;
    }

    public String getObserv() {
        return observ;
    }

    public void setObserv(String observ) {
        this.observ = observ;
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

    public UnidadAdmin getUnidadAdmin() {
        return unidadAdmin;
    }

    public CodCont getCuenta() {
        return cuenta;
    }

}
