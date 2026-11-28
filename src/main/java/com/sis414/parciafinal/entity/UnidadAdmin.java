package com.sis414.parciafinal.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import java.math.BigDecimal;
import java.time.LocalDate;

/** Tabla unidadadmin (migrada desde UNIDADADMIN.DBF). */
@Entity
@Table(name = "unidadadmin")
@IdClass(UnidadAdminId.class)
public class UnidadAdmin {

    @Id
    @Column(name = "entidad", length = 4, nullable = false)
    private String entidad;

    @Id
    @Column(name = "unidad", length = 5, nullable = false)
    private String unidad;

    @Column(name = "descrip", length = 120)
    private String descrip;

    @Column(name = "ciudad", length = 20)
    private String ciudad;

    @Column(name = "estadouni")
    private Integer estadoUni;

    public UnidadAdmin() {
    }

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Integer getEstadoUni() {
        return estadoUni;
    }

    public void setEstadoUni(Integer estadoUni) {
        this.estadoUni = estadoUni;
    }

}
