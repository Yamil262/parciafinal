package com.sis414.parciafinal.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Datos de entrada para crear o modificar: activo dado de baja.
 * Los campos que forman la clave solo se usan al crear; al modificar se toman de la URL.
 */
public record ActivoBajaRequest(
        @NotBlank @Size(max = 4) String entidad,
        @NotBlank @Size(max = 5) String unidad,
        @NotBlank @Size(max = 15) String codigo,
        @NotNull @PositiveOrZero @Max(99) Integer codBaja,
        @PositiveOrZero @Max(99) Integer codCont,
        @PositiveOrZero @Max(999) Integer codAux,
        @PositiveOrZero @Max(9999) Integer codOfic,
        @PositiveOrZero @Max(99999) Integer codResp,
        @PositiveOrZero @Digits(integer = 12, fraction = 2) BigDecimal costo,
        @PositiveOrZero @Digits(integer = 12, fraction = 2) BigDecimal depAcuAnt,
        @Size(max = 120) String descrip,
        @PositiveOrZero @Max(99) Integer dBaja,
        @PositiveOrZero @Max(99) Integer dia,
        @PositiveOrZero @Max(99) Integer mBaja,
        @PositiveOrZero @Max(99) Integer mes,
        @PositiveOrZero @Max(9999) Integer aBaja,
        @PositiveOrZero @Max(9999) Integer ano,
         Boolean bRev,
        @PositiveOrZero @Digits(integer = 2, fraction = 2) BigDecimal vidaUtil,
        @Size(max = 16) String resolucion,
        @Size(max = 4000) String observ,
        @Size(max = 120) String motivo,
        @PositiveOrZero @Digits(integer = 12, fraction = 2) BigDecimal depAcu,
        @PositiveOrZero @Digits(integer = 12, fraction = 2) BigDecimal actua,
        @PositiveOrZero @Digits(integer = 12, fraction = 2) BigDecimal depGestion,
        @PositiveOrZero @Digits(integer = 12, fraction = 2) BigDecimal cosBajIni,
        @PositiveOrZero @Digits(integer = 12, fraction = 2) BigDecimal cosDaIni,
        @PositiveOrZero @Digits(integer = 12, fraction = 2) BigDecimal cosActBaj,
        @PositiveOrZero @Digits(integer = 12, fraction = 2) BigDecimal actDaBaj,
        @Size(max = 8) String usuar
) {
}
