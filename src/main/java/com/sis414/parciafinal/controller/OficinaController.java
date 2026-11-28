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
@RequestMapping("/api/oficinas")
@Tag(name = "Oficinas", description = "Oficinas de una unidad administrativa (tabla OFICINA). Baja lógica: api_estado = 3.")
public class OficinaController {

    private final OficinaService oficinaService;
    private final RespService respService;
    private final ActualService actualService;

    public OficinaController(OficinaService oficinaService, RespService respService, ActualService actualService) {
        this.oficinaService = oficinaService;
        this.respService = respService;
        this.actualService = actualService;
    }

    @GetMapping
    @Operation(summary = "Listar oficinas (paginado)")
    public PageResponse<OficinaResponse> listar(@RequestParam(defaultValue = "false") boolean incluirAnulados,
                                          @ParameterObject Pageable pageable) {
        return oficinaService.listar(incluirAnulados, pageable);
    }

    @GetMapping("/{entidad}/{unidad}/{codOfic}")
    @Operation(summary = "Obtener oficina por clave")
    public OficinaResponse obtener(@PathVariable("entidad") String entidad, @PathVariable("unidad") String unidad, @PathVariable("codOfic") Integer codOfic) {
        return oficinaService.obtener(entidad, unidad, codOfic);
    }

    @PostMapping
    @Operation(summary = "Crear oficina")
    public ResponseEntity<OficinaResponse> crear(@Valid @RequestBody OficinaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(oficinaService.crear(request));
    }

    @PutMapping("/{entidad}/{unidad}/{codOfic}")
    @Operation(summary = "Modificar oficina")
    public OficinaResponse actualizar(@PathVariable("entidad") String entidad, @PathVariable("unidad") String unidad, @PathVariable("codOfic") Integer codOfic, @Valid @RequestBody OficinaRequest request) {
        return oficinaService.actualizar(entidad, unidad, codOfic, request);
    }

    @DeleteMapping("/{entidad}/{unidad}/{codOfic}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Anular oficina (baja lógica, api_estado = 3)")
    public void eliminar(@PathVariable("entidad") String entidad, @PathVariable("unidad") String unidad, @PathVariable("codOfic") Integer codOfic) {
        oficinaService.eliminar(entidad, unidad, codOfic);
    }

    @GetMapping("/{entidad}/{unidad}/{codOfic}/responsables")
    @Operation(summary = "Responsables de la oficina")
    public List<RespResponse> responsables(@PathVariable("entidad") String entidad, @PathVariable("unidad") String unidad, @PathVariable("codOfic") Integer codOfic) {
        return respService.listarPorOficina(entidad, unidad, codOfic);
    }

    @GetMapping("/{entidad}/{unidad}/{codOfic}/activos")
    @Operation(summary = "Activos fijos asignados a la oficina")
    public List<ActualResponse> activos(@PathVariable("entidad") String entidad, @PathVariable("unidad") String unidad, @PathVariable("codOfic") Integer codOfic) {
        return actualService.listarPorOficina(entidad, unidad, codOfic);
    }
}
