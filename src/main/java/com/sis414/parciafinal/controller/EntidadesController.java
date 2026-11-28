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
@RequestMapping("/api/entidades")
@Tag(name = "Entidades", description = "Catálogo de entidades públicas por gestión (tabla ENTIDADES).")
public class EntidadesController {

    private final EntidadesService entidadesService;

    public EntidadesController(EntidadesService entidadesService) {
        this.entidadesService = entidadesService;
    }

    @GetMapping
    @Operation(summary = "Listar entidades (paginado)")
    public PageResponse<EntidadesResponse> listar(@ParameterObject Pageable pageable) {
        return entidadesService.listar(pageable);
    }

    @GetMapping("/{gestion}/{entidad}")
    @Operation(summary = "Obtener entidad por clave")
    public EntidadesResponse obtener(@PathVariable("gestion") Integer gestion, @PathVariable("entidad") String entidad) {
        return entidadesService.obtener(gestion, entidad);
    }

    @PostMapping
    @Operation(summary = "Crear entidad")
    public ResponseEntity<EntidadesResponse> crear(@Valid @RequestBody EntidadesRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(entidadesService.crear(request));
    }

    @PutMapping("/{gestion}/{entidad}")
    @Operation(summary = "Modificar entidad")
    public EntidadesResponse actualizar(@PathVariable("gestion") Integer gestion, @PathVariable("entidad") String entidad, @Valid @RequestBody EntidadesRequest request) {
        return entidadesService.actualizar(gestion, entidad, request);
    }

    @DeleteMapping("/{gestion}/{entidad}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Eliminar entidad")
    public void eliminar(@PathVariable("gestion") Integer gestion, @PathVariable("entidad") String entidad) {
        entidadesService.eliminar(gestion, entidad);
    }
}
