package com.sis414.parciafinal.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "data_temp")
public class DataTemp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String data;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getData() { return data; }
    public void setData(String data) { this.data = data; }
}
