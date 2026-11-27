package com.sis414.parciafinal.dto;

import java.util.List;

public record GrupoDetalleDto(Integer codCont, String nombre, Integer vidaUtil, String observ,
                              Boolean depreciar, Boolean actualizar, List<AuxiliarFilaDto> auxiliares) {}
