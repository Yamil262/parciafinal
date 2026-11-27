package com.sis414.parciafinal.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/** Tabla ORGUSER.DBF del VSIAF. Clave: type, idUser, name. */
@Entity
@Table(name = "vsiaf_orguser")
@IdClass(OrgUser.Key.class)
public class OrgUser {

    /** DBF: TYPE */
    @Id
    @Column(length = 12)
    private String type;

    /** DBF: ID */
    @Id
    @Column(length = 12)
    private String idUser;

    /** DBF: NAME */
    @Id
    @Column(length = 24)
    private String name;

    /** DBF: READONLY */
    private Boolean readonly;

    /** DBF: CKVAL */
    private Integer ckval;

    /** DBF: DATA */
    @Column(columnDefinition = "text")
    private String data;

    /** DBF: UPDATED */
    private LocalDate updated;

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

    /** Clave compuesta (type, idUser, name) */
    public static class Key implements Serializable {
        private String type;
        private String idUser;
        private String name;
        public Key() {}
        public Key(String type, String idUser, String name) {
            this.type = type;
            this.idUser = idUser;
            this.name = name;
        }
        public String getType() { return type; }
        public void setType(String type) { this.type = type; }
        public String getIdUser() { return idUser; }
        public void setIdUser(String idUser) { this.idUser = idUser; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        @Override public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Key)) return false;
            Key x = (Key) o;
            return Objects.equals(type, x.type) && Objects.equals(idUser, x.idUser) && Objects.equals(name, x.name);
        }
        @Override public int hashCode() { return Objects.hash(type, idUser, name); }
    }
}
