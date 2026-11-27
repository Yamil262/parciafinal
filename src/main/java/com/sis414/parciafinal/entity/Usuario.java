package com.sis414.parciafinal.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.time.LocalDate;

/** Tabla PASSW.DBF del VSIAF. Clave: usuario. */
@Entity
@Table(name = "vsiaf_passw")
public class Usuario {

    /** DBF: USUARIO */
    @Id
    @Column(length = 10)
    private String usuario;

    /** DBF: COMPLETO */
    @Column(length = 40)
    private String completo;

    /** DBF: DES */
    @Column(length = 50)
    private String des;

    /** DBF: PSW */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(length = 10)
    private String psw;

    /** DBF: TIPO */
    private Boolean tipo;

    /** DBF: GRUPO */
    @Column(length = 8)
    private String grupo;

    /** DBF: FEULT */
    private LocalDate feult;

    /** DBF: USUAR */
    @Column(length = 8)
    private String usuar;

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }
    public String getCompleto() { return completo; }
    public void setCompleto(String completo) { this.completo = completo; }
    public String getDes() { return des; }
    public void setDes(String des) { this.des = des; }
    public String getPsw() { return psw; }
    public void setPsw(String psw) { this.psw = psw; }
    public Boolean getTipo() { return tipo; }
    public void setTipo(Boolean tipo) { this.tipo = tipo; }
    public String getGrupo() { return grupo; }
    public void setGrupo(String grupo) { this.grupo = grupo; }
    public LocalDate getFeult() { return feult; }
    public void setFeult(LocalDate feult) { this.feult = feult; }
    public String getUsuar() { return usuar; }
    public void setUsuar(String usuar) { this.usuar = usuar; }
}
