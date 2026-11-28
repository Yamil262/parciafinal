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
@RequestMapping("/api/estructuras-entidad")
@Tag(name = "Estructuras institucionales", description = "Clasificación sectorial/institucional de entidades por gestión (tabla EST_ENT).")
public class EstEntController {

    private final EstEntService estEntService;

    public EstEntController(EstEntService estEntService) {
        this.estEntService = estEntService;
    }

    @GetMapping
    @Operation(summary = "Listar estructuras institucionales (paginado)")
    public PageResponse<EstEntResponse> listar(@ParameterObject Pageable pageable) {
        return estEntService.listar(pageable);
    }

    @GetMapping("/{gestion}/{sectorEnt}/{subsecEnt}/{areaEnt}/{subAreaEnt}/{nivelInst}")
    @Operation(summary = "Obtener estructura institucional por clave")
    public EstEntResponse obtener(@PathVariable("gestion") Integer gestion, @PathVariable("sectorEnt") Integer sectorEnt, @PathVariable("subsecEnt") Integer subsecEnt, @PathVariable("areaEnt") Integer areaEnt, @PathVariable("subAreaEnt") Integer subAreaEnt, @PathVariable("nivelInst") Integer nivelInst) {
        return estEntService.obtener(gestion, sectorEnt, subsecEnt, areaEnt, subAreaEnt, nivelInst);
    }

    @PostMapping
    @Operation(summary = "Crear estructura institucional")
    public ResponseEntity<EstEntResponse> crear(@Valid @RequestBody EstEntRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(estEntService.crear(request));
    }

    @PutMapping("/{gestion}/{sectorEnt}/{subsecEnt}/{areaEnt}/{subAreaEnt}/{nivelInst}")
    @Operation(summary = "Modificar estructura institucional")
    public EstEntResponse actualizar(@PathVariable("gestion") Integer gestion, @PathVariable("sectorEnt") Integer sectorEnt, @PathVariable("subsecEnt") Integer subsecEnt, @PathVariable("areaEnt") Integer areaEnt, @PathVariable("subAreaEnt") Integer subAreaEnt, @PathVariable("nivelInst") Integer nivelInst, @Valid @RequestBody EstEntRequest request) {
        return estEntService.actualizar(gestion, sectorEnt, subsecEnt, areaEnt, subAreaEnt, nivelInst, request);
    }

    @DeleteMapping("/{gestion}/{sectorEnt}/{subsecEnt}/{areaEnt}/{subAreaEnt}/{nivelInst}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Eliminar estructura institucional")
    public void eliminar(@PathVariable("gestion") Integer gestion, @PathVariable("sectorEnt") Integer sectorEnt, @PathVariable("subsecEnt") Integer subsecEnt, @PathVariable("areaEnt") Integer areaEnt, @PathVariable("subAreaEnt") Integer subAreaEnt, @PathVariable("nivelInst") Integer nivelInst) {
        estEntService.eliminar(gestion, sectorEnt, subsecEnt, areaEnt, subAreaEnt, nivelInst);
    }
}
