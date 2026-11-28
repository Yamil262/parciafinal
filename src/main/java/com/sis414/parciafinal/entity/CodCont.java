package com.sis414.parciafinal.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import java.math.BigDecimal;
import java.time.LocalDate;

/** Tabla codcont (migrada desde CODCONT.DBF). */
@Entity
@Table(name = "codcont")
public class CodCont {

    @Id
    @Column(name = "codcont", nullable = false)
    private Integer codCont;

    @Column(name = "nombre", length = 50)
    private String nombre;

    @Column(name = "vidautil")
    private Integer vidaUtil;

    @Column(name = "observ", columnDefinition = "TEXT")
    private String observ;

    @Column(name = "depreciar")
    private Boolean depreciar;

    @Column(name = "actualizar")
    private Boolean actualizar;

    @Column(name = "feult")
    private LocalDate feult;

    @Column(name = "usuar", length = 8)
    private String usuar;

    public CodCont() {
    }

    public Integer getCodCont() {
        return codCont;
    }

    public void setCodCont(Integer codCont) {
        this.codCont = codCont;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getVidaUtil() {
        return vidaUtil;
    }

    public void setVidaUtil(Integer vidaUtil) {
        this.vidaUtil = vidaUtil;
    }

    public String getObserv() {
        return observ;
    }

    public void setObserv(String observ) {
        this.observ = observ;
    }

    public Boolean getDepreciar() {
        return depreciar;
    }

    public void setDepreciar(Boolean depreciar) {
        this.depreciar = depreciar;
    }

    public Boolean getActualizar() {
        return actualizar;
    }

    public void setActualizar(Boolean actualizar) {
        this.actualizar = actualizar;
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

}
