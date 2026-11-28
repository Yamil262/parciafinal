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
@RequestMapping("/api/estados")
@Tag(name = "Estados del bien", description = "Estado de conservación del bien: Bueno, Regular, Malo (tabla ESTADO).")
public class EstadoController {

    private final EstadoService estadoService;

    public EstadoController(EstadoService estadoService) {
        this.estadoService = estadoService;
    }

    @GetMapping
    @Operation(summary = "Listar estados del bien (paginado)")
    public PageResponse<EstadoResponse> listar(@ParameterObject Pageable pageable) {
        return estadoService.listar(pageable);
    }

    @GetMapping("/{codEstado}")
    @Operation(summary = "Obtener estado del bien por clave")
    public EstadoResponse obtener(@PathVariable("codEstado") Integer codEstado) {
        return estadoService.obtener(codEstado);
    }

    @PostMapping
    @Operation(summary = "Crear estado del bien")
    public ResponseEntity<EstadoResponse> crear(@Valid @RequestBody EstadoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(estadoService.crear(request));
    }

    @PutMapping("/{codEstado}")
    @Operation(summary = "Modificar estado del bien")
    public EstadoResponse actualizar(@PathVariable("codEstado") Integer codEstado, @Valid @RequestBody EstadoRequest request) {
        return estadoService.actualizar(codEstado, request);
    }

    @DeleteMapping("/{codEstado}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Eliminar estado del bien")
    public void eliminar(@PathVariable("codEstado") Integer codEstado) {
        estadoService.eliminar(codEstado);
    }
}
