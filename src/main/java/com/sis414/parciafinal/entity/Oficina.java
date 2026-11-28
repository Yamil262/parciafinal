package com.sis414.parciafinal.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import java.math.BigDecimal;
import java.time.LocalDate;

/** Tabla oficina (migrada desde OFICINA.DBF). */
@Entity
@Table(name = "oficina")
@IdClass(OficinaId.class)
public class Oficina {

    @Id
    @Column(name = "entidad", length = 4, nullable = false)
    private String entidad;

    @Id
    @Column(name = "unidad", length = 5, nullable = false)
    private String unidad;

    @Id
    @Column(name = "codofic", nullable = false)
    private Integer codOfic;

    @Column(name = "nomofic", length = 65)
    private String nomOfic;

    @Column(name = "observ", columnDefinition = "TEXT")
    private String observ;

    @Column(name = "feult")
    private LocalDate feult;

    @Column(name = "usuar", length = 8)
    private String usuar;

    @Column(name = "api_estado")
    private Integer apiEstado;

    /** Relación de solo lectura (sin restricción FK, los datos legados pueden tener referencias huérfanas). */
    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumns(value = {
        @JoinColumn(name = "entidad", referencedColumnName = "entidad", insertable = false, updatable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)),
        @JoinColumn(name = "unidad", referencedColumnName = "unidad", insertable = false, updatable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    }, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private UnidadAdmin unidadAdmin;

    public Oficina() {
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

    public String getNomOfic() {
        return nomOfic;
    }

    public void setNomOfic(String nomOfic) {
        this.nomOfic = nomOfic;
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

    public Integer getApiEstado() {
        return apiEstado;
    }

    public void setApiEstado(Integer apiEstado) {
        this.apiEstado = apiEstado;
    }

    public UnidadAdmin getUnidadAdmin() {
        return unidadAdmin;
    }

}
