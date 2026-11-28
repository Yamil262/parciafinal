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
@RequestMapping("/api/revaluaciones")
@Tag(name = "Revaluaciones", description = "Revaluaciones técnicas de activos (tabla REVAL). Id autogenerado.")
public class RevalController {

    private final RevalService revalService;

    public RevalController(RevalService revalService) {
        this.revalService = revalService;
    }

    @GetMapping
    @Operation(summary = "Listar revaluaciones (paginado)")
    public PageResponse<RevalResponse> listar(@ParameterObject Pageable pageable) {
        return revalService.listar(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener revaluación por clave")
    public RevalResponse obtener(@PathVariable("id") Long id) {
        return revalService.obtener(id);
    }

    @PostMapping
    @Operation(summary = "Crear revaluación")
    public ResponseEntity<RevalResponse> crear(@Valid @RequestBody RevalRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(revalService.crear(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modificar revaluación")
    public RevalResponse actualizar(@PathVariable("id") Long id, @Valid @RequestBody RevalRequest request) {
        return revalService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Eliminar revaluación")
    public void eliminar(@PathVariable("id") Long id) {
        revalService.eliminar(id);
    }
}
