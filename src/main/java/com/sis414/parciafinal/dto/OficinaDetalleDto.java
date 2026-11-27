package com.sis414.parciafinal.dto;

import java.util.List;

public record OficinaDetalleDto(String entidad, String unidad, Integer codOfic, String nomOfic, String estado,
                                String observ, List<ResponsableFilaDto> responsables) {}
