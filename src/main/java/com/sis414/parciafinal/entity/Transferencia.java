package com.sis414.parciafinal.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/** Tabla TRASFE.DBF del VSIAF. Clave: entidad, unidad, codigo. */
@Entity
@Table(name = "vsiaf_trasfe")
@IdClass(Transferencia.Key.class)
public class Transferencia {

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

    /** DBF: CODOFIC */
    private Integer codOfic;

    /** DBF: CODRESP */
    private Integer codResp;

    /** DBF: FEULT */
    private LocalDate feult;

    /** DBF: USUAR */
    @Column(length = 8)
    private String usuar;

    /** DBF: UNIDADINI */
    @Column(length = 5)
    private String unidadIni;

    public String getEntidad() { return entidad; }
    public void setEntidad(String entidad) { this.entidad = entidad; }
    public String getUnidad() { return unidad; }
    public void setUnidad(String unidad) { this.unidad = unidad; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public Integer getCodOfic() { return codOfic; }
    public void setCodOfic(Integer codOfic) { this.codOfic = codOfic; }
    public Integer getCodResp() { return codResp; }
    public void setCodResp(Integer codResp) { this.codResp = codResp; }
    public LocalDate getFeult() { return feult; }
    public void setFeult(LocalDate feult) { this.feult = feult; }
    public String getUsuar() { return usuar; }
    public void setUsuar(String usuar) { this.usuar = usuar; }
    public String getUnidadIni() { return unidadIni; }
    public void setUnidadIni(String unidadIni) { this.unidadIni = unidadIni; }

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
