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
@RequestMapping("/api/responsables")
@Tag(name = "Responsables", description = "Responsables de activos de una oficina (tabla RESP). El CI se enmascara en las respuestas. Baja lógica: api_estado = 3.")
public class RespController {

    private final RespService respService;

    public RespController(RespService respService) {
        this.respService = respService;
    }

    @GetMapping
    @Operation(summary = "Listar responsables (paginado)")
    public PageResponse<RespResponse> listar(@RequestParam(defaultValue = "false") boolean incluirAnulados,
                                          @ParameterObject Pageable pageable) {
        return respService.listar(incluirAnulados, pageable);
    }

    @GetMapping("/{entidad}/{unidad}/{codOfic}/{codResp}")
    @Operation(summary = "Obtener responsable por clave")
    public RespResponse obtener(@PathVariable("entidad") String entidad, @PathVariable("unidad") String unidad, @PathVariable("codOfic") Integer codOfic, @PathVariable("codResp") Integer codResp) {
        return respService.obtener(entidad, unidad, codOfic, codResp);
    }

    @PostMapping
    @Operation(summary = "Crear responsable")
    public ResponseEntity<RespResponse> crear(@Valid @RequestBody RespRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(respService.crear(request));
    }

    @PutMapping("/{entidad}/{unidad}/{codOfic}/{codResp}")
    @Operation(summary = "Modificar responsable")
    public RespResponse actualizar(@PathVariable("entidad") String entidad, @PathVariable("unidad") String unidad, @PathVariable("codOfic") Integer codOfic, @PathVariable("codResp") Integer codResp, @Valid @RequestBody RespRequest request) {
        return respService.actualizar(entidad, unidad, codOfic, codResp, request);
    }

    @DeleteMapping("/{entidad}/{unidad}/{codOfic}/{codResp}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Anular responsable (baja lógica, api_estado = 3)")
    public void eliminar(@PathVariable("entidad") String entidad, @PathVariable("unidad") String unidad, @PathVariable("codOfic") Integer codOfic, @PathVariable("codResp") Integer codResp) {
        respService.eliminar(entidad, unidad, codOfic, codResp);
    }
}
