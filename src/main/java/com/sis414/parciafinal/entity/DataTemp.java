package com.sis414.parciafinal.entity;

import jakarta.persistence.*;

/** Tabla DATA.DBF del VSIAF. Clave: data. */
@Entity
@Table(name = "vsiaf_data")
public class DataTemp {

    /** DBF: DATA */
    @Id
    @Column(length = 50)
    private String data;

    public String getData() { return data; }
    public void setData(String data) { this.data = data; }
}
