package com.sis414.parciafinal.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

/** Tabla BACKUPS.DBF del VSIAF. Clave: archivo. */
@Entity
@Table(name = "vsiaf_backups")
public class Respaldo {

    /** DBF: DIA */
    private LocalDate dia;

    /** DBF: HORA */
    @Column(length = 8)
    private String hora;

    /** DBF: ARCHIVO */
    @Id
    @Column(length = 12)
    private String archivo;

    /** DBF: OBSERV */
    @Column(columnDefinition = "text")
    private String observ;

    /** DBF: USUAR */
    @Column(length = 8)
    private String usuar;

    /** DBF: FEULT */
    private LocalDate feult;

    public LocalDate getDia() { return dia; }
    public void setDia(LocalDate dia) { this.dia = dia; }
    public String getHora() { return hora; }
    public void setHora(String hora) { this.hora = hora; }
    public String getArchivo() { return archivo; }
    public void setArchivo(String archivo) { this.archivo = archivo; }
    public String getObserv() { return observ; }
    public void setObserv(String observ) { this.observ = observ; }
    public String getUsuar() { return usuar; }
    public void setUsuar(String usuar) { this.usuar = usuar; }
    public LocalDate getFeult() { return feult; }
    public void setFeult(LocalDate feult) { this.feult = feult; }
}
