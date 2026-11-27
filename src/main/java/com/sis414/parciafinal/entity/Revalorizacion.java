package com.sis414.parciafinal.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/** Tabla REVAL.DBF del VSIAF. Clave: entidad, unidad, codigo. */
@Entity
@Table(name = "vsiaf_reval")
@IdClass(Revalorizacion.Key.class)
public class Revalorizacion {

    /** DBF: ENTIDAD */
    @Id
    @Column(length = 4)
    private String entidad;

    /** DBF: UNIDAD */
    @Id
    @Column(length = 5)
    private String unidad;

    /** DBF: CODIGO */
    @Id
    @Column(length = 15)
    private String codigo;

    /** DBF: COSTO */
    @Column(precision = 15, scale = 2)
    private BigDecimal costo;

    /** DBF: DEPACU_ANT */
    @Column(precision = 15, scale = 2)
    private BigDecimal depacuAnt;

    /** DBF: D_REVAL */
    private Integer diaReval;

    /** DBF: DIA */
    private Integer dia;

    /** DBF: M_REVAL */
    private Integer mesReval;

    /** DBF: MES */
    private Integer mes;

    /** DBF: A_REVAL */
    private Integer anoReval;

    /** DBF: ANO */
    private Integer ano;

    /** DBF: VIDAUTIL */
    @Column(precision = 5, scale = 2)
    private BigDecimal vidaUtil;

    /** DBF: RESOLUCION */
    @Column(length = 10)
    private String resolucion;

    /** DBF: OBSERV */
    @Column(columnDefinition = "text")
    private String observ;

    /** DBF: USUAR */
    @Column(length = 8)
    private String usuar;

    /** DBF: FEULT */
    private LocalDate feult;

    /** DBF: DEPACU */
    @Column(precision = 15, scale = 2)
    private BigDecimal depacu;

    /** DBF: ACTUA */
    @Column(precision = 15, scale = 2)
    private BigDecimal actua;

    /** DBF: DEPGESTION */
    @Column(precision = 15, scale = 2)
    private BigDecimal depGestion;

    /** DBF: FEC_MOD */
    private LocalDate fecMod;

    /** DBF: USU_MOD */
    @Column(length = 8)
    private String usuMod;

    public String getEntidad() { return entidad; }
    public void setEntidad(String entidad) { this.entidad = entidad; }
    public String getUnidad() { return unidad; }
    public void setUnidad(String unidad) { this.unidad = unidad; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public BigDecimal getCosto() { return costo; }
    public void setCosto(BigDecimal costo) { this.costo = costo; }
    public BigDecimal getDepacuAnt() { return depacuAnt; }
    public void setDepacuAnt(BigDecimal depacuAnt) { this.depacuAnt = depacuAnt; }
    public Integer getDiaReval() { return diaReval; }
    public void setDiaReval(Integer diaReval) { this.diaReval = diaReval; }
    public Integer getDia() { return dia; }
    public void setDia(Integer dia) { this.dia = dia; }
    public Integer getMesReval() { return mesReval; }
    public void setMesReval(Integer mesReval) { this.mesReval = mesReval; }
    public Integer getMes() { return mes; }
    public void setMes(Integer mes) { this.mes = mes; }
    public Integer getAnoReval() { return anoReval; }
    public void setAnoReval(Integer anoReval) { this.anoReval = anoReval; }
    public Integer getAno() { return ano; }
    public void setAno(Integer ano) { this.ano = ano; }
    public BigDecimal getVidaUtil() { return vidaUtil; }
    public void setVidaUtil(BigDecimal vidaUtil) { this.vidaUtil = vidaUtil; }
    public String getResolucion() { return resolucion; }
    public void setResolucion(String resolucion) { this.resolucion = resolucion; }
    public String getObserv() { return observ; }
    public void setObserv(String observ) { this.observ = observ; }
    public String getUsuar() { return usuar; }
    public void setUsuar(String usuar) { this.usuar = usuar; }
    public LocalDate getFeult() { return feult; }
    public void setFeult(LocalDate feult) { this.feult = feult; }
    public BigDecimal getDepacu() { return depacu; }
    public void setDepacu(BigDecimal depacu) { this.depacu = depacu; }
    public BigDecimal getActua() { return actua; }
    public void setActua(BigDecimal actua) { this.actua = actua; }
    public BigDecimal getDepGestion() { return depGestion; }
    public void setDepGestion(BigDecimal depGestion) { this.depGestion = depGestion; }
    public LocalDate getFecMod() { return fecMod; }
    public void setFecMod(LocalDate fecMod) { this.fecMod = fecMod; }
    public String getUsuMod() { return usuMod; }
    public void setUsuMod(String usuMod) { this.usuMod = usuMod; }

    /** Clave compuesta (entidad, unidad, codigo) */
    public static class Key implements Serializable {
        private String entidad;
        private String unidad;
        private String codigo;
        public Key() {}
        public Key(String entidad, String unidad, String codigo) {
            this.entidad = entidad;
            this.unidad = unidad;
            this.codigo = codigo;
        }
        public String getEntidad() { return entidad; }
        public void setEntidad(String entidad) { this.entidad = entidad; }
        public String getUnidad() { return unidad; }
        public void setUnidad(String unidad) { this.unidad = unidad; }
        public String getCodigo() { return codigo; }
        public void setCodigo(String codigo) { this.codigo = codigo; }
        @Override public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Key)) return false;
            Key x = (Key) o;
            return Objects.equals(entidad, x.entidad) && Objects.equals(unidad, x.unidad) && Objects.equals(codigo, x.codigo);
        }
        @Override public int hashCode() { return Objects.hash(entidad, unidad, codigo); }
    }
}
