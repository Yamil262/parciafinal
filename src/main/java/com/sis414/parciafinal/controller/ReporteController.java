package com.sis414.parciafinal.controller;

import com.sis414.parciafinal.entity.Reporte;
import com.sis414.parciafinal.repository.ReporteRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/** Reporte: tabla REPORTS.DBF. La clave es la del DBF (reportName, modType), no hay "id" artificial. */
@RestController
@RequestMapping("/api/reportes")
@Tag(name = "Generar Reportes")
public class ReporteController {

    private final ReporteRepository repository;

    public ReporteController(ReporteRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    @Operation(summary = "Reporte - Listar")
    public List<Reporte> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{reportName}/{modType}")
    @Operation(summary = "Reporte - Obtener por clave")
    public Reporte findByKey(@PathVariable("reportName") String reportName, @PathVariable("modType") String modType) {
        return repository.findById(new Reporte.Key(reportName, modType))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reporte no encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Reporte - Nuevo")
    public Reporte create(@RequestBody Reporte body) {
        if (body.getReportName() == null || body.getModType() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Falta la clave: reportName, modType");
        }
        if (repository.existsById(new Reporte.Key(body.getReportName(), body.getModType()))) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Reporte ya existe");
        }
        return repository.save(body);
    }

    @PutMapping("/{reportName}/{modType}")
    @Operation(summary = "Reporte - Modificar")
    public Reporte update(@PathVariable("reportName") String reportName, @PathVariable("modType") String modType, @RequestBody Reporte body) {
        if (!repository.existsById(new Reporte.Key(reportName, modType))) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reporte no encontrado");
        }
        body.setReportName(reportName);
        body.setModType(modType);
        return repository.save(body);
    }

    @DeleteMapping("/{reportName}/{modType}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Reporte - Eliminar")
    public void delete(@PathVariable("reportName") String reportName, @PathVariable("modType") String modType) {
        if (!repository.existsById(new Reporte.Key(reportName, modType))) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reporte no encontrado");
        }
        repository.deleteById(new Reporte.Key(reportName, modType));
    }
}
