package com.sis414.parciafinal.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/** Tabla BAJAS.DBF del VSIAF. Clave: entidad, unidad, codigo. */
@Entity
@Table(name = "vsiaf_bajas")
@IdClass(BajaActivo.Key.class)
public class BajaActivo {

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

    /** DBF: CODBAJA */
    private Integer codBaja;

    /** DBF: CODCONT */
    private Integer codCont;

    /** DBF: CODAUX */
    private Integer codAux;

    /** DBF: CODOFIC */
    private Integer codOfic;

    /** DBF: CODRESP */
    private Integer codResp;

    /** DBF: COSTO */
    @Column(precision = 15, scale = 2)
    private BigDecimal costo;

    /** DBF: DEPACU_ANT */
    @Column(precision = 15, scale = 2)
    private BigDecimal depacuAnt;

    /** DBF: DESCRIP */
    @Column(length = 120)
    private String descrip;

    /** DBF: D_BAJA */
    private Integer diaBaja;

    /** DBF: DIA */
    private Integer dia;

    /** DBF: M_BAJA */
    private Integer mesBaja;

    /** DBF: MES */
    private Integer mes;

    /** DBF: A_BAJA */
    private Integer anoBaja;

    /** DBF: ANO */
    private Integer ano;

    /** DBF: B_REV */
    private Boolean brev;

    /** DBF: VIDAUTIL */
    @Column(precision = 5, scale = 2)
    private BigDecimal vidaUtil;

    /** DBF: RESOLUCION */
    @Column(length = 10)
    private String resolucion;

    /** DBF: OBSERV */
    @Column(columnDefinition = "text")
    private String observ;

    /** DBF: MOTIVO */
    @Column(length = 120)
    private String motivo;

    /** DBF: DEPACU */
    @Column(precision = 15, scale = 2)
    private BigDecimal depacu;

    /** DBF: ACTUA */
    @Column(precision = 15, scale = 2)
    private BigDecimal actua;

    /** DBF: DEPGESTION */
    @Column(precision = 15, scale = 2)
    private BigDecimal depGestion;

    /** DBF: COSBAJINI */
    @Column(precision = 15, scale = 2)
    private BigDecimal cosBajIni;

    /** DBF: COSDAINI */
    @Column(precision = 15, scale = 2)
    private BigDecimal cosDaIni;

    /** DBF: COSACTBAJ */
    @Column(precision = 15, scale = 2)
    private BigDecimal cosActBaj;

    /** DBF: ACTDABAJ */
    @Column(precision = 15, scale = 2)
    private BigDecimal actDaBaj;

    /** DBF: USUAR */
    @Column(length = 8)
    private String usuar;

    /** DBF: FEULT */
    private LocalDate feult;

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
    public Integer getCodBaja() { return codBaja; }
    public void setCodBaja(Integer codBaja) { this.codBaja = codBaja; }
    public Integer getCodCont() { return codCont; }
    public void setCodCont(Integer codCont) { this.codCont = codCont; }
    public Integer getCodAux() { return codAux; }
    public void setCodAux(Integer codAux) { this.codAux = codAux; }
    public Integer getCodOfic() { return codOfic; }
    public void setCodOfic(Integer codOfic) { this.codOfic = codOfic; }
    public Integer getCodResp() { return codResp; }
    public void setCodResp(Integer codResp) { this.codResp = codResp; }
    public BigDecimal getCosto() { return costo; }
    public void setCosto(BigDecimal costo) { this.costo = costo; }
    public BigDecimal getDepacuAnt() { return depacuAnt; }
    public void setDepacuAnt(BigDecimal depacuAnt) { this.depacuAnt = depacuAnt; }
    public String getDescrip() { return descrip; }
    public void setDescrip(String descrip) { this.descrip = descrip; }
    public Integer getDiaBaja() { return diaBaja; }
    public void setDiaBaja(Integer diaBaja) { this.diaBaja = diaBaja; }
    public Integer getDia() { return dia; }
    public void setDia(Integer dia) { this.dia = dia; }
    public Integer getMesBaja() { return mesBaja; }
    public void setMesBaja(Integer mesBaja) { this.mesBaja = mesBaja; }
    public Integer getMes() { return mes; }
    public void setMes(Integer mes) { this.mes = mes; }
    public Integer getAnoBaja() { return anoBaja; }
    public void setAnoBaja(Integer anoBaja) { this.anoBaja = anoBaja; }
    public Integer getAno() { return ano; }
    public void setAno(Integer ano) { this.ano = ano; }
    public Boolean getBrev() { return brev; }
    public void setBrev(Boolean brev) { this.brev = brev; }
    public BigDecimal getVidaUtil() { return vidaUtil; }
    public void setVidaUtil(BigDecimal vidaUtil) { this.vidaUtil = vidaUtil; }
    public String getResolucion() { return resolucion; }
    public void setResolucion(String resolucion) { this.resolucion = resolucion; }
    public String getObserv() { return observ; }
    public void setObserv(String observ) { this.observ = observ; }
    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }
    public BigDecimal getDepacu() { return depacu; }
    public void setDepacu(BigDecimal depacu) { this.depacu = depacu; }
    public BigDecimal getActua() { return actua; }
    public void setActua(BigDecimal actua) { this.actua = actua; }
    public BigDecimal getDepGestion() { return depGestion; }
    public void setDepGestion(BigDecimal depGestion) { this.depGestion = depGestion; }
    public BigDecimal getCosBajIni() { return cosBajIni; }
    public void setCosBajIni(BigDecimal cosBajIni) { this.cosBajIni = cosBajIni; }
    public BigDecimal getCosDaIni() { return cosDaIni; }
    public void setCosDaIni(BigDecimal cosDaIni) { this.cosDaIni = cosDaIni; }
    public BigDecimal getCosActBaj() { return cosActBaj; }
    public void setCosActBaj(BigDecimal cosActBaj) { this.cosActBaj = cosActBaj; }
    public BigDecimal getActDaBaj() { return actDaBaj; }
    public void setActDaBaj(BigDecimal actDaBaj) { this.actDaBaj = actDaBaj; }
    public String getUsuar() { return usuar; }
    public void setUsuar(String usuar) { this.usuar = usuar; }
    public LocalDate getFeult() { return feult; }
    public void setFeult(LocalDate feult) { this.feult = feult; }
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
