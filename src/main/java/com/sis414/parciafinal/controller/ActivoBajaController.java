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
@RequestMapping("/api/activos-baja")
@Tag(name = "Activos dados de baja", description = "Histórico de activos dados de baja (tabla BAJAS). Id autogenerado.")
public class ActivoBajaController {

    private final ActivoBajaService activoBajaService;

    public ActivoBajaController(ActivoBajaService activoBajaService) {
        this.activoBajaService = activoBajaService;
    }

    @GetMapping
    @Operation(summary = "Listar activos dados de baja (paginado)")
    public PageResponse<ActivoBajaResponse> listar(@ParameterObject Pageable pageable) {
        return activoBajaService.listar(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener activo dado de baja por clave")
    public ActivoBajaResponse obtener(@PathVariable("id") Long id) {
        return activoBajaService.obtener(id);
    }

    @PostMapping
    @Operation(summary = "Crear activo dado de baja")
    public ResponseEntity<ActivoBajaResponse> crear(@Valid @RequestBody ActivoBajaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(activoBajaService.crear(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modificar activo dado de baja")
    public ActivoBajaResponse actualizar(@PathVariable("id") Long id, @Valid @RequestBody ActivoBajaRequest request) {
        return activoBajaService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Eliminar activo dado de baja")
    public void eliminar(@PathVariable("id") Long id) {
        activoBajaService.eliminar(id);
    }
}
