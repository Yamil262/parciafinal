package com.sis414.parciafinal.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "org_user")
public class OrgUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 12)
    private String type;

    @Column(length = 12)
    private String idUser;

    @Column(length = 24)
    private String name;

    private Boolean readonly;

    private Integer ckval;

    @Column(columnDefinition = "text")
    private String data;

    private LocalDate updated;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getIdUser() { return idUser; }
    public void setIdUser(String idUser) { this.idUser = idUser; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Boolean getReadonly() { return readonly; }
    public void setReadonly(Boolean readonly) { this.readonly = readonly; }

    public Integer getCkval() { return ckval; }
    public void setCkval(Integer ckval) { this.ckval = ckval; }

    public String getData() { return data; }
    public void setData(String data) { this.data = data; }

    public LocalDate getUpdated() { return updated; }
    public void setUpdated(LocalDate updated) { this.updated = updated; }
}
