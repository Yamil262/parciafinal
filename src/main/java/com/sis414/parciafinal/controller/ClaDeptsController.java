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
@RequestMapping("/api/departamentos")
@Tag(name = "Departamentos", description = "Departamentos de Bolivia con su sigla (tabla CLA_DEPTS).")
public class ClaDeptsController {

    private final ClaDeptsService claDeptsService;

    public ClaDeptsController(ClaDeptsService claDeptsService) {
        this.claDeptsService = claDeptsService;
    }

    @GetMapping
    @Operation(summary = "Listar departamentos (paginado)")
    public PageResponse<ClaDeptsResponse> listar(@ParameterObject Pageable pageable) {
        return claDeptsService.listar(pageable);
    }

    @GetMapping("/{codigo}")
    @Operation(summary = "Obtener departamento por clave")
    public ClaDeptsResponse obtener(@PathVariable("codigo") Integer codigo) {
        return claDeptsService.obtener(codigo);
    }

    @PostMapping
    @Operation(summary = "Crear departamento")
    public ResponseEntity<ClaDeptsResponse> crear(@Valid @RequestBody ClaDeptsRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(claDeptsService.crear(request));
    }

    @PutMapping("/{codigo}")
    @Operation(summary = "Modificar departamento")
    public ClaDeptsResponse actualizar(@PathVariable("codigo") Integer codigo, @Valid @RequestBody ClaDeptsRequest request) {
        return claDeptsService.actualizar(codigo, request);
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Eliminar departamento")
    public void eliminar(@PathVariable("codigo") Integer codigo) {
        claDeptsService.eliminar(codigo);
    }
}
