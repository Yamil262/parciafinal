package com.sis414.parciafinal.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/** Tabla ACTUAL.DBF del VSIAF. Clave: entidad, unidad, codigo. */
@Entity
@Table(name = "vsiaf_actual")
@IdClass(ActivoFijo.Key.class)
public class ActivoFijo {

    /** DBF: UNIDAD */
    @Id
    @Column(length = 5)
    private String unidad;

    /** DBF: ENTIDAD */
    @Id
    @Column(length = 4)
    private String entidad;

    /** DBF: CODIGO */
    @Id
    @Column(length = 15)
    private String codigo;

    /** DBF: CODCONT */
    private Integer codCont;

    /** DBF: CODAUX */
    private Integer codAux;

    /** DBF: VIDAUTIL */
    @Column(precision = 6, scale = 2)
    private BigDecimal vidaUtil;

    /** DBF: DESCRIP */
    @Column(length = 150)
    private String descrip;

    /** DBF: COSTO */
    @Column(precision = 15, scale = 2)
    private BigDecimal costo;

    /** DBF: DEPACU */
    @Column(precision = 15, scale = 2)
    private BigDecimal depacu;

    /** DBF: MES */
    private Integer mes;

    /** DBF: ANO */
    private Integer ano;

    /** DBF: B_REV */
    private Boolean brev;

    /** DBF: DIA */
    private Integer dia;

    /** DBF: CODOFIC */
    private Integer codOfic;

    /** DBF: CODRESP */
    private Integer codResp;

    /** DBF: OBSERV */
    @Column(columnDefinition = "text")
    private String observ;

    /** DBF: DIA_ANT */
    private Integer diaAnt;

    /** DBF: MES_ANT */
    private Integer mesAnt;

    /** DBF: ANO_ANT */
    private Integer anoAnt;

    /** DBF: VUT_ANT */
    private Integer vutAnt;

    /** DBF: COSTO_ANT */
    @Column(precision = 15, scale = 2)
    private BigDecimal costoAnt;

    /** DBF: BAND_UFV */
    private Boolean bandUfv;

    /** DBF: CODESTADO */
    private Integer codEstado;

    /** DBF: COD_RUBE */
    @Column(length = 15)
    private String codRube;

    /** DBF: NRO_CONV */
    @Column(length = 10)
    private String nroConv;

    /** DBF: ORG_FIN */
    @Column(length = 10)
    private String orgFin;

    /** DBF: FEULT */
    private LocalDate feult;

    /** DBF: USUAR */
    @Column(length = 8)
    private String usuar;

    /** DBF: API_ESTADO */
    private Integer apiEstado;

    /** DBF: CODIGOSEC */
    @Column(length = 15)
    private String codigoSec;

    /** DBF: BANDERAS */
    @Column(length = 12)
    private String banderas;

    /** DBF: FEC_MOD */
    private LocalDate fecMod;

    /** DBF: USU_MOD */
    @Column(length = 8)
    private String usuMod;

    public String getUnidad() { return unidad; }
    public void setUnidad(String unidad) { this.unidad = unidad; }
    public String getEntidad() { return entidad; }
    public void setEntidad(String entidad) { this.entidad = entidad; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public Integer getCodCont() { return codCont; }
    public void setCodCont(Integer codCont) { this.codCont = codCont; }
    public Integer getCodAux() { return codAux; }
    public void setCodAux(Integer codAux) { this.codAux = codAux; }
    public BigDecimal getVidaUtil() { return vidaUtil; }
    public void setVidaUtil(BigDecimal vidaUtil) { this.vidaUtil = vidaUtil; }
    public String getDescrip() { return descrip; }
    public void setDescrip(String descrip) { this.descrip = descrip; }
    public BigDecimal getCosto() { return costo; }
    public void setCosto(BigDecimal costo) { this.costo = costo; }
    public BigDecimal getDepacu() { return depacu; }
    public void setDepacu(BigDecimal depacu) { this.depacu = depacu; }
    public Integer getMes() { return mes; }
    public void setMes(Integer mes) { this.mes = mes; }
    public Integer getAno() { return ano; }
    public void setAno(Integer ano) { this.ano = ano; }
    public Boolean getBrev() { return brev; }
    public void setBrev(Boolean brev) { this.brev = brev; }
    public Integer getDia() { return dia; }
    public void setDia(Integer dia) { this.dia = dia; }
    public Integer getCodOfic() { return codOfic; }
    public void setCodOfic(Integer codOfic) { this.codOfic = codOfic; }
    public Integer getCodResp() { return codResp; }
    public void setCodResp(Integer codResp) { this.codResp = codResp; }
    public String getObserv() { return observ; }
    public void setObserv(String observ) { this.observ = observ; }
    public Integer getDiaAnt() { return diaAnt; }
    public void setDiaAnt(Integer diaAnt) { this.diaAnt = diaAnt; }
    public Integer getMesAnt() { return mesAnt; }
    public void setMesAnt(Integer mesAnt) { this.mesAnt = mesAnt; }
    public Integer getAnoAnt() { return anoAnt; }
    public void setAnoAnt(Integer anoAnt) { this.anoAnt = anoAnt; }
    public Integer getVutAnt() { return vutAnt; }
    public void setVutAnt(Integer vutAnt) { this.vutAnt = vutAnt; }
    public BigDecimal getCostoAnt() { return costoAnt; }
    public void setCostoAnt(BigDecimal costoAnt) { this.costoAnt = costoAnt; }
    public Boolean getBandUfv() { return bandUfv; }
    public void setBandUfv(Boolean bandUfv) { this.bandUfv = bandUfv; }
    public Integer getCodEstado() { return codEstado; }
    public void setCodEstado(Integer codEstado) { this.codEstado = codEstado; }
    public String getCodRube() { return codRube; }
    public void setCodRube(String codRube) { this.codRube = codRube; }
    public String getNroConv() { return nroConv; }
    public void setNroConv(String nroConv) { this.nroConv = nroConv; }
    public String getOrgFin() { return orgFin; }
    public void setOrgFin(String orgFin) { this.orgFin = orgFin; }
    public LocalDate getFeult() { return feult; }
    public void setFeult(LocalDate feult) { this.feult = feult; }
    public String getUsuar() { return usuar; }
    public void setUsuar(String usuar) { this.usuar = usuar; }
    public Integer getApiEstado() { return apiEstado; }
    public void setApiEstado(Integer apiEstado) { this.apiEstado = apiEstado; }
    public String getCodigoSec() { return codigoSec; }
    public void setCodigoSec(String codigoSec) { this.codigoSec = codigoSec; }
    public String getBanderas() { return banderas; }
    public void setBanderas(String banderas) { this.banderas = banderas; }
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
