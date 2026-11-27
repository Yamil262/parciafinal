package com.sis414.parciafinal.dto;

import java.math.BigDecimal;

/** Pantalla "ADMINISTRACION DE ACTIVOS FIJOS": registro de ACTUAL con los nombres de sus tablas relacionadas y el cálculo. */
public record ActivoFijoFichaDto(
        String entidad,
        String unidad,
        String codigo,
        String codigoAdi,
        Integer incorporacionDia,
        Integer incorporacionMes,
        Integer incorporacionAno,
        BigDecimal indiceUfv,
        String descripcion,
        Integer codCont,
        String grupo,
        Integer codAux,
        String auxiliar,
        Integer codOfic,
        String oficina,
        Integer codResp,
        String responsable,
        String cargo,
        Integer codEstado,
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
