package com.sis414.parciafinal.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "respaldo")
public class Respaldo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dia;

    @Column(length = 8)
    private String hora;

    @Column(length = 12)
    private String archivo;

    @Column(columnDefinition = "text")
    private String observ;

    @Column(length = 8)
    private String usuar;

    private LocalDate feult;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

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
