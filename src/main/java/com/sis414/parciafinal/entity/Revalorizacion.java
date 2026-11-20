package com.sis414.parciafinal.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "revalorizacion")
public class Revalorizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 15)
    private String codigo;

    @Column(precision = 15, scale = 2)
    private BigDecimal costo;

    @Column(precision = 15, scale = 2)
    private BigDecimal depacuAnt;

    private Integer diaReval;

    private Integer dia;

    private Integer mesReval;

    private Integer mes;

    private Integer anoReval;

    private Integer ano;

    @Column(precision = 5, scale = 2)
    private BigDecimal vidaUtil;

    @Column(length = 16)
    private String resolucion;

    @Column(columnDefinition = "text")
    private String observ;

    @Column(length = 8)
    private String usuar;

    private LocalDate feult;

    @Column(precision = 15, scale = 2)
    private BigDecimal depacu;

    @Column(precision = 15, scale = 2)
    private BigDecimal actua;

    @Column(precision = 15, scale = 2)
    private BigDecimal depGestion;

    private LocalDate fecMod;

    @Column(length = 8)
    private String usuMod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unidad_id", nullable = false)
    private UnidadAdmin unidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activo_id", nullable = true)
    private ActivoFijo activo;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

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

    public UnidadAdmin getUnidad() { return unidad; }
    public void setUnidad(UnidadAdmin unidad) { this.unidad = unidad; }

    public ActivoFijo getActivo() { return activo; }
    public void setActivo(ActivoFijo activo) { this.activo = activo; }
}
