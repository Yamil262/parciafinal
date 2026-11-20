package com.sis414.parciafinal.dto;

public record UnidadAdminDto(
    Long id,
    String unidad,
    String descrip,
    String ciudad,
    Integer estadoUni,
    Long entidadId
) {}
