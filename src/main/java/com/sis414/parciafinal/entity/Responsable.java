package com.sis414.parciafinal.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/** Tabla RESP.DBF del VSIAF. Clave: entidad, unidad, codOfic, codResp. */
@Entity
@Table(name = "vsiaf_resp")
@IdClass(Responsable.Key.class)
public class Responsable {

    /** DBF: ENTIDAD */
    @Id
    @Column(length = 4)
    private String entidad;

    /** DBF: UNIDAD */
    @Id
    @Column(length = 5)
    private String unidad;

    /** DBF: CODOFIC */
    @Id
    private Integer codOfic;

    /** DBF: CODRESP */
    @Id
    private Integer codResp;

    /** DBF: NOMRESP */
    @Column(length = 35)
    private String nomResp;

    /** DBF: CARGO */
    @Column(length = 35)
    private String cargo;

    /** DBF: OBSERV */
    @Column(columnDefinition = "text")
    private String observ;

    /** DBF: CI */
    @Column(length = 10)
    private String ci;

    /** DBF: FEULT */
    private LocalDate feult;

    /** DBF: USUAR */
    @Column(length = 8)
    private String usuar;

    /** DBF: extensión, no está en el DBF */
    private Integer apiEstado;

    public String getEntidad() { return entidad; }
    public void setEntidad(String entidad) { this.entidad = entidad; }
    public String getUnidad() { return unidad; }
    public void setUnidad(String unidad) { this.unidad = unidad; }
    public Integer getCodOfic() { return codOfic; }
    public void setCodOfic(Integer codOfic) { this.codOfic = codOfic; }
    public Integer getCodResp() { return codResp; }
    public void setCodResp(Integer codResp) { this.codResp = codResp; }
    public String getNomResp() { return nomResp; }
    public void setNomResp(String nomResp) { this.nomResp = nomResp; }
    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }
    public String getObserv() { return observ; }
    public void setObserv(String observ) { this.observ = observ; }
    public String getCi() { return ci; }
    public void setCi(String ci) { this.ci = ci; }
    public LocalDate getFeult() { return feult; }
    public void setFeult(LocalDate feult) { this.feult = feult; }
    public String getUsuar() { return usuar; }
    public void setUsuar(String usuar) { this.usuar = usuar; }
    public Integer getApiEstado() { return apiEstado; }
    public void setApiEstado(Integer apiEstado) { this.apiEstado = apiEstado; }

    /** Clave compuesta (entidad, unidad, codOfic, codResp) */
    public static class Key implements Serializable {
        private String entidad;
        private String unidad;
        private Integer codOfic;
        private Integer codResp;
        public Key() {}
        public Key(String entidad, String unidad, Integer codOfic, Integer codResp) {
            this.entidad = entidad;
            this.unidad = unidad;
            this.codOfic = codOfic;
            this.codResp = codResp;
        }
        public String getEntidad() { return entidad; }
        public void setEntidad(String entidad) { this.entidad = entidad; }
        public String getUnidad() { return unidad; }
        public void setUnidad(String unidad) { this.unidad = unidad; }
        public Integer getCodOfic() { return codOfic; }
        public void setCodOfic(Integer codOfic) { this.codOfic = codOfic; }
        public Integer getCodResp() { return codResp; }
        public void setCodResp(Integer codResp) { this.codResp = codResp; }
        @Override public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Key)) return false;
            Key x = (Key) o;
            return Objects.equals(entidad, x.entidad) && Objects.equals(unidad, x.unidad) && Objects.equals(codOfic, x.codOfic) && Objects.equals(codResp, x.codResp);
        }
        @Override public int hashCode() { return Objects.hash(entidad, unidad, codOfic, codResp); }
    }
}
