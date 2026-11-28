package com.sis414.parciafinal.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

/** Datos de salida de activo fijo (sin datos sensibles). */
public record ActualResponse(
        String unidad,
        String entidad,
        String codigo,
        Integer codCont,
        Integer codAux,
        BigDecimal vidaUtil,
        String descrip,
        BigDecimal costo,
        BigDecimal depAcu,
        Integer mes,
        Integer ano,
        Boolean bRev,
        Integer dia,
        Integer codOfic,
        Integer codResp,
        String observ,
        Integer diaAnt,
        Integer mesAnt,
        Integer anoAnt,
        Integer vutAnt,
        BigDecimal costoAnt,
        Boolean bandUfv,
        Integer codEstado,
        String codRube,
        String nroConv,
        String orgFin,
        LocalDate feult,
        String usuar,
        Integer apiEstado,
        String codigoSec,
        String banderas,
        LocalDate fecMod,
        String usuMod,
        String unidadDescrip,
        String cuentaNombre,
        String auxiliarNombre,
        String oficinaNombre,
        String responsableNombre,
        String estadoNombre
) {
}
