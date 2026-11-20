package com.sis414.parciafinal.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
public record RevalorizacionDto(
    Long id,
    String codigo,
    BigDecimal costo,
    BigDecimal depacuAnt,
    Integer diaReval,
    Integer dia,
    Integer mesReval,
    Integer mes,
    Integer anoReval,
    Integer ano,
    BigDecimal vidaUtil,
    String resolucion,
    String observ,
    String usuar,
    LocalDate feult,
    BigDecimal depacu,
    BigDecimal actua,
    BigDecimal depGestion,
    LocalDate fecMod,
    String usuMod,
    Long unidadId,
    Long activoId
) {}
