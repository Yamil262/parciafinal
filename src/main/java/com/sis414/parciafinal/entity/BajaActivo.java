package com.sis414.parciafinal.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "baja_activo")
public class BajaActivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 15)
    private String codigo;

    @Column(precision = 15, scale = 2)
    private BigDecimal costo;

    @Column(precision = 15, scale = 2)
    private BigDecimal depacuAnt;

    @Column(length = 120)
    private String descrip;

    private Integer diaBaja;

    private Integer dia;

    private Integer mesBaja;

    private Integer mes;

    private Integer anoBaja;

    private Integer ano;

    private Boolean brev;

    @Column(precision = 5, scale = 2)
    private BigDecimal vidaUtil;

    @Column(length = 16)
    private String resolucion;

    @Column(columnDefinition = "text")
    private String observ;

    @Column(length = 120)
    private String motivo;

    @Column(precision = 15, scale = 2)
    private BigDecimal depacu;

    @Column(precision = 15, scale = 2)
    private BigDecimal actua;

    @Column(precision = 15, scale = 2)
    private BigDecimal depGestion;

    @Column(precision = 15, scale = 2)
    private BigDecimal cosBajIni;

    @Column(precision = 15, scale = 2)
    private BigDecimal cosDaIni;

    @Column(precision = 15, scale = 2)
    private BigDecimal cosActBaj;

    @Column(precision = 15, scale = 2)
    private BigDecimal actDaBaj;

    private LocalDate feult;

    @Column(length = 8)
    private String usuar;

    private LocalDate fecMod;

    @Column(length = 8)
    private String usuMod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unidad_id", nullable = false)
    private UnidadAdmin unidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "motivo_baja_id", nullable = false)
    private MotivoBaja motivoBaja;

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

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

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

    public LocalDate getFeult() { return feult; }
    public void setFeult(LocalDate feult) { this.feult = feult; }

    public String getUsuar() { return usuar; }
    public void setUsuar(String usuar) { this.usuar = usuar; }

    public LocalDate getFecMod() { return fecMod; }
    public void setFecMod(LocalDate fecMod) { this.fecMod = fecMod; }

    public String getUsuMod() { return usuMod; }
    public void setUsuMod(String usuMod) { this.usuMod = usuMod; }

    public UnidadAdmin getUnidad() { return unidad; }
    public void setUnidad(UnidadAdmin unidad) { this.unidad = unidad; }

    public MotivoBaja getMotivoBaja() { return motivoBaja; }
    public void setMotivoBaja(MotivoBaja motivoBaja) { this.motivoBaja = motivoBaja; }

    public Grupo getGrupo() { return grupo; }
    public void setGrupo(Grupo grupo) { this.grupo = grupo; }

    public Auxiliar getAuxiliar() { return auxiliar; }
    public void setAuxiliar(Auxiliar auxiliar) { this.auxiliar = auxiliar; }

    public Oficina getOficina() { return oficina; }
    public void setOficina(Oficina oficina) { this.oficina = oficina; }

    public Responsable getResponsable() { return responsable; }
    public void setResponsable(Responsable responsable) { this.responsable = responsable; }
}
