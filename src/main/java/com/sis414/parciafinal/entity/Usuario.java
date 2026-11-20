package com.sis414.parciafinal.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10)
    private String usuario;

    @Column(length = 40)
    private String completo;

    @Column(length = 50)
    private String des;

    @Column(length = 10)
    private String psw;

    private Boolean tipo;

    @Column(length = 8)
    private String grupo;

    private LocalDate feult;

    @Column(length = 8)
    private String usuar;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

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
