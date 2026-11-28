package com.sis414.parciafinal.controller;

import com.sis414.parciafinal.common.PageResponse;
import com.sis414.parciafinal.dto.*;
import com.sis414.parciafinal.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/motivos-baja")
@Tag(name = "Motivos de baja", description = "Catálogo de motivos de baja de activos (tabla BAJA).")
public class MotivoBajaController {

    private final MotivoBajaService motivoBajaService;

    public MotivoBajaController(MotivoBajaService motivoBajaService) {
        this.motivoBajaService = motivoBajaService;
    }

    @GetMapping
    @Operation(summary = "Listar motivos de baja (paginado)")
    public PageResponse<MotivoBajaResponse> listar(@ParameterObject Pageable pageable) {
        return motivoBajaService.listar(pageable);
    }

    @GetMapping("/{codBaja}")
    @Operation(summary = "Obtener motivo de baja por clave")
    public MotivoBajaResponse obtener(@PathVariable("codBaja") Integer codBaja) {
        return motivoBajaService.obtener(codBaja);
    }

    @PostMapping
    @Operation(summary = "Crear motivo de baja")
    public ResponseEntity<MotivoBajaResponse> crear(@Valid @RequestBody MotivoBajaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(motivoBajaService.crear(request));
    }

    @PutMapping("/{codBaja}")
    @Operation(summary = "Modificar motivo de baja")
    public MotivoBajaResponse actualizar(@PathVariable("codBaja") Integer codBaja, @Valid @RequestBody MotivoBajaRequest request) {
        return motivoBajaService.actualizar(codBaja, request);
    }

    @DeleteMapping("/{codBaja}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Eliminar motivo de baja")
    public void eliminar(@PathVariable("codBaja") Integer codBaja) {
        motivoBajaService.eliminar(codBaja);
    }
}
