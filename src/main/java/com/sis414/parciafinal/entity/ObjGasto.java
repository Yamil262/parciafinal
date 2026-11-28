package com.sis414.parciafinal.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import java.math.BigDecimal;
import java.time.LocalDate;

/** Tabla objgasto (migrada desde OBJGASTO.DBF). */
@Entity
@Table(name = "objgasto")
@IdClass(ObjGastoId.class)
public class ObjGasto {

    @Id
    @Column(name = "gestion", nullable = false)
    private Integer gestion;

    @Id
    @Column(name = "partida", length = 5, nullable = false)
    private String partida;

    @Column(name = "descrip", length = 50)
    private String descrip;

    public ObjGasto() {
    }

    public Integer getGestion() {
        return gestion;
    }

    public void setGestion(Integer gestion) {
        this.gestion = gestion;
    }

    public String getPartida() {
        return partida;
    }

    public void setPartida(String partida) {
        this.partida = partida;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

}
