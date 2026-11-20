package com.sis414.parciafinal.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "activo_fijo")
public class ActivoFijo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 15)
    private String codigo;

    @Column(precision = 6, scale = 2)
    private BigDecimal vidaUtil;

    @Column(length = 150)
    private String descrip;

    @Column(precision = 15, scale = 2)
    private BigDecimal costo;

    @Column(precision = 15, scale = 2)
    private BigDecimal depacu;

    private Integer mes;

    private Integer ano;

    private Boolean brev;

    private Integer dia;

    @Column(columnDefinition = "text")
    private String observ;

    private Integer diaAnt;

    private Integer mesAnt;

    private Integer anoAnt;

    private Integer vutAnt;

    @Column(precision = 15, scale = 2)
    private BigDecimal costoAnt;

    private Boolean bandUfv;

    @Column(length = 15)
    private String codRube;

    @Column(length = 10)
    private String nroConv;

    private LocalDate feult;

    @Column(length = 8)
    private String usuar;

    private Integer apiEstado;

    @Column(length = 15)
    private String codigoSec;

    @Column(length = 12)
    private String banderas;

    private LocalDate fecMod;

    @Column(length = 8)
    private String usuMod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unidad_id", nullable = false)
    private UnidadAdmin unidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grupo_id", nullable = false)
    private Grupo grupo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auxiliar_id", nullable = true)
    private Auxiliar auxiliar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "oficina_id", nullable = false)
    private Oficina oficina;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "responsable_id", nullable = false)
    private Responsable responsable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estado_id", nullable = false)
    private Estado estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organismo_fin_id", nullable = true)
    private OrganismoFin organismoFin;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

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

    public String getCodRube() { return codRube; }
    public void setCodRube(String codRube) { this.codRube = codRube; }

    public String getNroConv() { return nroConv; }
    public void setNroConv(String nroConv) { this.nroConv = nroConv; }

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

    public UnidadAdmin getUnidad() { return unidad; }
    public void setUnidad(UnidadAdmin unidad) { this.unidad = unidad; }

    public Grupo getGrupo() { return grupo; }
    public void setGrupo(Grupo grupo) { this.grupo = grupo; }

    public Auxiliar getAuxiliar() { return auxiliar; }
    public void setAuxiliar(Auxiliar auxiliar) { this.auxiliar = auxiliar; }

    public Oficina getOficina() { return oficina; }
    public void setOficina(Oficina oficina) { this.oficina = oficina; }

    public Responsable getResponsable() { return responsable; }
    public void setResponsable(Responsable responsable) { this.responsable = responsable; }

    public Estado getEstado() { return estado; }
    public void setEstado(Estado estado) { this.estado = estado; }

    public OrganismoFin getOrganismoFin() { return organismoFin; }
    public void setOrganismoFin(OrganismoFin organismoFin) { this.organismoFin = organismoFin; }
}
