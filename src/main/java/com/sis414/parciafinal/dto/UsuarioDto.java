package com.sis414.parciafinal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
public record UsuarioDto(
    Long id,
    String usuario,
    String completo,
    String des,
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) String psw,
    Boolean tipo,
    String grupo,
    LocalDate feult,
    String usuar
) {}
