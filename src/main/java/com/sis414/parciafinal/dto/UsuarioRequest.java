package com.sis414.parciafinal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Datos de entrada de usuario. La contraseña solo se recibe (nunca se devuelve).
 * 'usuario' solo se usa al crear; al modificar se toma de la URL. En PUT la contraseña es opcional.
 */
public record UsuarioRequest(
        @Size(max = 10) String usuario,
        @NotBlank @Size(max = 40) String completo,
        @Size(max = 50) String des,
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) @Size(min = 4, max = 10) String password,
        Boolean tipo,
        @Size(max = 8) String grupo,
        @Size(max = 8) String usuar
) {
}
