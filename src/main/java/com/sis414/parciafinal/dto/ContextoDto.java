package com.sis414.parciafinal.dto;

/** Cabecera de la pantalla principal: ENTIDAD, UNIDAD, BACKUPS. */
public record ContextoDto(String entidad, String entidadDescripcion, String unidad, String unidadDescripcion, Integer gestion, String backups) {}
