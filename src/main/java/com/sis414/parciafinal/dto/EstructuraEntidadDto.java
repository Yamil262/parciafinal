package com.sis414.parciafinal.dto;

public record EstructuraEntidadDto(
    Long id,
    Integer gestion,
    Integer sectorEnt,
    Integer subsecEnt,
    Integer areaEnt,
    Integer subareaEnt,
    Integer nivelInst,
    String desEstruc,
    String siglaEstru,
    String apropiable
) {}
