package com.sis414.parciafinal.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import java.math.BigDecimal;
import java.time.LocalDate;

/** Tabla reval (migrada desde REVAL.DBF). */
@Entity
@Table(name = "reval")
public class Reval {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "entidad", length = 4)
    private String entidad;

    @Column(name = "unidad", length = 5)
    private String unidad;

    @Column(name = "codigo", length = 15)
    private String codigo;

    @Column(name = "costo", precision = 15, scale = 2)
    private BigDecimal costo;

    @Column(name = "depacu_ant", precision = 15, scale = 2)
    private BigDecimal depAcuAnt;

    @Column(name = "d_reval")
    private Integer dReval;

    @Column(name = "dia")
    private Integer dia;

    @Column(name = "m_reval")
    private Integer mReval;

    @Column(name = "mes")
    private Integer mes;

    @Column(name = "a_reval")
    private Integer aReval;

    @Column(name = "ano")
    private Integer ano;

    @Column(name = "vidautil", precision = 5, scale = 2)
    private BigDecimal vidaUtil;

    @Column(name = "resolucion", length = 16)
    private String resolucion;

    @Column(name = "observ", columnDefinition = "TEXT")
    private String observ;

    @Column(name = "usuar", length = 8)
    private String usuar;

    @Column(name = "feult")
    private LocalDate feult;

    @Column(name = "depacu", precision = 15, scale = 2)
    private BigDecimal depAcu;

    @Column(name = "actua", precision = 15, scale = 2)
    private BigDecimal actua;

    @Column(name = "depgestion", precision = 15, scale = 2)
    private BigDecimal depGestion;

    @Column(name = "fec_mod")
    private LocalDate fecMod;

    @Column(name = "usu_mod", length = 8)
    private String usuMod;

    /** Relación de solo lectura (sin restricción FK, los datos legados pueden tener referencias huérfanas). */
    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumns(value = {
        @JoinColumn(name = "entidad", referencedColumnName = "entidad", insertable = false, updatable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)),
        @JoinColumn(name = "unidad", referencedColumnName = "unidad", insertable = false, updatable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)),
        @JoinColumn(name = "codigo", referencedColumnName = "codigo", insertable = false, updatable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    }, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Actual activo;

    public Reval() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    public BigDecimal getDepAcuAnt() {
        return depAcuAnt;
    }

    public void setDepAcuAnt(BigDecimal depAcuAnt) {
        this.depAcuAnt = depAcuAnt;
    }

    public Integer getDReval() {
        return dReval;
    }

    public void setDReval(Integer dReval) {
        this.dReval = dReval;
    }

    public Integer getDia() {
        return dia;
    }

    public void setDia(Integer dia) {
        this.dia = dia;
    }

    public Integer getMReval() {
        return mReval;
    }

    public void setMReval(Integer mReval) {
        this.mReval = mReval;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Integer getAReval() {
        return aReval;
    }

    public void setAReval(Integer aReval) {
        this.aReval = aReval;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public BigDecimal getVidaUtil() {
        return vidaUtil;
    }

    public void setVidaUtil(BigDecimal vidaUtil) {
        this.vidaUtil = vidaUtil;
    }

    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }

    public String getObserv() {
        return observ;
    }

    public void setObserv(String observ) {
        this.observ = observ;
    }

    public String getUsuar() {
        return usuar;
    }

    public void setUsuar(String usuar) {
        this.usuar = usuar;
    }

    public LocalDate getFeult() {
        return feult;
    }

    public void setFeult(LocalDate feult) {
        this.feult = feult;
    }

    public BigDecimal getDepAcu() {
        return depAcu;
    }

    public void setDepAcu(BigDecimal depAcu) {
        this.depAcu = depAcu;
    }

    public BigDecimal getActua() {
        return actua;
    }

    public void setActua(BigDecimal actua) {
        this.actua = actua;
    }

    public BigDecimal getDepGestion() {
        return depGestion;
    }

    public void setDepGestion(BigDecimal depGestion) {
        this.depGestion = depGestion;
    }

    public LocalDate getFecMod() {
        return fecMod;
    }

    public void setFecMod(LocalDate fecMod) {
        this.fecMod = fecMod;
    }

    public String getUsuMod() {
        return usuMod;
    }

    public void setUsuMod(String usuMod) {
        this.usuMod = usuMod;
    }

    public Actual getActivo() {
        return activo;
    }

}
