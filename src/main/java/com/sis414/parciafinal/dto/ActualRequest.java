package com.sis414.parciafinal.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Datos de entrada para crear o modificar: activo fijo.
 * Los campos que forman la clave solo se usan al crear; al modificar se toman de la URL.
 */
public record ActualRequest(
        @Size(max = 5) String unidad,
        @Size(max = 4) String entidad,
        @Size(max = 15) String codigo,
        @NotNull @PositiveOrZero @Max(99) Integer codCont,
        @PositiveOrZero @Max(999) Integer codAux,
        @PositiveOrZero @Digits(integer = 3, fraction = 2) BigDecimal vidaUtil,
        @NotBlank @Size(max = 150) String descrip,
        @NotNull @PositiveOrZero @Digits(integer = 12, fraction = 2) BigDecimal costo,
        @PositiveOrZero @Digits(integer = 12, fraction = 2) BigDecimal depAcu,
        @PositiveOrZero @Max(12) Integer mes,
        @PositiveOrZero @Max(9999) Integer ano,
         Boolean bRev,
        @PositiveOrZero @Max(31) Integer dia,
        @NotNull @PositiveOrZero @Max(9999) Integer codOfic,
        @NotNull @PositiveOrZero @Max(99999) Integer codResp,
        @Size(max = 4000) String observ,
        @PositiveOrZero @Max(99) Integer diaAnt,
        @PositiveOrZero @Max(99) Integer mesAnt,
        @PositiveOrZero @Max(9999) Integer anoAnt,
        @PositiveOrZero @Max(999) Integer vutAnt,
        @PositiveOrZero @Digits(integer = 12, fraction = 2) BigDecimal costoAnt,
         Boolean bandUfv,
        @PositiveOrZero @Max(99) Integer codEstado,
        @Size(max = 15) String codRube,
        @Size(max = 10) String nroConv,
        @Size(max = 3) String orgFin,
        @Size(max = 8) String usuar,
        @Size(max = 15) String codigoSec,
        @Size(max = 12) String banderas
) {
}
