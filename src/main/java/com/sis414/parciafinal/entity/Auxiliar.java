package com.sis414.parciafinal.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/** Tabla AUXILIAR.DBF del VSIAF. Clave: entidad, unidad, codCont, codAux. */
@Entity
@Table(name = "vsiaf_auxiliar")
@IdClass(Auxiliar.Key.class)
public class Auxiliar {

    /** DBF: ENTIDAD */
    @Id
    @Column(length = 4)
    private String entidad;

    /** DBF: UNIDAD */
    @Id
    @Column(length = 5)
    private String unidad;

    /** DBF: CODCONT */
    @Id
    private Integer codCont;

    /** DBF: CODAUX */
    @Id
    private Integer codAux;

    /** DBF: NOMAUX */
    @Column(length = 50)
    private String nomAux;

    /** DBF: OBSERV */
    @Column(columnDefinition = "text")
    private String observ;

    /** DBF: FEULT */
    private LocalDate feult;

    /** DBF: USUAR */
    @Column(length = 8)
    private String usuar;

    public String getEntidad() { return entidad; }
    public void setEntidad(String entidad) { this.entidad = entidad; }
    public String getUnidad() { return unidad; }
    public void setUnidad(String unidad) { this.unidad = unidad; }
    public Integer getCodCont() { return codCont; }
    public void setCodCont(Integer codCont) { this.codCont = codCont; }
    public Integer getCodAux() { return codAux; }
    public void setCodAux(Integer codAux) { this.codAux = codAux; }
    public String getNomAux() { return nomAux; }
    public void setNomAux(String nomAux) { this.nomAux = nomAux; }
    public String getObserv() { return observ; }
    public void setObserv(String observ) { this.observ = observ; }
    public LocalDate getFeult() { return feult; }
    public void setFeult(LocalDate feult) { this.feult = feult; }
    public String getUsuar() { return usuar; }
    public void setUsuar(String usuar) { this.usuar = usuar; }

    /** Clave compuesta (entidad, unidad, codCont, codAux) */
    public static class Key implements Serializable {
        private String entidad;
        private String unidad;
        private Integer codCont;
        private Integer codAux;
        public Key() {}
        public Key(String entidad, String unidad, Integer codCont, Integer codAux) {
            this.entidad = entidad;
            this.unidad = unidad;
            this.codCont = codCont;
            this.codAux = codAux;
        }
        public String getEntidad() { return entidad; }
        public void setEntidad(String entidad) { this.entidad = entidad; }
        public String getUnidad() { return unidad; }
        public void setUnidad(String unidad) { this.unidad = unidad; }
        public Integer getCodCont() { return codCont; }
        public void setCodCont(Integer codCont) { this.codCont = codCont; }
        public Integer getCodAux() { return codAux; }
        public void setCodAux(Integer codAux) { this.codAux = codAux; }
        @Override public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Key)) return false;
            Key x = (Key) o;
            return Objects.equals(entidad, x.entidad) && Objects.equals(unidad, x.unidad) && Objects.equals(codCont, x.codCont) && Objects.equals(codAux, x.codAux);
        }
        @Override public int hashCode() { return Objects.hash(entidad, unidad, codCont, codAux); }
    }
}
