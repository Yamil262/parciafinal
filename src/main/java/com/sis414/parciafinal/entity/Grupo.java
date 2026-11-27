package com.sis414.parciafinal.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

/** Tabla CODCONT.DBF del VSIAF. Clave: codCont. */
@Entity
@Table(name = "vsiaf_codcont")
public class Grupo {

    /** DBF: CODCONT */
    @Id
    private Integer codCont;

    /** DBF: NOMBRE */
    @Column(length = 50)
    private String nombre;

    /** DBF: VIDAUTIL */
    private Integer vidaUtil;

    /** DBF: OBSERV */
    @Column(columnDefinition = "text")
    private String observ;

    /** DBF: DEPRECIAR */
    private Boolean depreciar;

    /** DBF: ACTUALIZAR */
    private Boolean actualizar;

    /** DBF: FEULT */
    private LocalDate feult;

    /** DBF: USUAR */
    @Column(length = 8)
    private String usuar;

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
