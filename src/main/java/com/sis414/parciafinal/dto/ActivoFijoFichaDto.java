package com.sis414.parciafinal.dto;

import java.math.BigDecimal;

/** Pantalla "ADMINISTRACION DE ACTIVOS FIJOS" tal como se ve en el VSIAF. */
public record ActivoFijoFichaDto(
        Long id,
        String codigo,
        String codigoAdi,
        Integer incorporacionDia,
        Integer incorporacionMes,
        Integer incorporacionAno,
        BigDecimal indiceUfv,
        String descripcion,
        String grupo,
        String auxiliar,
        String oficina,
        String responsable,
        String cargo,
        String estadoActivo,
        String observaciones,
        String idBien,
        String orgFinanciador,
        String nroConvenio,
        Boolean depreciar,
        Boolean actualizar,
        String situacion,
        CalculoActivoDto calculo
) {}
