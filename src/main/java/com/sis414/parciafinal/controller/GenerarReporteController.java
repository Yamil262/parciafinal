package com.sis414.parciafinal.controller;

import com.sis414.parciafinal.dto.FechaCalculoDto;
import com.sis414.parciafinal.dto.ReporteGenerarRequest;
import com.sis414.parciafinal.dto.ReporteGeneradoDto;
import com.sis414.parciafinal.service.CalculoActivoService;
import com.sis414.parciafinal.service.GenerarReporteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/generar-reportes")
@Tag(name = "Generar Reportes")
public class GenerarReporteController {

    private final GenerarReporteService service;
    private final CalculoActivoService calculoService;

    public GenerarReporteController(GenerarReporteService service, CalculoActivoService calculoService) {
        this.service = service;
        this.calculoService = calculoService;
    }

    @PostMapping
    @Operation(summary = "Reportes (boton Reportes de REPORTES A MEDIDA)",
            description = "Filtro en null = boton Todos. unidadId null = Consolidado. Sin dia/mes/ano usa hoy.")
    public ReporteGeneradoDto generar(@RequestBody ReporteGenerarRequest request) {
        return service.generar(request);
    }

    @GetMapping("/hoy")
    @Operation(summary = "Hoy (fecha de Calculados A)")
    public FechaCalculoDto hoy() {
        return calculoService.hoy();
    }
}
