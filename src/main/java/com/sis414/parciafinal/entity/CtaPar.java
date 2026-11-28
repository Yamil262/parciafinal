package com.sis414.parciafinal.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import java.math.BigDecimal;
import java.time.LocalDate;

/** Tabla cta_par (migrada desde CTA_PAR.DBF). */
@Entity
@Table(name = "cta_par")
@IdClass(CtaParId.class)
public class CtaPar {

    @Id
    @Column(name = "codcont", nullable = false)
    private Integer codCont;

    @Id
    @Column(name = "partida", length = 5, nullable = false)
    private String partida;

    @Id
    @Column(name = "gestion", nullable = false)
    private Integer gestion;

    /** Relación de solo lectura (sin restricción FK, los datos legados pueden tener referencias huérfanas). */
    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "codcont", referencedColumnName = "codcont", insertable = false, updatable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private CodCont cuenta;

    /** Relación de solo lectura (sin restricción FK, los datos legados pueden tener referencias huérfanas). */
    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumns(value = {
        @JoinColumn(name = "gestion", referencedColumnName = "gestion", insertable = false, updatable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)),
        @JoinColumn(name = "partida", referencedColumnName = "partida", insertable = false, updatable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    }, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private ObjGasto objetoGasto;

    public CtaPar() {
    }

    public Integer getCodCont() {
        return codCont;
    }

    public void setCodCont(Integer codCont) {
        this.codCont = codCont;
    }

    public String getPartida() {
        return partida;
    }

    public void setPartida(String partida) {
        this.partida = partida;
    }

    public Integer getGestion() {
        return gestion;
    }

    public void setGestion(Integer gestion) {
        this.gestion = gestion;
    }

    public CodCont getCuenta() {
        return cuenta;
    }

    public ObjGasto getObjetoGasto() {
        return objetoGasto;
    }

}
