package com.sis414.parciafinal.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

/** Tabla passw (migrada desde PASSW.DBF). El campo psw guarda SOLO el hash de la contraseña. */
@Entity
@Table(name = "passw")
public class Usuario {

    @Id
    @Column(name = "usuario", length = 10, nullable = false)
    private String usuario;

    @Column(name = "completo", length = 40)
    private String completo;

    @Column(name = "des", length = 50)
    private String des;

    /** Hash PBKDF2 (formato PBKDF2$iter$salt$hash) o valor legado "LEGACY:HEX". Nunca se expone. */
    @Column(name = "psw", length = 120)
    private String psw;

    @Column(name = "tipo")
    private Boolean tipo;

    @Column(name = "grupo", length = 8)
    private String grupo;

    @Column(name = "feult")
    private LocalDate feult;

    @Column(name = "usuar", length = 8)
    private String usuar;

    public Usuario() {
    }

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
