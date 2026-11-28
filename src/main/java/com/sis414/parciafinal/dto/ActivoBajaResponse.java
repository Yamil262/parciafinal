package com.sis414.parciafinal.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

/** Datos de salida de activo dado de baja (sin datos sensibles). */
public record ActivoBajaResponse(
        Long id,
        String entidad,
        String unidad,
        String codigo,
        Integer codBaja,
        Integer codCont,
        Integer codAux,
        Integer codOfic,
        Integer codResp,
        BigDecimal costo,
        BigDecimal depAcuAnt,
        String descrip,
        Integer dBaja,
        Integer dia,
        Integer mBaja,
        Integer mes,
        Integer aBaja,
        Integer ano,
        Boolean bRev,
        BigDecimal vidaUtil,
        String resolucion,
        String observ,
        String motivo,
        BigDecimal depAcu,
        BigDecimal actua,
        BigDecimal depGestion,
        BigDecimal cosBajIni,
        BigDecimal cosDaIni,
        BigDecimal cosActBaj,
        BigDecimal actDaBaj,
        String usuar,
        LocalDate feult,
        LocalDate fecMod,
        String usuMod,
        String motivoBajaDescripcion
) {
}
