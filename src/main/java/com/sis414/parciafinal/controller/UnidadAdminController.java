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
@RequestMapping("/api/unidades")
@Tag(name = "Unidades administrativas", description = "Unidades administrativas de una entidad (tabla UNIDADADMIN). Raíz de la jerarquía.")
public class UnidadAdminController {

    private final UnidadAdminService unidadAdminService;
    private final OficinaService oficinaService;
    private final ActualService actualService;

    public UnidadAdminController(UnidadAdminService unidadAdminService, OficinaService oficinaService, ActualService actualService) {
        this.unidadAdminService = unidadAdminService;
        this.oficinaService = oficinaService;
        this.actualService = actualService;
    }

    @GetMapping
    @Operation(summary = "Listar unidades administrativas (paginado)")
    public PageResponse<UnidadAdminResponse> listar(@ParameterObject Pageable pageable) {
        return unidadAdminService.listar(pageable);
    }

    @GetMapping("/{entidad}/{unidad}")
    @Operation(summary = "Obtener unidad administrativa por clave")
    public UnidadAdminResponse obtener(@PathVariable("entidad") String entidad, @PathVariable("unidad") String unidad) {
        return unidadAdminService.obtener(entidad, unidad);
    }

    @PostMapping
    @Operation(summary = "Crear unidad administrativa")
    public ResponseEntity<UnidadAdminResponse> crear(@Valid @RequestBody UnidadAdminRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(unidadAdminService.crear(request));
    }

    @PutMapping("/{entidad}/{unidad}")
    @Operation(summary = "Modificar unidad administrativa")
    public UnidadAdminResponse actualizar(@PathVariable("entidad") String entidad, @PathVariable("unidad") String unidad, @Valid @RequestBody UnidadAdminRequest request) {
        return unidadAdminService.actualizar(entidad, unidad, request);
    }

    @DeleteMapping("/{entidad}/{unidad}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Eliminar unidad administrativa")
    public void eliminar(@PathVariable("entidad") String entidad, @PathVariable("unidad") String unidad) {
        unidadAdminService.eliminar(entidad, unidad);
    }

    @GetMapping("/{entidad}/{unidad}/oficinas")
    @Operation(summary = "Oficinas de la unidad")
    public List<OficinaResponse> oficinas(@PathVariable("entidad") String entidad, @PathVariable("unidad") String unidad) {
        return oficinaService.listarPorUnidad(entidad, unidad);
    }

    @GetMapping("/{entidad}/{unidad}/activos")
    @Operation(summary = "Activos fijos de la unidad")
    public List<ActualResponse> activos(@PathVariable("entidad") String entidad, @PathVariable("unidad") String unidad) {
        return actualService.listarPorUnidad(entidad, unidad);
    }
}
