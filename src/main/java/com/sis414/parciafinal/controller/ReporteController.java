package com.sis414.parciafinal.controller;

import com.sis414.parciafinal.dto.ReporteDto;
import com.sis414.parciafinal.service.ReporteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reportes")
@Tag(name = "Generar Reportes")
public class ReporteController {

    private final ReporteService service;

    public ReporteController(ReporteService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Reporte - Listar")
    public List<ReporteDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Reporte - Obtener por id")
    public ReporteDto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Reporte - Nuevo")
    public ReporteDto create(@RequestBody ReporteDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Reporte - Modificar")
    public ReporteDto update(@PathVariable Long id, @RequestBody ReporteDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Reporte - Eliminar")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
