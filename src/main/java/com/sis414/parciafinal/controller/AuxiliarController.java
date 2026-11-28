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
@RequestMapping("/api/auxiliares")
@Tag(name = "Auxiliares contables", description = "Subcuentas (auxiliares) de una cuenta contable dentro de una unidad (tabla AUXILIAR).")
public class AuxiliarController {

    private final AuxiliarService auxiliarService;

    public AuxiliarController(AuxiliarService auxiliarService) {
        this.auxiliarService = auxiliarService;
    }

    @GetMapping
    @Operation(summary = "Listar auxiliares contables (paginado)")
    public PageResponse<AuxiliarResponse> listar(@ParameterObject Pageable pageable) {
        return auxiliarService.listar(pageable);
    }

    @GetMapping("/{entidad}/{unidad}/{codCont}/{codAux}")
    @Operation(summary = "Obtener auxiliar contable por clave")
    public AuxiliarResponse obtener(@PathVariable("entidad") String entidad, @PathVariable("unidad") String unidad, @PathVariable("codCont") Integer codCont, @PathVariable("codAux") Integer codAux) {
        return auxiliarService.obtener(entidad, unidad, codCont, codAux);
    }

    @PostMapping
    @Operation(summary = "Crear auxiliar contable")
    public ResponseEntity<AuxiliarResponse> crear(@Valid @RequestBody AuxiliarRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(auxiliarService.crear(request));
    }

    @PutMapping("/{entidad}/{unidad}/{codCont}/{codAux}")
    @Operation(summary = "Modificar auxiliar contable")
    public AuxiliarResponse actualizar(@PathVariable("entidad") String entidad, @PathVariable("unidad") String unidad, @PathVariable("codCont") Integer codCont, @PathVariable("codAux") Integer codAux, @Valid @RequestBody AuxiliarRequest request) {
        return auxiliarService.actualizar(entidad, unidad, codCont, codAux, request);
    }

    @DeleteMapping("/{entidad}/{unidad}/{codCont}/{codAux}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Eliminar auxiliar contable")
    public void eliminar(@PathVariable("entidad") String entidad, @PathVariable("unidad") String unidad, @PathVariable("codCont") Integer codCont, @PathVariable("codAux") Integer codAux) {
        auxiliarService.eliminar(entidad, unidad, codCont, codAux);
    }
}
