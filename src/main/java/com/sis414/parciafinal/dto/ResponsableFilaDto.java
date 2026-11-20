package com.sis414.parciafinal.dto;

/** Fila de la grilla de la pantalla OFICINA: Cod. / Responsable / Cargo / CI / Expedido / Estado. */
public record ResponsableFilaDto(Long id, Integer codigo, String nombre, String cargo, String ci, String expedido, String estado) {}
