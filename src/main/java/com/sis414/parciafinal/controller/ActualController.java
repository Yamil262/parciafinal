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
@RequestMapping("/api/activos")
@Tag(name = "Activos fijos", description = "Activos fijos vigentes (tabla ACTUAL): costo, depreciación, oficina y responsable. Baja lógica: api_estado = 3.")
public class ActualController {

    private final ActualService actualService;
    private final RevalService revalService;
    private final TrasfeService trasfeService;

    public ActualController(ActualService actualService, RevalService revalService, TrasfeService trasfeService) {
        this.actualService = actualService;
        this.revalService = revalService;
        this.trasfeService = trasfeService;
    }

    @GetMapping
    @Operation(summary = "Listar activos fijos (paginado)")
    public PageResponse<ActualResponse> listar(@RequestParam(defaultValue = "false") boolean incluirAnulados,
                                          @ParameterObject Pageable pageable) {
        return actualService.listar(incluirAnulados, pageable);
    }

    @GetMapping("/{entidad}/{unidad}/{codigo}")
    @Operation(summary = "Obtener activo fijo por clave")
    public ActualResponse obtener(@PathVariable("entidad") String entidad, @PathVariable("unidad") String unidad, @PathVariable("codigo") String codigo) {
        return actualService.obtener(entidad, unidad, codigo);
    }

    @PostMapping
    @Operation(summary = "Crear activo fijo")
    public ResponseEntity<ActualResponse> crear(@Valid @RequestBody ActualRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(actualService.crear(request));
    }

    @PutMapping("/{entidad}/{unidad}/{codigo}")
    @Operation(summary = "Modificar activo fijo")
    public ActualResponse actualizar(@PathVariable("entidad") String entidad, @PathVariable("unidad") String unidad, @PathVariable("codigo") String codigo, @Valid @RequestBody ActualRequest request) {
        return actualService.actualizar(entidad, unidad, codigo, request);
    }

    @DeleteMapping("/{entidad}/{unidad}/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Anular activo fijo (baja lógica, api_estado = 3)")
    public void eliminar(@PathVariable("entidad") String entidad, @PathVariable("unidad") String unidad, @PathVariable("codigo") String codigo) {
        actualService.eliminar(entidad, unidad, codigo);
    }

    @GetMapping("/{entidad}/{unidad}/{codigo}/revaluaciones")
    @Operation(summary = "Revaluaciones del activo")
    public List<RevalResponse> revaluaciones(@PathVariable("entidad") String entidad, @PathVariable("unidad") String unidad, @PathVariable("codigo") String codigo) {
        return revalService.listarPorActivo(entidad, unidad, codigo);
    }

    @GetMapping("/{entidad}/{unidad}/{codigo}/transferencias")
    @Operation(summary = "Transferencias del activo")
    public List<TrasfeResponse> transferencias(@PathVariable("entidad") String entidad, @PathVariable("unidad") String unidad, @PathVariable("codigo") String codigo) {
        return trasfeService.listarPorActivo(entidad, unidad, codigo);
    }
}
