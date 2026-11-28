package com.sis414.parciafinal.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import java.math.BigDecimal;
import java.time.LocalDate;

/** Tabla bajas (migrada desde BAJAS.DBF). */
@Entity
@Table(name = "bajas")
public class ActivoBaja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "entidad", length = 4)
    private String entidad;

    @Column(name = "unidad", length = 5)
    private String unidad;

    @Column(name = "codigo", length = 15)
    private String codigo;

    @Column(name = "codbaja")
    private Integer codBaja;

    @Column(name = "codcont")
    private Integer codCont;

    @Column(name = "codaux")
    private Integer codAux;

    @Column(name = "codofic")
    private Integer codOfic;

    @Column(name = "codresp")
    private Integer codResp;

    @Column(name = "costo", precision = 15, scale = 2)
    private BigDecimal costo;

    @Column(name = "depacu_ant", precision = 15, scale = 2)
    private BigDecimal depAcuAnt;

    @Column(name = "descrip", length = 120)
    private String descrip;

    @Column(name = "d_baja")
    private Integer dBaja;

    @Column(name = "dia")
    private Integer dia;

    @Column(name = "m_baja")
    private Integer mBaja;

    @Column(name = "mes")
    private Integer mes;

    @Column(name = "a_baja")
    private Integer aBaja;

    @Column(name = "ano")
    private Integer ano;

    @Column(name = "b_rev")
    private Boolean bRev;

    @Column(name = "vidautil", precision = 5, scale = 2)
    private BigDecimal vidaUtil;

    @Column(name = "resolucion", length = 16)
    private String resolucion;

    @Column(name = "observ", columnDefinition = "TEXT")
    private String observ;

    @Column(name = "motivo", length = 120)
    private String motivo;

    @Column(name = "depacu", precision = 15, scale = 2)
    private BigDecimal depAcu;

    @Column(name = "actua", precision = 15, scale = 2)
    private BigDecimal actua;

    @Column(name = "depgestion", precision = 15, scale = 2)
    private BigDecimal depGestion;

    @Column(name = "cosbajini", precision = 15, scale = 2)
    private BigDecimal cosBajIni;

    @Column(name = "cosdaini", precision = 15, scale = 2)
    private BigDecimal cosDaIni;

    @Column(name = "cosactbaj", precision = 15, scale = 2)
    private BigDecimal cosActBaj;

    @Column(name = "actdabaj", precision = 15, scale = 2)
    private BigDecimal actDaBaj;

    @Column(name = "usuar", length = 8)
    private String usuar;

    @Column(name = "feult")
    private LocalDate feult;

    @Column(name = "fec_mod")
    private LocalDate fecMod;

    @Column(name = "usu_mod", length = 8)
    private String usuMod;

    /** Relación de solo lectura (sin restricción FK, los datos legados pueden tener referencias huérfanas). */
    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "codbaja", referencedColumnName = "codbaja", insertable = false, updatable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private MotivoBaja motivoBaja;

    public ActivoBaja() {
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

    public Integer getCodBaja() {
        return codBaja;
    }

    public void setCodBaja(Integer codBaja) {
        this.codBaja = codBaja;
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

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public Integer getDBaja() {
        return dBaja;
    }

    public void setDBaja(Integer dBaja) {
        this.dBaja = dBaja;
    }

    public Integer getDia() {
        return dia;
    }

    public void setDia(Integer dia) {
        this.dia = dia;
    }

    public Integer getMBaja() {
        return mBaja;
    }

    public void setMBaja(Integer mBaja) {
        this.mBaja = mBaja;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Integer getABaja() {
        return aBaja;
    }

    public void setABaja(Integer aBaja) {
        this.aBaja = aBaja;
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

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
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

    public BigDecimal getCosBajIni() {
        return cosBajIni;
    }

    public void setCosBajIni(BigDecimal cosBajIni) {
        this.cosBajIni = cosBajIni;
    }

    public BigDecimal getCosDaIni() {
        return cosDaIni;
    }

    public void setCosDaIni(BigDecimal cosDaIni) {
        this.cosDaIni = cosDaIni;
    }

    public BigDecimal getCosActBaj() {
        return cosActBaj;
    }

    public void setCosActBaj(BigDecimal cosActBaj) {
        this.cosActBaj = cosActBaj;
    }

    public BigDecimal getActDaBaj() {
        return actDaBaj;
    }

    public void setActDaBaj(BigDecimal actDaBaj) {
        this.actDaBaj = actDaBaj;
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

    public MotivoBaja getMotivoBaja() {
        return motivoBaja;
    }

}
