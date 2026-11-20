package com.sis414.parciafinal.dto;

import java.util.List;

/** Pantalla GRUPO CONTABLE: grupo, nombre, vida util, observaciones, deprecia, actualiza y grilla de auxiliares. */
public record GrupoDetalleDto(Long id, Integer grupo, String nombre, Integer vidaUtil, String observaciones,
                              Boolean deprecia, Boolean actualiza, List<AuxiliarFilaDto> auxiliares) {}
