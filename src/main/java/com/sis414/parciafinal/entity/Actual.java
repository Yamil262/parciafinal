package com.sis414.parciafinal.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import java.math.BigDecimal;
import java.time.LocalDate;

/** Tabla actual (migrada desde ACTUAL.DBF). */
@Entity
@Table(name = "actual")
@IdClass(ActualId.class)
public class Actual {

    @Id
    @Column(name = "unidad", length = 5, nullable = false)
    private String unidad;

    @Id
    @Column(name = "entidad", length = 4, nullable = false)
    private String entidad;

    @Id
    @Column(name = "codigo", length = 15, nullable = false)
    private String codigo;

    @Column(name = "codcont")
    private Integer codCont;

    @Column(name = "codaux")
    private Integer codAux;

    @Column(name = "vidautil", precision = 6, scale = 2)
    private BigDecimal vidaUtil;

    @Column(name = "descrip", length = 150)
    private String descrip;

    @Column(name = "costo", precision = 15, scale = 2)
    private BigDecimal costo;

    @Column(name = "depacu", precision = 15, scale = 2)
    private BigDecimal depAcu;

    @Column(name = "mes")
    private Integer mes;

    @Column(name = "ano")
    private Integer ano;

    @Column(name = "b_rev")
    private Boolean bRev;

    @Column(name = "dia")
    private Integer dia;

    @Column(name = "codofic")
    private Integer codOfic;

    @Column(name = "codresp")
    private Integer codResp;

    @Column(name = "observ", columnDefinition = "TEXT")
    private String observ;

    @Column(name = "dia_ant")
    private Integer diaAnt;

    @Column(name = "mes_ant")
    private Integer mesAnt;

    @Column(name = "ano_ant")
    private Integer anoAnt;

    @Column(name = "vut_ant")
    private Integer vutAnt;

    @Column(name = "costo_ant", precision = 15, scale = 2)
    private BigDecimal costoAnt;

    @Column(name = "band_ufv")
    private Boolean bandUfv;

    @Column(name = "codestado")
    private Integer codEstado;

    @Column(name = "cod_rube", length = 15)
    private String codRube;

    @Column(name = "nro_conv", length = 10)
    private String nroConv;

    @Column(name = "org_fin", length = 3)
    private String orgFin;

    @Column(name = "feult")
    private LocalDate feult;

    @Column(name = "usuar", length = 8)
    private String usuar;

    @Column(name = "api_estado")
    private Integer apiEstado;

    @Column(name = "codigosec", length = 15)
    private String codigoSec;

    @Column(name = "banderas", length = 12)
    private String banderas;

    @Column(name = "fec_mod")
    private LocalDate fecMod;

    @Column(name = "usu_mod", length = 8)
    private String usuMod;

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

    /** Relación de solo lectura (sin restricción FK, los datos legados pueden tener referencias huérfanas). */
    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumns(value = {
        @JoinColumn(name = "entidad", referencedColumnName = "entidad", insertable = false, updatable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)),
        @JoinColumn(name = "unidad", referencedColumnName = "unidad", insertable = false, updatable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)),
        @JoinColumn(name = "codcont", referencedColumnName = "codcont", insertable = false, updatable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)),
        @JoinColumn(name = "codaux", referencedColumnName = "codaux", insertable = false, updatable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    }, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Auxiliar auxiliar;

    /** Relación de solo lectura (sin restricción FK, los datos legados pueden tener referencias huérfanas). */
    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumns(value = {
        @JoinColumn(name = "entidad", referencedColumnName = "entidad", insertable = false, updatable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)),
        @JoinColumn(name = "unidad", referencedColumnName = "unidad", insertable = false, updatable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)),
        @JoinColumn(name = "codofic", referencedColumnName = "codofic", insertable = false, updatable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    }, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Oficina oficina;

    /** Relación de solo lectura (sin restricción FK, los datos legados pueden tener referencias huérfanas). */
    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumns(value = {
        @JoinColumn(name = "entidad", referencedColumnName = "entidad", insertable = false, updatable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)),
        @JoinColumn(name = "unidad", referencedColumnName = "unidad", insertable = false, updatable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)),
        @JoinColumn(name = "codofic", referencedColumnName = "codofic", insertable = false, updatable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)),
        @JoinColumn(name = "codresp", referencedColumnName = "codresp", insertable = false, updatable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    }, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Resp responsable;

    /** Relación de solo lectura (sin restricción FK, los datos legados pueden tener referencias huérfanas). */
    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "codestado", referencedColumnName = "codestado", insertable = false, updatable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Estado estadoBien;

    public Actual() {
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public BigDecimal getVidaUtil() {
        return vidaUtil;
    }

    public void setVidaUtil(BigDecimal vidaUtil) {
        this.vidaUtil = vidaUtil;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    public BigDecimal getDepAcu() {
        return depAcu;
    }

    public void setDepAcu(BigDecimal depAcu) {
        this.depAcu = depAcu;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Boolean getBRev() {
        return bRev;
    }

    public void setBRev(Boolean bRev) {
        this.bRev = bRev;
    }

    public Integer getDia() {
        return dia;
    }

    public void setDia(Integer dia) {
        this.dia = dia;
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

    public String getObserv() {
        return observ;
    }

    public void setObserv(String observ) {
        this.observ = observ;
    }

    public Integer getDiaAnt() {
        return diaAnt;
    }

    public void setDiaAnt(Integer diaAnt) {
        this.diaAnt = diaAnt;
    }

    public Integer getMesAnt() {
        return mesAnt;
    }

    public void setMesAnt(Integer mesAnt) {
        this.mesAnt = mesAnt;
    }

    public Integer getAnoAnt() {
        return anoAnt;
    }

    public void setAnoAnt(Integer anoAnt) {
        this.anoAnt = anoAnt;
    }

    public Integer getVutAnt() {
        return vutAnt;
    }

    public void setVutAnt(Integer vutAnt) {
        this.vutAnt = vutAnt;
    }

    public BigDecimal getCostoAnt() {
        return costoAnt;
    }

    public void setCostoAnt(BigDecimal costoAnt) {
        this.costoAnt = costoAnt;
    }

    public Boolean getBandUfv() {
        return bandUfv;
    }

    public void setBandUfv(Boolean bandUfv) {
        this.bandUfv = bandUfv;
    }

    public Integer getCodEstado() {
        return codEstado;
    }

    public void setCodEstado(Integer codEstado) {
        this.codEstado = codEstado;
    }

    public String getCodRube() {
        return codRube;
    }

    public void setCodRube(String codRube) {
        this.codRube = codRube;
    }

    public String getNroConv() {
        return nroConv;
    }

    public void setNroConv(String nroConv) {
        this.nroConv = nroConv;
    }

    public String getOrgFin() {
        return orgFin;
    }

    public void setOrgFin(String orgFin) {
        this.orgFin = orgFin;
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

    public String getCodigoSec() {
        return codigoSec;
    }

    public void setCodigoSec(String codigoSec) {
        this.codigoSec = codigoSec;
    }

    public String getBanderas() {
        return banderas;
    }

    public void setBanderas(String banderas) {
        this.banderas = banderas;
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

    public UnidadAdmin getUnidadAdmin() {
        return unidadAdmin;
    }

    public CodCont getCuenta() {
        return cuenta;
    }

    public Auxiliar getAuxiliar() {
        return auxiliar;
    }

    public Oficina getOficina() {
        return oficina;
    }

    public Resp getResponsable() {
        return responsable;
    }

    public Estado getEstadoBien() {
        return estadoBien;
    }

}
