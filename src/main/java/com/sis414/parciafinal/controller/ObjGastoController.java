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
@RequestMapping("/api/partidas")
@Tag(name = "Partidas de gasto", description = "Objetos del gasto / partidas presupuestarias por gestión (tabla OBJGASTO).")
public class ObjGastoController {

    private final ObjGastoService objGastoService;

    public ObjGastoController(ObjGastoService objGastoService) {
        this.objGastoService = objGastoService;
    }

    @GetMapping
    @Operation(summary = "Listar partidas de gasto (paginado)")
    public PageResponse<ObjGastoResponse> listar(@ParameterObject Pageable pageable) {
        return objGastoService.listar(pageable);
    }

    @GetMapping("/{gestion}/{partida}")
    @Operation(summary = "Obtener partida de gasto por clave")
    public ObjGastoResponse obtener(@PathVariable("gestion") Integer gestion, @PathVariable("partida") String partida) {
        return objGastoService.obtener(gestion, partida);
    }

    @PostMapping
    @Operation(summary = "Crear partida de gasto")
    public ResponseEntity<ObjGastoResponse> crear(@Valid @RequestBody ObjGastoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(objGastoService.crear(request));
    }

    @PutMapping("/{gestion}/{partida}")
    @Operation(summary = "Modificar partida de gasto")
    public ObjGastoResponse actualizar(@PathVariable("gestion") Integer gestion, @PathVariable("partida") String partida, @Valid @RequestBody ObjGastoRequest request) {
        return objGastoService.actualizar(gestion, partida, request);
    }

    @DeleteMapping("/{gestion}/{partida}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Eliminar partida de gasto")
    public void eliminar(@PathVariable("gestion") Integer gestion, @PathVariable("partida") String partida) {
        objGastoService.eliminar(gestion, partida);
    }
}
