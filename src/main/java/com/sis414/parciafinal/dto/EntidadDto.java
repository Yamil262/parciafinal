package com.sis414.parciafinal.dto;

public record EntidadDto(
    Long id,
    Integer gestion,
    String codigo,
    String descEnt,
    String siglaEnt,
    Integer sectorEnt,
    Integer subsecEnt,
    Integer areaEnt,
    Integer subareaEnt,
    Integer nivelInst,
    Long estructuraId
) {}
