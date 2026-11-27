package com.sis414.parciafinal.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/** Tabla OFICINA.DBF del VSIAF. Clave: entidad, unidad, codOfic. */
@Entity
@Table(name = "vsiaf_oficina")
@IdClass(Oficina.Key.class)
public class Oficina {

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

    /** DBF: NOMOFIC */
    @Column(length = 65)
    private String nomOfic;

    /** DBF: OBSERV */
    @Column(columnDefinition = "text")
    private String observ;

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
    public String getNomOfic() { return nomOfic; }
    public void setNomOfic(String nomOfic) { this.nomOfic = nomOfic; }
    public String getObserv() { return observ; }
    public void setObserv(String observ) { this.observ = observ; }
    public LocalDate getFeult() { return feult; }
    public void setFeult(LocalDate feult) { this.feult = feult; }
    public String getUsuar() { return usuar; }
    public void setUsuar(String usuar) { this.usuar = usuar; }
    public Integer getApiEstado() { return apiEstado; }
    public void setApiEstado(Integer apiEstado) { this.apiEstado = apiEstado; }

    /** Clave compuesta (entidad, unidad, codOfic) */
    public static class Key implements Serializable {
        private String entidad;
        private String unidad;
        private Integer codOfic;
        public Key() {}
        public Key(String entidad, String unidad, Integer codOfic) {
            this.entidad = entidad;
            this.unidad = unidad;
            this.codOfic = codOfic;
        }
        public String getEntidad() { return entidad; }
        public void setEntidad(String entidad) { this.entidad = entidad; }
        public String getUnidad() { return unidad; }
        public void setUnidad(String unidad) { this.unidad = unidad; }
        public Integer getCodOfic() { return codOfic; }
        public void setCodOfic(Integer codOfic) { this.codOfic = codOfic; }
        @Override public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Key)) return false;
            Key x = (Key) o;
            return Objects.equals(entidad, x.entidad) && Objects.equals(unidad, x.unidad) && Objects.equals(codOfic, x.codOfic);
        }
        @Override public int hashCode() { return Objects.hash(entidad, unidad, codOfic); }
    }
}
