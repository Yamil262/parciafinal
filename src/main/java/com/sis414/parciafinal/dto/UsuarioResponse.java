package com.sis414.parciafinal.dto;

import java.time.LocalDate;

/** Datos de salida de usuario: NO incluye la contraseña ni su hash. */
public record UsuarioResponse(
        String usuario,
        String completo,
        String des,
        Boolean tipo,
        String grupo,
        LocalDate feult,
        String usuar
) {
}
