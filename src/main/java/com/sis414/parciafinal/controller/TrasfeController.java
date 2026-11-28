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
@RequestMapping("/api/transferencias")
@Tag(name = "Transferencias", description = "Transferencias de activos entre oficinas/responsables (tabla TRASFE). Id autogenerado.")
public class TrasfeController {

    private final TrasfeService trasfeService;

    public TrasfeController(TrasfeService trasfeService) {
        this.trasfeService = trasfeService;
    }

    @GetMapping
    @Operation(summary = "Listar transferencias (paginado)")
    public PageResponse<TrasfeResponse> listar(@ParameterObject Pageable pageable) {
        return trasfeService.listar(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener transferencia por clave")
    public TrasfeResponse obtener(@PathVariable("id") Long id) {
        return trasfeService.obtener(id);
    }

    @PostMapping
    @Operation(summary = "Crear transferencia")
    public ResponseEntity<TrasfeResponse> crear(@Valid @RequestBody TrasfeRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(trasfeService.crear(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modificar transferencia")
    public TrasfeResponse actualizar(@PathVariable("id") Long id, @Valid @RequestBody TrasfeRequest request) {
        return trasfeService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Eliminar transferencia")
    public void eliminar(@PathVariable("id") Long id) {
        trasfeService.eliminar(id);
    }
}
